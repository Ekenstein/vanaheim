package edu.cth.tmnd.vanaheim.model.creatures.impl;

import edu.cth.tmnd.vanaheim.model.GameObject;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public interface Creature extends GameObject {
	public abstract int getCurrentHP();
	public abstract int getMaxHP();
	public abstract int getLevel();
	public abstract int getExp();
	public abstract void addItem(Item item);
	
}
