package edu.cth.tmnd.vanaheim.model;

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
