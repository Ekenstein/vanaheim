package edu.cth.tmnd.vanaheim.model.items.impl;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;

public interface DrinkableItem extends Item {
	public abstract void drink(Creature by);
}
