package io.github.plenglin.magix.game.entity.ai

import java.util.logging.Logger

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import io.github.plenglin.magix.GameData
import io.github.plenglin.magix.game.ability.exception.TargetRangeException
import io.github.plenglin.magix.game.entity.humanoid.Goblin

object GoblinState {

  private val logger = Logger.getLogger(getClass.getName)

  val LOOKOUT: State[Goblin] = new State[Goblin] {

    override def enter(entity: Goblin): Unit = {
      logger.info("entering lookout state")
      entity.target.set(entity.pos)
    }

    override def update(entity: Goblin): Unit = {
      if (GameData.player.pos.dst2(entity.pos) <= entity.detectionRadius2) {
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
      if (entity.pos.dst2(GameData.player.pos) <= 0.5) {
        entity.stateMachine.changeState(ATTACK)
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
        entity.melee.activateIfReady()
      } catch {
        case _: TargetRangeException => entity.stateMachine.changeState(GoblinState.APPROACH)
      }
    }

    override def exit(entity: Goblin): Unit = {

    }

    override def onMessage(entity: Goblin, telegram: Telegram): Boolean = false

  }
}
