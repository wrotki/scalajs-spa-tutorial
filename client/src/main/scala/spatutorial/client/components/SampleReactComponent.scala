package spatutorial.client.components

import japgolly.scalajs.react.vdom.prefix_<^.<
import japgolly.scalajs.react.{BackendScope, Callback, JsComponentM, JsComponentType, JsComponentU, ReactComponentB, ReactComponentU_, ReactNode}
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSName

@js.native
trait SampleReactComponentProps extends js.Object {
  def propOne: String = js.native
  def propRwo: String = js.native
}

@js.native
@JSName("SampleReactComponent")
class SampleReactComponent(props: SampleReactComponentProps) extends JsComponentU[SampleReactComponentProps, js.Object, HTMLElement]

object SampleReactComponentProps {
  def apply(propOne: String,
            propTwo: String): SampleReactComponentProps = {
    js.Dynamic.literal(
      propOne = propOne,
      propYwo = propTwo
    ).asInstanceOf[SampleReactComponentProps]
  }
}

object SampleReactComponent {

  //  ,^.style("width: 500; height: 500")
  val component = ReactComponentB[SampleReactComponentProps]("SampleReactComponent")
    .render_P(p => new SampleReactComponent(p))
    .build

  //  val component = ReactComponentB[AceProps]("AceEditor")
  //    //.render(p => new AceEditor(p))
  //    .render_P(p => new AceEditor(p))
  //    .build

  def apply(props: SampleReactComponentProps, children: ReactNode*) = component(props, children: _*)

  def apply() = component

}
