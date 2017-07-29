package io.github.plenglin.magix.control

trait Loopable {

  private[control] var hasInitialized: Boolean = false

  def init(): Unit = {
    hasInitialized = true
    onInit()
  }

  protected def onInit(): Unit = {}

  def update(dt: Float): Unit = {
    onUpdate(dt)
  }

  protected def onUpdate(dt: Float): Unit = {}

  def shouldRemove: Boolean

  def remove(): Unit = {
    onRemove()
    hasInitialized = false
  }

  protected def onRemove(): Unit = {}

}
