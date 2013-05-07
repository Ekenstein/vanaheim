package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;
import edu.cth.tmnd.vanaheim.model.items.impl.WeaponItem;

final public class Axe implements WeaponItem {
	private int damage;
	private int durability;
	
	private int itemID;
	
	public Axe() {
		this.damage = 10;
		this.durability = this.getDurability();
		this.itemID = 1;
	}
	
	@Override
	public String getItemName() {
		return "Crude Axe";
	}

	@Override
	public double getItemWeight() {
		return 10;
	}

	@Override
	public String getItemDescription() {
		return "YOLO";
	}
	
	@Override
	public void equip(Creature by) {
		if(by != null) {
			by.equip(this);
		}
	}

	@Override
	public void unequip(Creature by) {
		if(by != null) {
			by.unequip();
		}
	}

	@Override
	public int getDurability() {
		return 20;
	}

	@Override
	public void repair() {
		this.durability = this.getDurability();
	}

	@Override
	public int getCurrentDurability() {
		return this.durability;
	}

	@Override
	public int getItemID() {
		return this.itemID;
	}

	@Override
	public void attack(Creature by, Monster target) {
		if(by == null || target == null) {
			return;
		}
		
		if(this.durability <= 0) {
			return;
		}
		
		target.damage(this.damage);
		
		this.durability--;
	}
}
