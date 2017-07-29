package io.github.plenglin.magix.ui

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.{ProgressBar, Skin}
import io.github.plenglin.magix.{Assets, GameData}

class GameScreenHUD(stage: Stage, skin: Skin) {

  val hpBar = new ProgressBar(0, 100, 1, true, skin, "health-bar")
  hpBar.setWidth(50)
  hpBar.setHeight(200)
  hpBar.setPosition(10, 10)
  stage.addActor(hpBar)

  val manaBar = new ProgressBar(0, 100, 1, true, skin, "mana-bar")
  manaBar.setWidth(50)
  manaBar.setHeight(200)
  manaBar.setPosition(70, 10)
  stage.addActor(manaBar)

  def update(): Unit = {
    hpBar.setRange(0, GameData.player.maxHP.toFloat)
    hpBar.setValue(GameData.player.hp.toFloat)
    manaBar.setRange(0, GameData.player.maxMana.toFloat)
    manaBar.setValue(GameData.player.mana.toFloat)
    new ProgressBar.ProgressBarStyle()
  }

}