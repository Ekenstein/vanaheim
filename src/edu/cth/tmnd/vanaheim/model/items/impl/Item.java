package edu.cth.tmnd.vanaheim.model.items.impl;

import org.newdawn.slick.Image;

public interface Item {
	
	
	public abstract int getItemID();
	
	/**
	 * The name of the item
	 * @return	the name of the item
	 */
	public abstract String getItemName();
	
	/**
	 * The weight of the item.
	 * @return	the item weight
	 */
	public abstract double getItemWeight();
	
	/**
	 * The item description.
	 * @return	the item description.
	 */
	public abstract String getItemDescription();
	
	/**
	 * The base durability of the item.
	 * @return	base durability of the item.
	 */
	public abstract int getDurability();
	
	/**
	 * The current durability of the item.
	 * @return	the current durability of the item.
	 */
	public abstract int getCurrentDurability();
	
	/**
	 * Repairs the item to the base durability.
	 */
	public abstract void repair();
	
	
	/**
	 * Get the items image
	 */
	public abstract Image getImage();
}
