package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.DrinkableItem;

final public class HealthPotion implements DrinkableItem {
	
	private int healing;
	
	private int itemID;
	
	public HealthPotion() {
		this.healing = this.getDurability();
		this.itemID = 2;
	}

	public String getItemName() {
		return "Healing Potion";
	}

	public String getItemDescription() {
		return "A brew made for Gods";
	}
	
	@Override
	public double getItemWeight() {
		return 0.5;
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

	@Override
	public void drink(Creature by) {
		if(by == null) {
			return;
		}
		
		// make sure that the creature has the item.
		if(by.getItem(this) == null) {
			return;
		}
		
		// make sure that the creature doesn't have full HP first.
		if(by.getCurrentHP() == by.getMaxHP()) {
			return;
		}
		
		// heal the creature
		by.heal(this.healing);
		
		// remove the item from the creature's inventory.
		by.destroyItem(this);
	}
	
	
}
