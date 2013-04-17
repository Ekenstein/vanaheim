package edu.cth.tmnd.vanaheim.model.world.tiles.impl;

import org.newdawn.slick.Animation;

import edu.cth.tmnd.vanaheim.model.Battle;
import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public class Tile {
	
	protected Inventory items;
	protected Animation[] animation;
	
	public Tile(){
		
	}
	
	public boolean hasMonster(){
		return false;
	}
	
	public Battle generateBattle(){
		return new Battle();
	}
	
	public boolean addItem(Item item) {
		return this.items.addItem(item);
	}
	
	public Item getItem(Item item) {
		return this.items.retrieveItem(item);
	}
	
	public boolean hasItem() {
		return !this.items.isEmpty();
	}
	
	public Animation[] getAnimation() {
		return this.animation;
	}
	
	public void setAnimation(Animation[] animation) {
		this.animation = animation;
	}
}
