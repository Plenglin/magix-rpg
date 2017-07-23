package io.github.plenglin.magix.ability
import com.badlogic.gdx.math.Vector2


/**
  * An ability that is triggered with an angle.
  */
abstract class AngularAbility extends PositionalAbility {

  /**
    * Trigger the ability pointed at an angle.
    * @param angle the angle, in radians, where 0rad is right and goes counterclockwise.
    */
  def activate(angle: Float)

  override def activate(pos: Vector2): Unit = {
    activate(source.pos.cpy.sub(pos).angleRad(Vector2.X))
  }

}
