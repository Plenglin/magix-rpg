package io.github.plenglin.magix

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.entity.humanoid.Player
import io.github.plenglin.magix.world.World

import scala.collection.mutable.ListBuffer

object GameData {

  var entities: ListBuffer[Entity] = _
  var player: Player = _

  var world: World = _

  def reset(): Unit = {
    world = new World()
    entities = new ListBuffer()
    player = new Player(new Vector2(0, 0))
    player.onInit()
    entities += player
  }

}
