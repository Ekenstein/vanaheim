package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.UseableItem;

final public class HealthPotion implements UseableItem {
	
	private int healing;
	private Creature owner;
	
	
	public HealthPotion(Creature owner) {
		this.owner = owner;
		this.healing = 5;
		
	}

	public String getItemName() {
		return "Healing Potion";
	}

	public String getItemDescription() {
		return "A brew made for Gods";
	}

	public int getHealing() {
		return healing;
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
		if(this.owner != null) {
			this.healing = this.owner.heal(this.healing);
		}
	}

	@Override
	public void use(Creature target) {
		target.heal(this.healing);
	}
	
	
}
