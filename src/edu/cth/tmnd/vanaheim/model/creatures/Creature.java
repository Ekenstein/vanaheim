package edu.cth.tmnd.vanaheim.model.creatures;

import edu.cth.tmnd.vanaheim.model.GameObject;
import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;

public abstract class Creature extends GameObject {
	private final String creatureName;
	private int healthPoints;
	private final int healthPointsTreshold;
	private EquipableItem equipment;
	private final Inventory inventory;

	public Creature(final float x, final float y,
			final String creatureName, final int healthPoints,
			final int healthPointsTreshold, final int inventorySlots) {
		super(x, y);
		this.creatureName = creatureName;
		this.healthPoints = healthPoints;
		this.healthPointsTreshold = healthPointsTreshold;
		this.inventory = new Inventory(inventorySlots);
	}


	public String getName() {
		return this.creatureName;
	}

	public int getHealthPoints() {
		return this.healthPoints;
	}

	public int getHealthPointsTreshold() {
		return this.healthPointsTreshold;
	}

	public boolean setHealthPoints(final int healthPoints) {
		if(healthPoints > this.healthPointsTreshold) {
			return false;
		}

		this.healthPoints = healthPoints;
		return true;
	}

	public void heal(final int healthPoints) {
		int hp = this.healthPoints + healthPoints;

		if(hp > this.healthPointsTreshold) {
			hp = this.healthPointsTreshold;
		}

		this.healthPoints = hp;
	}

	public boolean equip(final EquipableItem item) {
		return false;
	}
}
