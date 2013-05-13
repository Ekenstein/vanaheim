package edu.cth.tmnd.vanaheim.model.items.impl;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;


public interface EatableItem extends Item {
	public abstract void eat(Creature by);
}
