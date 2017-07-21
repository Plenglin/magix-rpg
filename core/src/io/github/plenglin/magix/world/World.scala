package io.github.plenglin.magix.world

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.github.plenglin.magix.Constants
import io.github.plenglin.magix.world.terrain.Terrain
import io.github.plenglin.magix.world.wall.Wall

class World {

  private val emptyWall = new Wall {
    override def draw(batch: SpriteBatch, cellX: Int, cellY: Int): Unit = {}

    override val durability: Double = 0
    override val name: String = "empty"
  }

  private val emptyTerrain = new Terrain {
    override def draw(batch: SpriteBatch, cellX: Int, cellY: Int): Unit = {}
    override val name: String = "empty"
    override val speed: Double = 1
  }

  val grid: Array[Array[WorldCell]] = Array.ofDim(Constants.worldGridSize, Constants.worldGridSize)

  for (i <- 0 until Constants.worldGridSize; j <- 0 until Constants.worldGridSize) {
    grid(i)(j) = new WorldCell(None, None)
  }


  def doGeneration(generator: World => Unit): Unit = {
    generator(this)
  }

  def drawTerrain(batch: SpriteBatch): Unit = {
    for (i <- 0 until Constants.worldGridSize; j <- Constants.worldGridSize - 1 to 0 by -1) {
      var cell = grid(i)(j)
      cell.terrain.getOrElse(emptyTerrain).draw(batch, i, j)
    }
  }

  def drawWall(batch: SpriteBatch): Unit = {
    for (i <- 0 until Constants.worldGridSize; j <- Constants.worldGridSize - 1 to 0 by -1) {
      var cell = grid(i)(j)
      cell.wall.getOrElse(emptyWall).draw(batch, i, j)
    }
  }


}
