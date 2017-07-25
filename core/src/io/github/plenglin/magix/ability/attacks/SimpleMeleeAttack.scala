package io.github.plenglin.magix.ability.attacks
import io.github.plenglin.magix.types.Targetable

class SimpleMeleeAttack(_name: String, _damage: Double, _cooldown: Long, range: Float) extends InstantAttack {
  override val range2: Float = range * range

  override def damage: Double = _damage

  /**
    * Period, in milliseconds, between attacks
    */
  override def cooldown: Long = _cooldown

  override var name: String = _name

  override def preActivation(): Unit = {
    super.preActivation()
  }

  override def canTarget(target: Targetable): Boolean = {
    target.pos.dst2(source.pos) <= range2 && super.canTarget(target)
  }
}
