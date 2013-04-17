package edu.cth.tmnd.vanaheim.model.creatures.monsters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public class Spider extends Monster {

	public Spider(float x, float y, int velocity, Inventory inventory, int maxHP) {
		super(x, y, velocity, inventory, maxHP);
	}


}
