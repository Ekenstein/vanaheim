package edu.cth.tmnd.vanaheim.model.items;

import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.UseableItem;

final public class HealthPotion implements UseableItem {
	
	private int healing;
	
	private int itemID;
	
	public HealthPotion() {
		ObjectMapper.getInstance().registerObject(this.getItemName(), this);
		this.healing = this.getDurability();
		this.itemID = 2;
	}

	public String getItemName() {
		return "Healing Potion";
	}

	public String getItemDescription() {
		return "A brew made for Gods";
	}
	
	@Override
	public double getItemWeight() {
		return 0.5;
	}

	@Override
	public void use(Creature by) {
		if(by != null && this.healing != 0) {
			this.healing = by.heal(this.healing);
		}
	}

	@Override
	public void use(Creature by, Creature target) {
		target.heal(this.healing);
	}

	@Override
	public int getDurability() {
		return 5;
	}

	@Override
	public void repair() {
		this.healing = this.getDurability();
	}

	@Override
	public int getCurrentDurability() {
		return this.healing;
	}

	@Override
	public int getItemID() {
		return this.itemID;
	}
	
	
}
