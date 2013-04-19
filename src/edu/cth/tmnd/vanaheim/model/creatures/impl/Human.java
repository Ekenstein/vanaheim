package edu.cth.tmnd.vanaheim.model.creatures.impl;

import edu.cth.tmnd.vanaheim.model.Inventory;

public abstract class Human extends Creature {
	public Human(final float x, final float y, final int velocity,
			final Inventory inventory, final int maxHP, final String creatureName) {
		super(x, y, velocity, inventory, maxHP, creatureName);
	}

	public abstract void talk(Human human);
}
