package io.github.plenglin.magix

import com.badlogic.gdx.assets.{AssetDescriptor, AssetManager}
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{BitmapFont, TextureRegion}
import com.badlogic.gdx.scenes.scene2d.ui.Skin


object Assets {

  val manager = new AssetManager()

  lazy val fArial: BitmapFont = manager.get(dfArial)
  lazy val tPlayer: TextureRegion = new TextureRegion(manager.get(dtPlayer))
  lazy val tGoblin: TextureRegion = new TextureRegion(manager.get(dtGoblin))
  lazy val tMagicMissile: TextureRegion = new TextureRegion(manager.get(dtMagicMissile))
  lazy val tDirt: TextureRegion = new TextureRegion(manager.get(dtDirt))
  lazy val tGrass: TextureRegion = new TextureRegion(manager.get(dtGrass))
  lazy val tTree: TextureRegion = new TextureRegion(manager.get(dtTree))
  lazy val skinGame: Skin = manager.get(dskinGame)
  lazy val tDagger: TextureRegion = new TextureRegion(manager.get(dtDagger))

  val dfArial = new AssetDescriptor[BitmapFont]("com/badlogic/gdx/utils/arial-15.fnt", classOf[BitmapFont])
  val dtPlayer = new AssetDescriptor[Texture]("sprite/entity/player.png", classOf[Texture])
  val dtGoblin = new AssetDescriptor[Texture]("sprite/entity/goblin.png", classOf[Texture])
  val dtMagicMissile = new AssetDescriptor[Texture]("sprite/entity/projectile/magicmissile.png", classOf[Texture])
  val dtDirt = new AssetDescriptor[Texture]("sprite/terrain/dirt.png", classOf[Texture])
  val dtDagger = new AssetDescriptor[Texture]("sprite/item/dagger.png", classOf[Texture])
  val dtTree = new AssetDescriptor[Texture]("sprite/terrain/tree.png", classOf[Texture])
  val dtGrass = new AssetDescriptor[Texture]("sprite/terrain/grass.png", classOf[Texture])
  val dskinGame = new AssetDescriptor[Skin]("skin/skin/skin.skin", classOf[Skin])  // Temporary skin

  val toLoad = Array(

    dfArial,

    dtPlayer,
    dtGoblin,

    dtMagicMissile,

    dtDirt,
    dtTree,
    dtGrass,
    dtDagger,

    dskinGame

  )

}
