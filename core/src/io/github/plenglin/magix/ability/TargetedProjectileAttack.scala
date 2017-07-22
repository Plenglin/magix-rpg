package io.github.plenglin.magix.ability

import java.util.NoSuchElementException

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.entity.projectile.HomingProjectile
import io.github.plenglin.magix.{Damageable, Targetable}

abstract class TargetedProjectileAttack(source: Entity) extends TargetedAbility(source) {

  def generateProjectile(target: Targetable): HomingProjectile

  override def onTriggered(target: Targetable): Unit = try {
    target match {
      case d: Damageable => {
        val proj = generateProjectile(d)

      }
      case _ => throw new InvalidTargetException(Option(target))
    }
  } catch {
    case _: NoSuchElementException => throw new InvalidTargetException(None)
  }

}
