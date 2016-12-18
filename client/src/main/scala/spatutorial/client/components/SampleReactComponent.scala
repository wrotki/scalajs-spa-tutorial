package spatutorial.client.components

import japgolly.scalajs.react.{BackendScope, JsComponentM, JsComponentType}
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName


trait SampleReactComponentProperty extends js.Object {
  val propOne: js.UndefOr[String] = js.native
}

trait SampleReactComponentState extends js.Object {
  val num: js.UndefOr[Int] = js.native
  val num2: js.UndefOr[Int] = js.native
}

@JSName("SampleReactComponent")
object SampleReactComponent
  extends JsComponentType[SampleReactComponentProperty, SampleReactComponentState, HTMLElement]

trait SampleReactComponentM
  extends JsComponentM[SampleReactComponentProperty, SampleReactComponentState, HTMLElement] {
  def getNum(): Int = js.native
  def setNum(n: Int): Unit = js.native
}

object SampleReactComponentProperty {
  def apply(ref: js.UndefOr[String] = js.undefined, propOne: js.UndefOr[String] = js.undefined): SampleReactComponentProperty = {
    val p = js.Dynamic.literal()

    ref.foreach(p.updateDynamic("ref")(_))
    propOne.foreach(p.updateDynamic("propOne")(_))

    p.asInstanceOf[SampleReactComponentProperty]
  }
}

object SampleReactComponentState {
  def apply(prevState: SampleReactComponentState)(
    num: js.UndefOr[Int] = js.undefined,
    num2: js.UndefOr[Int] = js.undefined): SampleReactComponentState = {
    val p = js.Dynamic.literal()

    num.orElse(prevState.num).foreach(p.updateDynamic("num")(_))
    num2.orElse(prevState.num2).foreach(p.updateDynamic("num2")(_))

    p.asInstanceOf[SampleReactComponentState]
  }
}

@JSName("SampleReactComponent")
object SampleReactComponent extends JSComponentType[SampleReactComponentProperty, HTMLElement]

trait SampleReactComponentM extends JSComponentM[SampleReactComponentProperty, HTMLElement] {
  def getNum(): Int = js.native

  def setNum(n: Int): Unit = js.native
}
