package io.github.plenglin.magix.game.inventory.data

import com.badlogic.gdx.Gdx

import scala.xml.XML

sealed class Weapon {}

case class MeleeWeapon(name: String, cost: Double, damage: Double, speed: Double, toughness: Double, range: Double, weight: Double) extends Weapon
case class RangedWeapon() extends Weapon

object Weapon {

  var meleeWeapons: List[MeleeWeapon] = _
  var rangedWeapons: List[RangedWeapon] = _
  def weapons: List[Weapon] = meleeWeapons ++ rangedWeapons

  def fromXML(node: scala.xml.Node): Weapon = {
    node.label match {
      case "meleeWeapon" => MeleeWeapon(
        (node \ "@name").toString,
        (node \ "@cost").toString.toDouble,
        (node \ "@damage").toString.toDouble,
        (node \ "@speed").toString.toDouble,
        (node \ "@toughness").toString.toDouble,
        (node \ "@range").toString.toDouble,
        (node \ "@weight").toString.toDouble
      )
      case "rangedWeapon" => RangedWeapon(

      )
    }
  }

  def initWeapons(): Unit = {
    val xml = XML.loadFile(Gdx.files.internal("data/weapons.xml").file())
    meleeWeapons = (xml \ "meleeWeapons").map(Weapon.fromXML).map(_.asInstanceOf[MeleeWeapon]).toList
    rangedWeapons = (xml \ "rangedWeapons").map(Weapon.fromXML).map(_.asInstanceOf[RangedWeapon]).toList
  }

}
