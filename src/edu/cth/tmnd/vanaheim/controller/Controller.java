package edu.cth.tmnd.vanaheim.controller;

import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.Trie.Trie;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.world.World;



public class Controller {
	private Player player;
	private Trie lexicon;
	private World world;
	
	public Controller() {
		world = new World();
		player = new Player(32f, 32f, 300, new Inventory(20), 100);
	}
	
	public Point getPlayerLoc() {
		return player.getLoc();
	}
	
	public void setPlayerLoc(Point p) {
		player.setPlayerLoc(p);
	}
	
	public TiledMap getMap() {
		return world.getMap();
	}
	
	public void initMap(TiledMap map) {
		world.initMap(map);
	}
	
	public void checkTile(int x, int y) {
		world.checkTile(x, y);
	}
	
	public void changeTile(int x, int y) {
		world.changeTile(x, y);
	}
	
	public void lootAll(int x, int y) {
		world.lootAll(x, y);
	}
	
	public void dropItem(Item item) {
		Item i = this.player.getItem(item);
		
		if(i != null) {
			float x = this.player.getX();
			float y = this.player.getY();
			
			if(this.world.addItemToTile(x, y, i)) {
				this.player.removeItem(i);
			}
		}
	}
	
	public boolean hasTileItems(float x, float y) {
		return this.world.hasTileItems(x, y);
	}
	
	public Inventory getTileItems(float x, float y) {
		if(this.hasTileItems(x, y)) {
			return this.world.getTileInventory(x, y);
		}
		
		return null;
	}
}

