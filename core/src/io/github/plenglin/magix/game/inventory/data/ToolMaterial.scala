package io.github.plenglin.magix.game.inventory.data

import com.badlogic.gdx.Gdx

import scala.xml.XML

case class ToolMaterial(name: String, cost: Double, damage: Double, speed: Double, toughness: Double, weight: Double, flexibility: Double, attunement: Double) {

}

object ToolMaterial {

  var materials: List[ToolMaterial] = _

  def initMaterials(): Unit = {
    val xml = XML.loadFile(Gdx.files.internal("data/materials.xml").file())
    materials = (xml \ "material").map(ToolMaterial.fromXML).toList
  }

  def fromXML(node: scala.xml.Node): ToolMaterial = new ToolMaterial(
      (node \ "@name").toString,
      (node \ "@cost").toString.toDouble,
      (node \ "@damage").toString.toDouble,
      (node \ "@speed").toString.toDouble,
      (node \ "@toughness").toString.toDouble,
      (node \ "@weight").toString.toDouble,
      (node \ "@flexibility").toString.toDouble,
      (node \ "@attunement").toString.toDouble
    )

}