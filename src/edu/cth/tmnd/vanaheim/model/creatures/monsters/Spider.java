package edu.cth.tmnd.vanaheim.model.creatures.monsters;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;
import java.util.List;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public class Spider extends Monster {

	public Spider(final float x, final float y, final int velocity, final int maxHP, boolean register) {
		super(x, y, velocity, maxHP, "Furious Spider", register);
	}

	@Override
	protected void initLootPool() {
		
	}
	
	@Override
	public List<Item> dropItems() {
		return null;
	}
}
