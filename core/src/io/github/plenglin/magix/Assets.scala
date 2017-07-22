package io.github.plenglin.magix

import com.badlogic.gdx.assets.{AssetDescriptor, AssetManager}
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion


object Assets {

  val manager = new AssetManager()

  val dtPlayer = new AssetDescriptor[Texture]("sprite/entity/player.png", classOf[Texture])
  val dtGoblin = new AssetDescriptor[Texture]("sprite/entity/goblin.png", classOf[Texture])

  val dtMagicMissile = new AssetDescriptor[Texture]("sprite/entity/projectile/magicmissile.png", classOf[Texture])

  val dtDirt = new AssetDescriptor[Texture]("sprite/terrain/dirt.png", classOf[Texture])
  val dtTree = new AssetDescriptor[Texture]("sprite/terrain/tree.png", classOf[Texture])
  val dtGrass = new AssetDescriptor[Texture]("sprite/terrain/grass.png", classOf[Texture])

  def tPlayer: TextureRegion = {new TextureRegion(manager.get(dtPlayer))}
  def tGoblin: TextureRegion = {new TextureRegion(manager.get(dtGoblin))}

  def tMagicMissile: TextureRegion = {new TextureRegion(manager.get(dtMagicMissile))}

  def tDirt: TextureRegion = {new TextureRegion(manager.get(dtDirt))}
  def tGrass: TextureRegion = {new TextureRegion(manager.get(dtGrass))}
  def tTree: TextureRegion = {new TextureRegion(manager.get(dtTree))}

  val toLoad = Array(
    dtPlayer,
    dtGoblin,

    dtMagicMissile,

    dtDirt,
    dtTree,
    dtGrass
  )

}
