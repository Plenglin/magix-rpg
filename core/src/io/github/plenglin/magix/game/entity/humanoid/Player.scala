package io.github.plenglin.magix.game.entity.humanoid

import java.util.logging.Logger

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.Assets
import io.github.plenglin.magix.game.ability.player.attacks.MagicMissileAttack
import io.github.plenglin.magix.game.entity.Entity
import io.github.plenglin.magix.game.event.entity.EntityEvent
import io.github.plenglin.magix.render.TexturedDrawable

class Player(pos: Vector2) extends Entity(pos) with TexturedDrawable {

  private val logger = Logger.getLogger(getClass.getName)

  override val targetRadius2: Float = 1
  override val center = true
  override val dimensions: Vector2 = new Vector2(1, 1)
  override var baseHP: Double = 100
  override val baseSpeed: Double = 5
  override def name: String = "Player"
  override val baseCarryWeight: Double = 100

  override def onInit(): Unit = {
    abilities += new MagicMissileAttack()
    abilities.foreach(_.onInit(this))
  }

  override def onUpdate(dt: Float): Unit = {
    moveTowardsTarget(dt)
  }

  override def onDestroy(): Unit = {

  }

  override def onEntityEvent(event: EntityEvent): Boolean = {
    logger.fine(f"$event")
    true
  }

  override def drawPos: Vector2 = pos

  override def textureRegion: TextureRegion = Assets.tPlayer

  override val baseHPRegen: Double = 0.2
  override val baseMana: Double = 100
  override val baseManaRegen: Double = 5
}
