package edu.cth.tmnd.vanaheim.model.creatures.impl;

import org.newdawn.slick.Animation;

import edu.cth.tmnd.vanaheim.model.Inventory;

public abstract class Monster extends Creature {

	public Monster(float x, float y, int velocity, Inventory inventory,
			int maxHP) {
		super(x, y, velocity, inventory, maxHP);
	}


}
