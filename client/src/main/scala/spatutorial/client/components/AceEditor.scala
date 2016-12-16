package spatutorial.client.components

import japgolly.scalajs.react.{ReactComponentB, ReactNode}
import japgolly.scalajs.react.vdom.prefix_<^.{<, ^}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.univeq.UnivEq
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.scalajs.js.annotation.JSName

//import scala.language.implicitConversions
import scala.scalajs.js
import scalacss.ScalaCssReact._
import scalacss.Defaults._

@js.native
trait AceProps extends js.Object {
  def program: String = js.native
}

object AceProps {
  def apply(program: String): AceProps = {
    js.Dynamic.literal(
      program = program
    ).asInstanceOf[AceProps]
  }
}

@js.native
@JSName("ReactAce")
class AceEditor(props: AceProps) extends js.Object

object AceEditor {

  case class Props(program: String)

  val component = ReactComponentB[Props]("AceEditor")
    .render( _ => <.div("Editor should be here:"))
    .componentDidMount(scope => Callback {
      // access context of the canvas
      new AceEditor(AceProps("Some program"))
    }).build

  def apply(props: Props, children: ReactNode*) = component(props, children: _*)
  def apply() = component

}
