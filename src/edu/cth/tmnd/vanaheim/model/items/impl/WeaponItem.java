package edu.cth.tmnd.vanaheim.model.items.impl;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;

public interface WeaponItem extends Item, EquipableItem {
	public abstract void attack(Creature by, Monster target);
}
