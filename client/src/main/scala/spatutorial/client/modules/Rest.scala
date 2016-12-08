package spatutorial.client.modules

import diode.data.Pot
import diode.react.ModelProxy
import japgolly.scalajs.react.vdom.prefix_<^.<
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB, ReactElement}
import spatutorial.client.components.Bootstrap.{Button, Panel}
import spatutorial.client.components.{Icon, TodoList}
import spatutorial.client.services._

object Rest {
  case class Props(proxy: ModelProxy[Pot[Rest]])

  case class State(request: String, response: String)

  class Backend($: BackendScope[Props, State]) {
    def mounted(props: Props) =
    // dispatch a message to refresh the todos, which will cause TodoStore to fetch todos from the server
      Callback.when(props.proxy().isEmpty)(props.proxy.dispatchCB(RefreshRest))

    def doNothing(request: Option[String]) = {
      Callback.log("Rest button pushed")
      $.modState(s => s.copy())
    }

    def render(p: Props, s: State) =
      Button(Button.Props(doNothing(None)), Icon.plusSquare, " New")
  }


  // create the React component for To Do management
  val component = ReactComponentB[Props]("TODO")
    .initialState(State("/foo","{\"bar\": null}")) // initial state from TodoStore
    .renderBackend[Backend]
    .componentDidMount(scope => scope.backend.mounted(scope.props))
    .build


  /** Returns a function compatible with router location system while using our own props */
  def apply(proxy: ModelProxy[Pot[Rest]]) = component(Props(proxy))
}
