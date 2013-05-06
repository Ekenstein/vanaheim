package edu.cth.tmnd.vanaheim.model.items.impl;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;

public interface UseableItem extends Item {
	public abstract void use(Creature by);
	public abstract void use(Creature by, Creature target);
}
