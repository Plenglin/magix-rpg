package io.github.plenglin.magix.world

import java.util.NoSuchElementException

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.sun.xml.internal.bind.v2.model.core.NonElement
import io.github.plenglin.magix.Constants
import io.github.plenglin.magix.world.terrain.Terrain
import io.github.plenglin.magix.world.wall.Wall

class World {

  val grid: Array[Array[WorldCell]] = Array.ofDim(Constants.worldGridSize, Constants.worldGridSize)

  for (i <- 0 until Constants.worldGridSize; j <- 0 until Constants.worldGridSize) {
    grid(i)(j) = new WorldCell(i, j, None, None)
  }


  def doGeneration(generator: World => Unit): Unit = {
    generator(this)
  }

  def drawTerrain(batch: SpriteBatch): Unit = {
    cells.foreach(c => c.terrain.get.draw(batch, c.i, c.j))
  }

  def drawWall(batch: SpriteBatch): Unit = {
    walls.foreach(_.draw(batch))
  }

  def cells: Iterable[WorldCell] = grid.flatMap(j => j)

  def walls: Iterable[Wall] = cells.filter(_.wall.isDefined).map(_.wall.get)
}
