package io.github.plenglin.magix.entity.humanoid

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureRegion}
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.Assets
import io.github.plenglin.magix.entity.{LivingEntity, TexturedEntity}
import io.github.plenglin.magix.event.Event

class Player(pos: Vector2) extends LivingEntity(pos) with TexturedEntity {

  override var texture: TextureRegion = Assets.tPlayer
  override var baseHP: Double = 100
  override var hp: Double = _
  override var speed: Float = 5

  override def onInit(): Unit = {
    hp = 100
  }

  override def onUpdate(dt: Float): Unit = {
    moveTowardsTarget(dt)
  }

  override def onEvent(event: Event): Unit = {

  }

  override def onDestroy(): Unit = {

  }

}
