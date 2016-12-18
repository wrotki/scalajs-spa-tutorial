package spatutorial.client.components

import japgolly.scalajs.react.{BackendScope, JsComponentM, JsComponentType, ReactComponentU_, ReactNode}
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js._
import scala.scalajs.js.annotation.JSName

case class SampleReactComponent(propOne : UndefOr[String] = undefined,
                                propTwo : UndefOr[String] = undefined) {
  def apply(): ReactComponentU_ = {

    def toJS = {
      val p = Dynamic.literal()
      propOne.foreach(v => p.updateDynamic("propOne")(v))
      propTwo.foreach(v => p.updateDynamic("propTwo")(v))
      p
    }

    val f = Dynamic.global.SampleReactComponent
    f(toJS).asInstanceOf[ReactComponentU_]
  }
}