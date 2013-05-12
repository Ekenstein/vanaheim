package edu.cth.tmnd.vanaheim.model.world.tiles.impl;

import edu.cth.tmnd.vanaheim.model.Battle;
import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import java.util.Stack;

public abstract class Tile {
	
	protected Inventory items;
	protected enum FightingState { DEFENDING, FIGHTING };
	protected Stack<FightingState> pending;
	
	/**
	 * Returns a battle object if there was a monster
	 * on this tile. Otherwise null;
	 * @return	A battle object if a monster was encountered,
	 * 			otherwise null.
	 */
	public abstract Battle hasMonster();
	
	public boolean addItem(Item item) {
		if(this.canContainItems()) {
			return this.items.addItem(item);
		}
		return false;
	}
	
	protected abstract boolean canContainItems();
	
	public Item getItem(Item item) {
		if(this.canContainItems()) {
			return this.items.retrieveItem(item);
		}
		return null;
	}
	
	public boolean hasItem() {
		return !this.items.isEmpty();
	}
	
	public Inventory getInventory() {
		if(this.canContainItems()) {
			return this.items;
		}
		
		return null;
	}
}
