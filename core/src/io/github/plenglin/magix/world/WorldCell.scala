package io.github.plenglin.magix.world

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.github.plenglin.magix.world.terrain.Terrain
import io.github.plenglin.magix.world.wall.Wall

class WorldCell(var i: Int, var j: Int, var terrain: Option[Terrain], var wall: Option[Wall]) {

}
