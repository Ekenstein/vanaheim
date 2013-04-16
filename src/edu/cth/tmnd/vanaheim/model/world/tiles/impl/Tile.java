package edu.cth.tmnd.vanaheim.model.world.tiles.impl;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

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
	
	public void render(GameContainer container, Graphics context) throws SlickException {
		// TODO rendrera tilen här
	}
}
