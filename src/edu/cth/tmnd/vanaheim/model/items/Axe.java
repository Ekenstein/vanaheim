package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;
import edu.cth.tmnd.vanaheim.model.items.impl.UseableItem;

final public class Axe implements EquipableItem, UseableItem {
	private int damage;
	private Creature owner;
	
	public Axe(Creature owner) {
		this.damage = 10;
		this.owner = owner;
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
	public void setOwner(Creature human) {
		this.owner = human;
	}

	@Override
	public void use() {
		System.out.println("How do I shoot web?");
	}

	@Override
	public void equip() {
		if(this.owner != null) {
			this.owner.equip(this);
		}
	}

	@Override
	public void unequip() {
		if(this.owner != null) {
			this.owner.unequip();
		}
	}

	@Override
	public Creature getOwner() {
		return this.owner;
	}

	@Override
	public void use(Creature target) {
		if(target != null) {
			target.damage(this.damage);
		}
		
	}

}
