package spatutorial.client.services

import autowire._
import diode._
import diode.data._
import diode.util._
import diode.react.ReactConnector
import spatutorial.shared.{TodoItem, Api}
import boopickle.Default._
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

// Actions
case object RefreshTodos extends Action

case class UpdateAllTodos(todos: Seq[TodoItem]) extends Action

case class UpdateTodo(item: TodoItem) extends Action

case class DeleteTodo(item: TodoItem) extends Action

case class UpdateMotd(potResult: Pot[String] = Empty) extends PotAction[String, UpdateMotd] {
  override def next(value: Pot[String]) = UpdateMotd(value)
}

case object RefreshRest extends Action

case class UpdateRest(rest: Rest) extends Action


// The base model of our application
case class RootModel(todos: Pot[Todos], motd: Pot[String], rest: Pot[Rest])

case class Todos(items: Seq[TodoItem]) {
  def updated(newItem: TodoItem) = {
    items.indexWhere(_.id == newItem.id) match {
      case -1 =>
        // add new
        Todos(items :+ newItem)
      case idx =>
        // replace old
        Todos(items.updated(idx, newItem))
    }
  }
  def remove(item: TodoItem) = Todos(items.filterNot(_ == item))
}

case class Rest(path: String)

/**
  * Handles actions related to todos
  *
  * @param modelRW Reader/Writer to access the model
  */
class TodoHandler[M](modelRW: ModelRW[M, Pot[Todos]]) extends ActionHandler(modelRW) {
  override def handle = {
    case RefreshTodos =>
      effectOnly(Effect(AjaxClient[Api].getAllTodos().call().map(UpdateAllTodos)))
    case UpdateAllTodos(todos) =>
      // got new todos, update model
      updated(Ready(Todos(todos)))
    case UpdateTodo(item) =>
      // make a local update and inform server
      updated(value.map(_.updated(item)), Effect(AjaxClient[Api].updateTodo(item).call().map(UpdateAllTodos)))
    case DeleteTodo(item) =>
      // make a local update and inform server
      updated(value.map(_.remove(item)), Effect(AjaxClient[Api].deleteTodo(item.id).call().map(UpdateAllTodos)))
  }
}

/**
  * Handles actions related to the Motd
  *
  * @param modelRW Reader/Writer to access the model
  */
class MotdHandler[M](modelRW: ModelRW[M, Pot[String]]) extends ActionHandler(modelRW) {
  implicit val runner = new RunAfterJS

  override def handle = {
    case action: UpdateMotd =>
      val updateF = action.effect(AjaxClient[Api].welcomeMsg("User X").call())(identity _)
      action.handleWith(this, updateF)(PotAction.handler())
  }
}

class RestHandler[M](modelRW: ModelRW[M, Pot[Rest]]) extends ActionHandler(modelRW) {
  implicit val runner = new RunAfterJS

  override def handle = {
    case RefreshRest =>
      noChange
    case UpdateRest(rest) =>
      noChange
  }
}

// Application circuit
object SPACircuit extends Circuit[RootModel] with ReactConnector[RootModel] {
  // initial application model
  override protected def initialModel = RootModel(Empty, Empty, Empty)
  // combine all handlers into one
  override protected val actionHandler = composeHandlers(
    new TodoHandler(zoomRW(_.todos)((m, v) => m.copy(todos = v))),
    new MotdHandler(zoomRW(_.motd)((m, v) => m.copy(motd = v))),
    new RestHandler(zoomRW(_.rest)((m, v) => m.copy(rest = v)))
  )
}