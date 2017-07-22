package io.github.plenglin.magix.entity.humanoid

import java.util.logging.Logger

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.entity.{Entity, TexturedEntity}
import io.github.plenglin.magix.event.entity.EntityEvent
import io.github.plenglin.magix.{Assets, GameData}

class Goblin(pos: Vector2) extends Entity(pos) with TexturedEntity {

  private val logger = Logger.getLogger(getClass.getName)

  override var texture: TextureRegion = Assets.tGoblin

  override var baseHP: Double = 50
  override var speed: Float = 4
  override val name = "Goblin"
  override val targetRadius2: Float = 256

  var detectionRadius2 = 64

  override def onInit(): Unit = {
    hp = 50
  }

  override def onUpdate(dt: Float): Unit = {
    if (GameData.player.pos.dst2(pos) <= detectionRadius2) {
      target = GameData.player.pos
    } else {
      target = pos
    }
    moveTowardsTarget(dt)
  }

  override def onDestroy(): Unit = {

  }

  override def onEntityEvent(event: EntityEvent): Boolean = {
    true
  }

}
