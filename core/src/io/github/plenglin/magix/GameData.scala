package io.github.plenglin.magix

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.game.entity.Entity
import io.github.plenglin.magix.game.entity.humanoid.Player
import io.github.plenglin.magix.game.event.global.GlobalEvent
import io.github.plenglin.magix.game.types.Targetable
import io.github.plenglin.magix.game.world.World
import io.github.plenglin.magix.render.Drawable

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GameData {

  val eventQueue: mutable.Queue[GlobalEvent] = mutable.Queue()
  var entities: ListBuffer[Entity] = _
  var player: Player = _
  var world: World = _

  def targetables: mutable.Iterable[Targetable] = {
    entities.map(_.asInstanceOf[Targetable]) ++ world.walls
  }

  def drawables: mutable.Seq[Drawable] = {
    entities.map(_.asInstanceOf[Drawable]) ++ world.walls
  }

  def reset(): Unit = {
    world = new World()
    entities = new ListBuffer()
    player = addEntity(new Player(new Vector2(0, 0))).asInstanceOf[Player]
  }

  /**
    * Adds an entity to the list and initializes it.
    *
    * @param entity the entity to add
    * @return the same entity, for convenience's sake
    */
  def addEntity(entity: Entity): Entity = {
    entity.init()
    entities += entity
    entity
  }

  def processEventQueue(): Unit = {
    while (eventQueue.nonEmpty) {
      val event = eventQueue.dequeue()
      entities.foreach {
        _.onGlobalEvent(event)
      }
      event.onTrigger()
    }
  }

}
