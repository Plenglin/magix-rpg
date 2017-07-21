package io.github.plenglin.magix.world.terrain

import com.badlogic.gdx.graphics.g2d.TextureRegion
import io.github.plenglin.magix.Assets

class TerrainDirt extends Terrain with TexturedTerrain {
  override val speed: Double = 1
  override val name: String = "Dirt"
  override val texture: TextureRegion = Assets.tDirt
}
