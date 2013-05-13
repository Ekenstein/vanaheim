package edu.cth.tmnd.vanaheim.model.creatures.impl;

import java.util.Collection;
import java.util.HashSet;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import java.util.List;

public abstract class Monster extends Creature {

	protected Collection<Item> lootPool;
	
	public Monster(final float x, final float y, final int velocity,
			final int maxHP, final String creatureName, boolean register) {
		super(x, y, velocity, maxHP, creatureName, register);
		this.lootPool = new HashSet<Item>();
	}

	/**
	 * Will add items to the loot pool.
	 */
	protected abstract void initLootPool();
	
	/**
	 * Drops one or more items from the loot pool.
	 * @return List of items.
	 */
	public abstract List<Item> dropItems();
}
