package edu.cth.tmnd.vanaheim.controller;

import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.model.Trie.Trie;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.world.World;



public class Controller {
	private Player player;
	private Trie lexicon;
	private World world;
	
	public Controller() {
		world = new World();
		player = new Player();
	}
	
	public Point getPlayerLoc() {
		return player.getLoc();
	}
	
	public void setPlayerLoc(Point p) {
		player.setPlayerLoc(p);
	}
	
	public void initTiles(TiledMap map) {
		world.initTiles(map);
	}
	
	public void checkTile(int x, int y) {
		world.checkTile(x, y);
	}
}

