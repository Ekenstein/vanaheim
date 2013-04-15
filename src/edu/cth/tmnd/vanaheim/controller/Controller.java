package edu.cth.tmnd.vanaheim.controller;

import org.newdawn.slick.geom.Point;

import edu.cth.tmnd.vanaheim.model.Trie;
import edu.cth.tmnd.vanaheim.model.World;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.view.states.impl.GameState;



public class Controller {
	
	private Player player;
	private Trie lexicon;
	private GameState world;
	private World[] worlds;
	
	public Controller(){
		
	}

	
	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public Trie getLexicon() {
		return lexicon;
	}


	public void setLexicon(Trie lexicon) {
		this.lexicon = lexicon;
	}


	public GameState getWorld() {
		return world;
	}


	public void setWorld(GameState world) {
		this.world = world;
	}


	public World[] getWorlds() {
		return worlds;
	}


	public void setWorlds(World[] worlds) {
		this.worlds = worlds;
	}


	public void command(String command, Point location){
		
	}
	public void render(){
		
	}
	
	public Point getPlayerPosition(){
		return null;
		
	}
}

