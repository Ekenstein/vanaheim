package edu.cth.tmnd.vanaheim.model.creatures.impl;

public abstract class Monster extends Creature {

	public Monster(final float x, final float y, final int velocity,
			final int maxHP, final String creatureName) {
		super(x, y, velocity, maxHP, creatureName);
	}


}
