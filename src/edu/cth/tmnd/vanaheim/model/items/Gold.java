package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.QuestItem;
import edu.cth.tmnd.vanaheim.model.items.impl.UseableItem;

final public class Gold implements QuestItem {
	
	private Creature owner;
	private int durability;
	
	public Gold(Creature owner) {
		this.owner = owner;

	}

	public String getItemName() {
		return "Gold";
	}

	public String getItemDescription() {
		return "Catgold";
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
	public int getDurability() {
		
		return 1;
	}

	@Override
	public int getCurrentDurability() {
		return durability;
	}

	@Override
	public void repair() {
		durability = getDurability();
		
	}
	
}
