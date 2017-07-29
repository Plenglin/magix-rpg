package io.github.plenglin.magix.game.world

import io.github.plenglin.magix.game.world.terrain.Terrain
import io.github.plenglin.magix.game.world.wall.Wall

class WorldCell(var i: Int, var j: Int, var terrain: Option[Terrain], private var _wall: Option[Wall]) {

  wall = _wall

  def wall: Option[Wall] = _wall

  def wall_=(value: Option[Wall]): Unit = {
    _wall = value
    if (_wall.isDefined) {
      _wall.get.init()
    }
  }

}
