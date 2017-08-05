package io.github.plenglin.magix.game.inventory.items

object Weapons {

  /**
    * A material that can be used for weapons. All these values are multipliers.
    * @param name the name
    * @param cost how much it costs to buy.
    * @param damage Increases damage per hit for melee. Increases velocity for ranged weapons.
    * @param speed how fast it can swing. Affects melee only.
    * @param toughness how easy it is to break.
    * @param weight how much it weighs.
    * @param flexibility how flexible it is. Increases fire rate for ranged.
    * @param attunement how easy it is to enchant.
    */
  sealed class Material(val name: String, val cost: Double, val damage: Double, val speed: Double, val toughness: Double, val weight: Double, val flexibility: Double, val attunement: Double)

  //                             name           cost  damage  speed   toughness weight  flexibility attunement
  val WOOD =        new Material("wood",        0.10, 0.35,   2.00,   0.15,     0.10,   2.00,       1.50)
  val COPPER =      new Material("copper",      0.50, 0.65,   1.25,   0.50,     0.75,   0.65,       1.00)
  val BRONZE =      new Material("bronze",      0.65, 0.75,   1.25,   0.70,     0.85,   0.50,       1.00)
  val IRON =        new Material("iron",        0.80, 0.85,   1.00,   0.80,     1.00,   0.85,       1.00)
  val STEEL =       new Material("steel",       1.00, 1.00,   1.00,   1.00,     1.00,   1.00,       1.00)
  val SILVER =      new Material("silver",      1.50, 2.00,   1.50,   0.25,     1.00,   0.25,       2.00)
  val TITANIUM =    new Material("titanium",    1.20, 1.50,   2.50,   1.25,     0.75,   1.50,       0.75)
  val COBALT =      new Material("cobalt",      1.00, 2.00,   2.00,   1.50,     0.10,   0.80,       3.00)
  val MITHRYL =     new Material("mithryl",     1.00, 2.00,   2.00,   0.25,     0.10,   1.25,       3.00)
  val ADAMANTIUM =  new Material("adamnantium", 1.00, 3.00,   3.00,   0.25,     0.10,   2.00,       3.00)
  val UNOBTAINIUM = new Material("unobtainium", 1.00, 9999,   2.00,   9999,     0.01,   0.25,       1.00)

  /**
    * A type of melee weapon. All these values are relative to steel.
    * @param cost how much it costs to buy.
    * @param name the name.
    * @param damage how much damage it does on hit.
    * @param speed how fast it can swing, in hits per second.
    * @param toughness how tough it is. Each use takes away 1 durability.
    * @param range how far it can reach, in tiles.
    * @param weight how much it weighs.
    * @param volume how much space it takes up.
    */
  sealed class Melee(val name: String, val cost: Double, val damage: Double, val speed: Double, val toughness: Int, val range: Double, val weight: Double)

  //                           name         cost damage speed toughness range weight
  val DAGGER =      new Melee("dagger",     200, 5,     2.00, 250,      0.5,  0.50)
  val SHORTSWORD =  new Melee("shortsword", 300, 10,    1.25, 350,      1,    0.75)
  val RAPIER =      new Melee("rapier",     300, 10,    1.50, 350,      2,    1.00)
  val BROADSWORD =  new Melee("broadsword", 500, 15,    1.00, 550,      1.5,  1.50)
  val LONGSWORD =   new Melee("longsword",  500, 20,    0.75, 500,      2,    1.50)
  val BATTLEAXE =   new Melee("battleaxe",  650, 30,    0.5,  750,      2,    3)

}
