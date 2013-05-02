package edu.cth.tmnd.vanaheim.model.world.tiles;

import java.util.Random;

import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

public class GrassTile extends Tile {
	
	private Random rand;
	
	public GrassTile() {
		rand = new Random();
	}

	public boolean hasMonster() {
		int randomNumber = rand.nextInt(4);
		if (randomNumber == 1) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean canContainItems() {
		return true;
	}
}
