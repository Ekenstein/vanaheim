package edu.cth.tmnd.vanaheim.model.items.impl;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;

public interface Item {
	public abstract String getItemName();
	public abstract double getItemWeight();
	public abstract String getItemDescription();
	public abstract void setOwner(Creature human);
	public abstract Creature getOwner();
	public abstract int getDurability();
	public abstract int getCurrentDurability();
	public abstract void repair();
}
