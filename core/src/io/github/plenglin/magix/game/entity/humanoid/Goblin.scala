package io.github.plenglin.magix.game.entity.humanoid

import java.util.logging.Logger

import com.badlogic.gdx.ai.fsm.{DefaultStateMachine, State}
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.game.entity.ai.GoblinState
import io.github.plenglin.magix.game.entity.Entity
import io.github.plenglin.magix.game.ability.entityability.{AbilityCooldownTimer, EntityAbilities}
import io.github.plenglin.magix.game.event.entity.EntityEvent
import io.github.plenglin.magix.render.TexturedDrawable
import io.github.plenglin.magix.{Assets, GameData}

class Goblin(pos: Vector2) extends Entity(pos) with TexturedDrawable {

  override val targetRadius2: Float = 1
  override val dimensions: Vector2 = new Vector2(1, 1)
  override val center: Boolean = true
  val detectionRadius2 = 64
  val stateMachine = new DefaultStateMachine[Goblin, State[Goblin]](this, GoblinState.LOOKOUT)
  val melee = new AbilityCooldownTimer(this, "Scratch", 1000, EntityAbilities.meleeAttack(GameData.player, 5, 1))
  private val logger = Logger.getLogger(getClass.getName)
  override var baseHP: Double = 30
  override var speed: Float = 4
  override var name = "Goblin"

  override def textureRegion: TextureRegion = Assets.tGoblin

  override def drawPos: Vector2 = pos

  override def onInit(): Unit = {
    //stateMachine.setInitialState(GoblinState.LOOKOUT)
  }

  override def onUpdate(dt: Float): Unit = {
    stateMachine.update()
    moveTowardsTarget(dt)
  }

  override def onDestroy(): Unit = {

  }

  override def onEntityEvent(event: EntityEvent): Boolean = {
    true
  }

}
