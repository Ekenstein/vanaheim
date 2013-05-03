package edu.cth.tmnd.vanaheim.model.items.impl;

import org.newdawn.slick.Image;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;

public interface Item {
	public abstract int getItemID();
	public abstract String getItemName();
	public abstract double getItemWeight();
	public abstract String getItemDescription();
	public abstract int getDurability();
	public abstract int getCurrentDurability();
	public abstract void repair();
}
