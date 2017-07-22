package io.github.plenglin.magix

trait Damageable extends Targetable {

  var hp: Double          // How much health it has
  def maxHP: Double       // How much health it can have
  def armor: Double = 0   // Percent of incoming damage negated. Armor cannot heal.

  def isDead: Boolean = {
    hp < 0
  }

  /**
    * Apply a change in health to the object. This accounts for the armor, as well.
    * @param change how much to change. If positive, heals. If negative, damages.
    */
  def applyHealthChange(change: Double): Unit = {
    if (change < 0) {
      var dmg = -change
      hp = hp - dmg * math.max(0, 1 - armor)
    } else {
      hp += math.min(change, maxHP)
    }
  }

}
