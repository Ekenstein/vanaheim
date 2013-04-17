package edu.cth.tmnd.vanaheim.model.creatures.impl;

import org.newdawn.slick.Animation;

import edu.cth.tmnd.vanaheim.model.GameObject;
import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public abstract class Creature extends GameObject {

	protected int velocity;
	protected Inventory inventory;
	protected int currentHP, maxHP;
	protected EquipableItem equipment;
	
	public Creature(float x, float y, int velocity, Inventory inventory, int maxHP) {
		super(x, y);
		this.velocity = velocity;
		this.inventory = inventory;
		this.maxHP = this.currentHP = maxHP;
	}
	
	public int getVelocity() {
		return this.velocity;
	}
	
	public void equip(EquipableItem item) {
		if(this.equipment != null) {
			return;
		}
		
		this.equipment = (EquipableItem) this.inventory.retrieveItem(item);
	}
	
	public void unequip() {
		this.equipment = this.inventory.addItem(this.equipment) ? null : this.equipment;
	}
	
	public void setMaxHP(int hp) {
		this.maxHP = hp;
	}
	
	public int heal(int hp) {
		int healed = this.currentHP + hp;
		
		this.currentHP = healed > this.maxHP ? this.maxHP : healed;
		return healed - this.currentHP;
	}
	
	public void damage(int damage) {
		this.currentHP -= damage > this.currentHP ? this.currentHP : damage; 
	}
	
	public boolean isDead() {
		return this.currentHP == 0;
	}
	
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	public boolean addItem(Item item) {
		return this.inventory.addItem(item);
	}
	
	public Item retrieveItem(Item item) {
		return this.inventory.retrieveItem(item);
	}

	public boolean destroyItem(Item item) {
		return this.inventory.destroyItem(item);
	}
}
