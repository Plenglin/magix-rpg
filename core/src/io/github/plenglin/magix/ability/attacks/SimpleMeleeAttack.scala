package io.github.plenglin.magix.ability.attacks

class SimpleMeleeAttack(_damage: Double, _cooldown: Long, _name: String) extends InstantAttack {
  override def damage: Double = _damage

  /**
    * Period, in milliseconds, between attacks
    */
  override def cooldown: Long = _cooldown

  override var name: String = _name
}
