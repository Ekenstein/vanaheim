package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.QuestItem;

final public class Gold implements QuestItem {
	
	private int durability;
	private int itemID;
	
	public Gold() {
		this.itemID = 0;
		//img = new Image("data/gold");
	}

	public String getItemName() {
		return "Gold";
	}

	public String getItemDescription() {
		return "Catgold";
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
		return "Gold mining";
	}
	
}
