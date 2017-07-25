package io.github.plenglin.magix.entity.humanoid

import java.util.logging.Logger

import com.badlogic.gdx.ai.btree.Task
import com.badlogic.gdx.ai.fsm.{DefaultStateMachine, State, StateMachine}
import com.badlogic.gdx.ai.msg.Telegram
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.ability.attacks.SimpleMeleeAttack
import io.github.plenglin.magix.ability.exception.{AbilityCooldownException, InvalidTargetException, TargetRangeException}
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.entity.EntityEvent
import io.github.plenglin.magix.types.TexturedDrawable
import io.github.plenglin.magix.{Assets, GameData}

class Goblin(pos: Vector2) extends Entity(pos) with TexturedDrawable {

  private val logger = Logger.getLogger(getClass.getName)

  override def textureRegion: TextureRegion = Assets.tGoblin

  override var baseHP: Double = 30
  override var speed: Float = 4
  override var name = "Goblin"
  override val targetRadius2: Float = 1
  override def drawPos: Vector2 = pos
  override val dimensions: Vector2 = new Vector2(1, 1)
  override val center: Boolean = true

  var detectionRadius2 = 64
  var stateMachine = new DefaultStateMachine[Goblin](this)

  val melee = new SimpleMeleeAttack("Scratch", 5, 2000, 1)
  melee.onInit(this)

  override def onInit(): Unit = {
    hp = 50
    stateMachine.setInitialState(GoblinState.LOOKOUT)
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

  object GoblinState {

    val LOOKOUT: State[Goblin] = new State[Goblin] {

      override def enter(entity: Goblin): Unit = {
        logger.info("entering lookout state")
        entity.target.set(entity.pos)
      }

      override def update(entity: Goblin): Unit = {
        if (GameData.player.pos.dst2(pos) <= detectionRadius2) {
          entity.stateMachine.changeState(APPROACH)
        }
      }

      override def exit(entity: Goblin): Unit = {

      }

      override def onMessage(entity: Goblin, telegram: Telegram): Boolean = false

    }

    val APPROACH: State[Goblin] = new State[Goblin] {

      override def enter(entity: Goblin): Unit = {
        logger.info("approaching player")
      }

      override def update(entity: Goblin): Unit = {
        entity.target.set(GameData.player.pos)
        if (entity.pos.dst2(GameData.player.pos) <= melee.range2 / 2) {
          stateMachine.changeState(ATTACK)
        }
      }

      override def exit(entity: Goblin): Unit = {
        entity.target.set(entity.pos)
      }

      override def onMessage(entity: Goblin, telegram: Telegram): Boolean = {
        false
      }

    }

    val ATTACK: State[Goblin] = new State[Goblin] {

      override def enter(entity: Goblin): Unit = {
        logger.info("attacking player")
      }

      override def update(entity: Goblin): Unit = {
        try {
          entity.melee.preActivation()
          entity.melee.activate(GameData.player)
          entity.melee.finishActivation()
        } catch {
          case _: TargetRangeException => entity.stateMachine.changeState(GoblinState.APPROACH)
          case _: AbilityCooldownException =>
        }
      }

      override def exit(entity: Goblin): Unit = {

      }

      override def onMessage(entity: Goblin, telegram: Telegram): Boolean = false

    }
  }

}
