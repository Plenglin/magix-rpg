package io.github.plenglin.magix.screen

import java.util.logging.Logger

import com.badlogic.gdx.Input.Buttons
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.math.{Vector2, Vector3}
import com.badlogic.gdx.{Gdx, Input, InputProcessor, Screen}
import io.github.plenglin.magix.entity.humanoid.Goblin
import io.github.plenglin.magix.world.terrain.TerrainDirt
import io.github.plenglin.magix.world.wall.WallTree
import io.github.plenglin.magix.{Constants, Damageable, GameData}

class GameScreen extends Screen with InputProcessor {

  private val logger = Logger.getLogger(getClass.getName)

  private var batch: SpriteBatch = _
  private var cam: OrthographicCamera = _

  override def show(): Unit = {

    batch = new SpriteBatch()
    cam = new OrthographicCamera()

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
      goblin.onInit()
      GameData.entities += goblin
    }

    Gdx.input.setInputProcessor(this)
  }

  override def render(delta: Float): Unit = {

    logger.finest(s"updating, dt=$delta")

    GameData.targetable.filter(_.isInstanceOf[Damageable]).map(_.asInstanceOf[Damageable]).foreach{_.processDamageQueue()}

    GameData.entities.foreach{_.onUpdate(delta)}

    logger.finest("drawing")

    cam.position.set(GameData.player.pos, 0)
    cam.zoom = 3/128f

    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    Gdx.gl.glClearColor(0, 0, 0, 1)

    cam.update()
    batch.setProjectionMatrix(cam.combined)
    batch.begin()
    GameData.world.drawTerrain(batch)
    GameData.entities.foreach{_.draw(batch)}
    GameData.world.drawWall(batch)
    batch.end()

  }

  override def hide(): Unit = {}

  override def resize(width: Int, height: Int): Unit = {
    cam.setToOrtho(false, width, height)
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
    val posOnScreen3 = cam.unproject(new Vector3(screenX, screenY, 0))
    val posOnScreen = new Vector2(posOnScreen3.x, posOnScreen3.y)
    logger.info(s"Player clicked: ($mx, $my); Unprojected: $posOnScreen")
    button match {
      case Buttons.RIGHT => GameData.player.target.set(posOnScreen)
      case Buttons.LEFT => GameData.player.abilities.head.trigger(posOnScreen)
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
