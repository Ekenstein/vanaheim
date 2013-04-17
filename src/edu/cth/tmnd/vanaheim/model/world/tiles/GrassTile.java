package edu.cth.tmnd.vanaheim.model.world.tiles;

import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

public class GrassTile extends Tile {

	public boolean hasMonster() {
		System.out.println("Monster här?");
		return false;
	}

	@Override
	protected boolean canContainItems() {
		return true;
	}
}
