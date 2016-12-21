package spatutorial.client.components

import japgolly.scalajs.react.{ReactComponentB, ReactNode}
import japgolly.scalajs.react.vdom.prefix_<^.{<, ^}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.univeq.UnivEq
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js.{Dynamic, UndefOr, undefined}
import scala.scalajs.js.annotation.JSName
import scalacss.internal.Attrs.{marginBottom, position}

//import scala.language.implicitConversions
import scala.scalajs.js
import scalacss.ScalaCssReact._
import scalacss.Defaults._


// https://github.com/chandu0101/scalajs-react-components/blob/master/doc/InteropWithThirdParty.md


@js.native
trait AceProps extends js.Object {
  def mode: String = js.native

  def theme: String = js.native

  def name: String = js.native

  def fontSize: Int = js.native

  def height: String = js.native

  def width: String = js.native

  def value: String = js.native
}

object AceProps {
  def apply(mode: String,
            theme: String,
            name: String,
            fontSize: Int,
            height: String = "500px",
            width: String = "500px",
            value: String
           ): AceProps = {
    js.Dynamic.literal(
      mode = mode,
      theme = theme,
      name = name,
      fontSize = fontSize,
      height = height,
      width = width,
      value = value
    ).asInstanceOf[AceProps]
  }
}

@js.native
@JSName("ReactAceFactory")
class AceEditor(props: AceProps) extends JsComponentU[AceProps, js.Object, HTMLElement]

object AceEditor {

  def renderEditor(p: AceProps) = {
    val ae = new AceEditor(p)
    ae
  }

  val component = ReactComponentB[AceProps]("AceEditor")
    .render_P(p => renderEditor(p))
    .build

  def apply(props: AceProps, children: ReactNode*) = component(props, children: _*)

  def apply() = component

}

