package io.github.plenglin.magix

object Constants {

  val movementThreshold: Double = 0.25
  val movementThreshold2: Double = movementThreshold*movementThreshold

  val worldGridSize: Int = 120
  val cellPixelSize: Int = 32

  val randomVisualOffset = 0.25

  /**
    * Minimum amount of health to count something as dead, because rounding errors suck
    */
  val deathThreshold = 0.001

}
