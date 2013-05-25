package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.QuestItem;

final public class Silver implements QuestItem {
	
	private int durability;
	private int itemID;
	
	
	public Silver(){
		
	}

	public String getItemName() {
		return "Silver";
	}

	public String getItemDescription() {
		return "Some shiny silver is never wrong";
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

	@Override
	public int getItemID() {
		
		return this.itemID;
	}
	
	@Override
	public String questName() {
		return "Silver mining";
	}
	
}
