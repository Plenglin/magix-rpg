package io.github.plenglin.magix.game.inventory.items

class MeleeWeapon(name: String, material: Weapons.Material, weaponType: Weapons.Melee)
  extends Item(f"${material.name.capitalize} ${weaponType.name.capitalize}", material.weight*weaponType.weight, 1) {

}
