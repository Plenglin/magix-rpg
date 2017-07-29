package io.github.plenglin.magix.game.ability.entityability

import io.github.plenglin.magix.game.ability.exception.TargetRangeException
import io.github.plenglin.magix.game.entity.Entity
import io.github.plenglin.magix.game.event.health.ChangeHealthEvent
import io.github.plenglin.magix.game.types.Damageable

object EntityAbilities {
  def meleeAttack(target: Damageable, damage: Long, range: Float): (Entity, AbilityCooldownTimer) => Unit = {

    (source, timer) => {
      if (target.pos.dst2(source.pos) <= range * range) {
        target.damageQueue += new ChangeHealthEvent(-damage, timer)
      } else {
        throw new TargetRangeException()
      }
    }

  }
}
