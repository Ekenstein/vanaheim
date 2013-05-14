package edu.cth.tmnd.vanaheim.model.items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.QuestItem;

final public class Gold implements QuestItem {
	
	private Creature owner;
	private int durability;
	private Image img;
	private int itemID;
	
	public Gold() throws SlickException {
		this.itemID = 0;
		//img = new Image("data/gold");
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

	@Override
	public int getItemID() {
		return this.itemID;
	}

	@Override
	public Image getImage() {
		return this.img;
	}

	@Override
	public String questName() {
		return "Gold mining";
	}
	
}
