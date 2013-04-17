package edu.cth.tmnd.vanaheim.model.world.tiles.impl;

import edu.cth.tmnd.vanaheim.model.Battle;
import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public abstract class Tile {
	
	protected Inventory items;
	
	public boolean hasMonster(){
		return false;
	}
	
	public Battle generateBattle(){
		return new Battle();
	}
	
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
