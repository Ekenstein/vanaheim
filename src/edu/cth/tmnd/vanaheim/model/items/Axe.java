package edu.cth.tmnd.vanaheim.model.items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;
import edu.cth.tmnd.vanaheim.model.items.impl.WeaponItem;

final public class Axe implements WeaponItem {
	private int damage;
	private int durability;
	private Image img;
	private int itemID;
	
	public Axe() throws SlickException {
		this.damage = 40;
		this.durability = this.getDurability();
		this.itemID = 1;
		//img = new Image("data/axe_one.png");
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
			System.out.println("Item equipped");
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
		System.out.println("Attacks");
		
		this.durability--;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + damage;
		result = prime * result + itemID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Axe other = (Axe) obj;
		if (damage != other.damage)
			return false;
		if (itemID != other.itemID)
			return false;
		if(!this.getItemName().equals(other.getItemName()))
			return false;
		return true;
	}

	@Override
	public Image getImage() {
		return this.img;
	}
}
