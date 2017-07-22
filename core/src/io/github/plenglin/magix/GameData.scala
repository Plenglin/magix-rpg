package io.github.plenglin.magix

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.entity.humanoid.Player
import io.github.plenglin.magix.event.global.GlobalEvent
import io.github.plenglin.magix.world.World

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GameData {

  var eventQueue: mutable.Queue[GlobalEvent] = mutable.Queue()
  var entities: ListBuffer[Entity] = _
  var player: Player = _
  def targetable: mutable.Iterable[Targetable] = {
    entities.map(_.asInstanceOf[Targetable])
  }

  var world: World = _

  def reset(): Unit = {
    world = new World()
    entities = new ListBuffer()
    player = addEntity(new Player(new Vector2(0, 0))).asInstanceOf[Player]
  }

  /**
    * Adds an entity to the list and initializes it.
    * @param entity the entity to add
    * @return the same entity, for convenience's sake
    */
  def addEntity(entity: Entity): Entity = {
    entity.onInit()
    entities += entity
    entity
  }

  def processEventQueue(): Unit = {
    while (eventQueue.nonEmpty) {
      val event = eventQueue.dequeue()
      entities.foreach{_.onGlobalEvent(event)}
      event.onTrigger()
    }
  }

}
