package io.github.plenglin.magix.screen

import java.util.logging.Logger

import com.badlogic.gdx.Input.Buttons
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.math.{Vector2, Vector3}
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d._
import com.badlogic.gdx.scenes.scene2d.ui.{ProgressBar, Skin, TextButton}
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.{Gdx, InputProcessor, Screen}
import io.github.plenglin.magix.game.ability.exception.AbilityFailureException
import io.github.plenglin.magix.game.entity.humanoid.Goblin
import io.github.plenglin.magix.game.types.Damageable
import io.github.plenglin.magix.ui.GameScreenHUD
import io.github.plenglin.magix.game.world.terrain.TerrainDirt
import io.github.plenglin.magix.game.world.wall.WallTree
import io.github.plenglin.magix.{Assets, Constants, GameData}

class GameScreen extends Screen with InputProcessor {

  private val logger = Logger.getLogger(getClass.getName)

  private var batch: SpriteBatch = _
  private var gameCam: OrthographicCamera = _
  private var hud: GameScreenHUD = _
  //private var guiCam: OrthographicCamera = _

  private var uiStage: Stage = _

  override def show(): Unit = {

    batch = new SpriteBatch()
    gameCam = new OrthographicCamera()

    uiStage = new Stage(new ScreenViewport())
    hud = new GameScreenHUD(uiStage, Assets.skinGame)

    logger.info("resetting game...")
    GameData.reset()
    GameData.world.doGeneration((w) => {
      for (i <- 0 until Constants.worldGridSize; j <- 0 until Constants.worldGridSize) {
        val cell = w.grid(i)(j)
        cell.terrain = Option(new TerrainDirt())
        if (Math.random() < 0.125) {
          cell.wall = Option(new WallTree(i, j))
        }
      }
    })

    logger.info("adding goblins...")
    for (_ <- 0 until 30) {
      var goblin = new Goblin(new Vector2(math.random.toFloat, math.random.toFloat).scl(Constants.worldGridSize))
      goblin.init()
      GameData.entities += goblin
    }

    Gdx.input.setInputProcessor(this)
  }

  override def render(delta: Float): Unit = {

    logger.finest(s"updating, dt=$delta")

    // Run updates
    GameData.entities.foreach(_.onUpdate(delta))

    // Regenerate mana and health
    GameData.entities.foreach(_.doManaRegen(delta))
    GameData.targetables.filter(_.isInstanceOf[Damageable]).map(_.asInstanceOf[Damageable]).foreach(_.doHPRegen(delta))

    // Process event queues
    GameData.entities.foreach(_.controlLoop.update(delta))
    GameData.entities.foreach(_.processEventQueue())
    GameData.targetables.filter(_.isInstanceOf[Damageable]).map(_.asInstanceOf[Damageable]).foreach(_.processDamageQueue())

    uiStage.act(delta)

    logger.finest("drawing")

    // Update HUD
    hud.update()

    // Update camera
    gameCam.position.set(GameData.player.pos, 0)
    gameCam.zoom = 3 / 128f
    gameCam.update()

    // Clear screen
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    Gdx.gl.glClearColor(0, 0, 0, 1)

    batch.setProjectionMatrix(gameCam.combined)
    batch.begin()

    // Draw game
    GameData.world.drawTerrain(batch)
    GameData.drawables.foreach(_.preDraw())
    GameData.drawables.filterNot(_.cull(gameCam)).sortBy(-_.layer).foreach(_.draw(batch))

    batch.end()

    // Draw UI
    uiStage.draw()
    //Assets.fArial.draw(batch, f"${GameData.player.hp}%.2f/${GameData.player.maxHP}%.2f", 0, 15)

  }

  override def hide(): Unit = {}

  override def resize(width: Int, height: Int): Unit = {
    gameCam.setToOrtho(false, width, height)
    uiStage.getViewport.update(width, height)
  }

  override def dispose(): Unit = {}

  override def pause(): Unit = {}

  override def resume(): Unit = {}

  override def keyUp(keycode: Int): Boolean = {
    false
  }

  override def keyTyped(character: Char): Boolean = {
    false
  }

  override def mouseMoved(screenX: Int, screenY: Int): Boolean = {
    false
  }

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    false
  }

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    val mx = screenX
    val my = Gdx.graphics.getHeight - screenY
    val mouseVec = new Vector2(mx, my)
    val posOnScreen3 = gameCam.unproject(new Vector3(screenX, screenY, 0))
    val posOnScreen = new Vector2(posOnScreen3.x, posOnScreen3.y)
    logger.info(s"Player clicked: ($mx, $my); Game: $posOnScreen")
    button match {
      case Buttons.RIGHT => GameData.player.target.set(posOnScreen)
      case Buttons.LEFT =>
        try {
          val ability = GameData.player.abilities.head
          logger.info(f"${ability.cooldownComplete}, ${ability.nextActivation}")
          if (ability.cooldownComplete && ability.enoughMana) {
            ability.activate(posOnScreen)
            ability.finishActivation()
          }
        } catch {
          case _: AbilityFailureException => logger.info("Could not activate ability, skipping")
        }
    }
    true
  }

  override def scrolled(amount: Int): Boolean = {
    false
  }

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = {
    false
  }

  override def keyDown(keycode: Int): Boolean = {
    false
  }
}
