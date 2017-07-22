package io.github.plenglin.magix.entity.humanoid

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.Assets
import io.github.plenglin.magix.ability.MagicMissileAttack
import io.github.plenglin.magix.entity.{Entity, TexturedEntity}

class Player(pos: Vector2) extends Entity(pos) with TexturedEntity {

  override var texture: TextureRegion = Assets.tPlayer
  override var baseHP: Double = 100
  override var speed: Float = 5
  override val targetRadius2: Float = 1

  override def onInit(): Unit = {
    abilities += new MagicMissileAttack(this)
  }

  override def onUpdate(dt: Float): Unit = {
    moveTowardsTarget(dt)
  }

  override def onDestroy(): Unit = {

  }

  override val name: String = "Player"
}
