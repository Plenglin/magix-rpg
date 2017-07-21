package io.github.plenglin.magix.entity.humanoid

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureRegion}
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.{Assets, GameData}
import io.github.plenglin.magix.entity.{LivingEntity, TexturedEntity}
import io.github.plenglin.magix.event.Event

class Goblin(pos: Vector2) extends LivingEntity(pos) with TexturedEntity {
  override var texture: TextureRegion = Assets.tGoblin

  override var baseHP: Double = 50
  override var hp: Double = _
  override var speed: Float = 4

  var detectionRadius2 = 100

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

  override def onEvent(event: Event): Unit = {

  }

}
