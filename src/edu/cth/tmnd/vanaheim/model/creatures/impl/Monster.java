package edu.cth.tmnd.vanaheim.model.creatures.impl;

import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import java.util.List;

public abstract class Monster extends Creature {

	protected int dmg;
	
	public Monster(final float x, final float y, final int velocity,
			final int maxHP, final int dmg, final String creatureName, boolean register) {
		super(x, y, velocity, maxHP, dmg, creatureName, register);
		this.dmg = dmg;
	}
	
	/**
	 * Drops one or more items from the loot pool.
	 * @return List of items.
	 */
	public abstract List<Item> dropItems();
	
	public abstract int getDamage();

}
