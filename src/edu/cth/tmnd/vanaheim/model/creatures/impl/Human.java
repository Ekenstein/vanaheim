package edu.cth.tmnd.vanaheim.model.creatures.impl;

import org.newdawn.slick.Animation;

import edu.cth.tmnd.vanaheim.model.Inventory;

public abstract class Human extends Creature {
	public Human(float x, float y, Animation[] animation, float velocity,
			Inventory inventory, int maxHP) {
		super(x, y, animation, velocity, inventory, maxHP);
	}

	public abstract void talk(Human human);
}
