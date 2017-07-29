package io.github.plenglin.magix.control

import scala.collection.mutable.ListBuffer

class ControlLoop {

  val loopables: ListBuffer[Loopable] = ListBuffer()

  def update(dt: Float): Unit = {
    loopables.filterNot(_.hasInitialized).foreach(_.init())
    loopables.foreach(_.update(dt))
    val toRemove = loopables.filter(_.shouldRemove)
    for (l <- toRemove) {
      l.remove()
      loopables -= l
    }
  }

  def +=(loopable: Loopable): Unit = {
    loopables += loopable
  }

}
