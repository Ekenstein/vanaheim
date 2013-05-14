package edu.cth.tmnd.vanaheim.model.items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.QuestItem;

final public class Silver implements QuestItem {
	
	private Creature owner;
	private int durability;
	private Image img;
	
	public Silver(Creature owner) throws SlickException {
		this.owner = owner;
		//this.img = new Image("data/coin.png");

	}

	public String getItemName() {
		return "Silver";
	}

	public String getItemDescription() {
		return "Some shiny silver is never wrong";
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Image getImage() {
		return this.img;
	}

	@Override
	public String questName() {
		return "Silver mining";
	}
	
}
