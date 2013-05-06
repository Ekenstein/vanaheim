package edu.cth.tmnd.vanaheim.model.items.impl;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;

public interface EquipableItem extends Item {
	public abstract void equip(Creature by);
	public abstract void unequip(Creature by);
}
