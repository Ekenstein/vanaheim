package edu.cth.tmnd.vanaheim.model.creatures.monsters;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.items.Gold;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public class Spider extends Monster {
	List<Item> items;

	public Spider(final float x, final float y, final int velocity, final int maxHP, final int dmg, boolean register) throws SlickException {
		super(x, y, velocity, maxHP,dmg, "Spider", register);
		this.items = new ArrayList<Item>();
		this.items.add(new Gold());
	}


	
	@Override
	public List<Item> dropItems() {
		return this.items;
	}

	@Override
	public int getDamage() {
		return super.dmg;
	}

}
