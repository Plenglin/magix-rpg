package io.github.plenglin.magix.ability

import io.github.plenglin.magix.ability.exception.TargetRangeException
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.health.ChangeHealthEvent
import io.github.plenglin.magix.types.{Damageable, Targetable}

object EntityAbilities {
  def meleeAttack(target: Damageable, damage: Long, range: Float): (Entity, AbilityCooldownTimer) => Unit = {

    (source, timer) => {
      if (target.pos.dst2(source.pos) <= range*range) {
        target.damageQueue += new ChangeHealthEvent(-damage, timer)
      } else {
        throw new TargetRangeException()
      }
    }

  }
}
