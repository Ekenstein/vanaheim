package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;

final public class HealthPotion {
	
	private int healing;
	private Creature owner;
	
	
	public HealthPotion(Creature owner) {
		this.owner = owner;
		this.healing = 5;
		
	}
	
	public void use(Creature owner){
		this.healing = owner.heal(this.healing);
		
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
	
	
}
