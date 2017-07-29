package io.github.plenglin.magix.game.effect

/**
  * Decreases mana regen immediately after casting a spell.
  */
class WizardsWait extends EntityEffect {
  override val hidden: Boolean = true
  override val name: String = "Wizard's Wait"  // TODO: Change to catchier name

  private var end: Long = _
  
  override def onInit(): Unit = {
    end = System.currentTimeMillis() + 2000
  }

  override def shouldRemove: Boolean = {
    System.currentTimeMillis() >= end
  }

  override def coeffManaRegen: Double = 0.5

}
