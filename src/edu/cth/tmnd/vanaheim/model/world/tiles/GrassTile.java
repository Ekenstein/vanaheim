package edu.cth.tmnd.vanaheim.model.world.tiles;

import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

public class GrassTile extends Tile {

	public GrassTile(){
		
	}
	
	public boolean hasMonster() {
		System.out.println("Monster här?");
		return false;
	}
}
