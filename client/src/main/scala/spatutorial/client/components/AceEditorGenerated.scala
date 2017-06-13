package spatutorial.client.components

import japgolly.scalajs.react.{React, ReactComponentU_, ReactNode}
import macros.JSMacro

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.{UndefOr, _}

@js.native
@JSName("ReactAce")
object AceEditor extends js.Object

case class AceEditorGenerated(mode : UndefOr[String] = undefined,
                              theme : UndefOr[String] = undefined,
                              name : UndefOr[String] = undefined,
                              height : UndefOr[String] = undefined,
                              fontSize: UndefOr[Int] = undefined,
                              value: UndefOr[String] = undefined
                             ) {
  def toJS = {
    val p = js.Dynamic.literal()
    mode.foreach(v => p.updateDynamic("mode")(v))
    theme.foreach(v => p.updateDynamic("theme")(v))
    name.foreach(v => p.updateDynamic("name")(v))
    height.foreach(v => p.updateDynamic("height")(v))
    fontSize.foreach(v => p.updateDynamic("fontSize")(v))
    value.foreach(v => p.updateDynamic("value")(v))
    p
  }

  def apply(children: js.UndefOr[ReactNode] = js.undefined) = {

    val props = JSMacro[AceEditorGenerated](this)
    val f = React.asInstanceOf[js.Dynamic].createFactory(AceEditor)
    f(props, children).asInstanceOf[ReactComponentU_]
  }
}