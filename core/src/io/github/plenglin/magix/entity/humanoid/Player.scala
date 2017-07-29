package io.github.plenglin.magix.entity.humanoid

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.Assets
import io.github.plenglin.magix.ability.attacks.MagicMissileAttack
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.types.TexturedDrawable

class Player(pos: Vector2) extends Entity(pos) with TexturedDrawable {

  override val targetRadius2: Float = 1
  override val center = true
  override val dimensions: Vector2 = new Vector2(1, 1)
  override var baseHP: Double = 100
  override var speed: Float = 5
  override var name: String = "Player"

  override def onInit(): Unit = {
    abilities += new MagicMissileAttack()
    abilities.foreach(_.onInit(this))
  }

  override def onUpdate(dt: Float): Unit = {
    moveTowardsTarget(dt)
  }

  override def onDestroy(): Unit = {

  }

  override def drawPos: Vector2 = pos

  override def textureRegion: TextureRegion = Assets.tPlayer

  override val baseHPRegen: Double = 0.2
  override val baseMana: Double = 100
  override val baseManaRegen: Double = 3
}
