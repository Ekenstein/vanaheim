package edu.cth.tmnd.vanaheim.controller;

import org.newdawn.slick.geom.Point;

import edu.cth.tmnd.vanaheim.model.Trie;
import edu.cth.tmnd.vanaheim.model.World;
import edu.cth.tmnd.vanaheim.model.creatures.Player;
import edu.cth.tmnd.vanaheim.view.states.impl.GameState;



public class Controller {
	
	Player player;
	Trie lexicon;
	GameState world;
	World[] worlds;
	
	public Controller(){
		
	}

	
	public void command(String command, Point location){
		
	}
	public void render(){
		
	}
	
	public Point getPlayerPosition(){
		return null;
		
	}
}

