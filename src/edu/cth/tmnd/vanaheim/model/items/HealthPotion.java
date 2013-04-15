package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.items.impl.UsableItem;

public final class HealthPotion extends Item implements UsableItem{

	final private int restors = 10;

	public HealthPotion() {
		super("Health Potion", "A magical potion that restores 10 health points to the hero who drinks a singel drop from this flask", 1,false);
	}

	@Override
	public void use() {

	}
}
