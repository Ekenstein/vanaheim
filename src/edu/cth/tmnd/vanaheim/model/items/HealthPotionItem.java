package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.items.impl.UsableItem;

public final class HealthPotionItem extends Item implements UsableItem{
	public HealthPotionItem(final String itemName, final String itemDescription, final int weight) {
		super(itemName, itemDescription, weight);
	}

	@Override
	public void use() {

	}
}
