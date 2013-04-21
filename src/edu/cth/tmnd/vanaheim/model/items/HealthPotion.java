package edu.cth.tmnd.vanaheim.model.items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.UseableItem;

final public class HealthPotion implements UseableItem {
	
	private int healing;
	private Creature owner;
	
	private int itemID;
	
	public HealthPotion(Creature owner) {
		this.owner = owner;
		this.healing = this.getDurability();
		this.itemID = 2;
	}

	public String getItemName() {
		return "Healing Potion";
	}

	public String getItemDescription() {
		return "A brew made for Gods";
	}

	public Creature getOwner() {
		return owner;
	}

	public void setOwner(Creature owner) {
		this.owner = owner;
	}

	@Override
	public double getItemWeight() {
		return 0.5;
	}

	@Override
	public void use() {
		if(this.owner != null && this.healing != 0) {
			this.healing = this.owner.heal(this.healing);
		}
	}

	@Override
	public void use(Creature target) {
		target.heal(this.healing);
	}

	@Override
	public int getDurability() {
		return 5;
	}

	@Override
	public void repair() {
		this.healing = this.getDurability();
	}

	@Override
	public int getCurrentDurability() {
		return this.healing;
	}

	@Override
	public int getItemID() {
		return this.itemID;
	}
	
	
}
