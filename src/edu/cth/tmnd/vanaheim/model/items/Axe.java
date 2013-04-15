package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.items.impl.UsableItem;

public class Axe extends Item implements UsableItem, EquipableItem {
	final private int damage = 5;
	public Axe() {
		super("Axe", "A crude axe that could simply be used for cutting wood. However, for the bearer it might also be seen as a weapon.", 10);
	}

	@Override
	public void use() {

	}

	@Override
	public void equip() {

	}
}
