package edu.cth.tmnd.vanaheim.model.items.impl;

public abstract class Item {
	protected String itemName;
	protected String itemDescription;
	protected int weight;

	public Item(final String itemName, final String itemDescription, final int weight) {
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.weight = weight;
	}

	public String getItemName() {
		return this.itemName;
	}

	public String getItemDescription() {
		return this.itemDescription;
	}
}
