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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemDescription == null) ? 0 : itemDescription.hashCode());
		result = prime * result
				+ ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Item)) {
			return false;
		}
		final Item other = (Item) obj;
		if (itemDescription == null) {
			if (other.itemDescription != null) {
				return false;
			}
		} else if (!itemDescription.equals(other.itemDescription)) {
			return false;
		}
		if (itemName == null) {
			if (other.itemName != null) {
				return false;
			}
		} else if (!itemName.equals(other.itemName)) {
			return false;
		}
		if (weight != other.weight) {
			return false;
		}
		return true;
	}
}
