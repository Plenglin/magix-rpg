package io.github.plenglin.magix

import com.badlogic.gdx.{Game, Gdx}
import io.github.plenglin.magix.screen.GameScreen

class Magix extends Game {

  override def create() {
    println(Gdx.files.internal("logging.properties").path())

    Assets.toLoad.foreach {
      Assets.manager.load
    }
    Assets.manager.finishLoading()
    setScreen(new GameScreen)
  }

  override def render() {
    super.render()
  }

  override def dispose(): Unit = {
    super.dispose()

    Assets.manager.dispose()
  }
}
