package edu.cth.tmnd.vanaheim.model.world.tiles.impl;

import edu.cth.tmnd.vanaheim.model.Battle;

public class Tile {
	
	public Tile(){
		
	}
	
	public boolean hasMonster(){
		return false;
	}
	
	public Battle generateBattle(){
		return new Battle();
	}
}
