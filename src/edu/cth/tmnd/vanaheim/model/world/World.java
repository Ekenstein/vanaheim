package edu.cth.tmnd.vanaheim.model.world;

import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.world.tiles.GrassTile;
import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

public class World {
	private final static int TILE_WIDTH = 32;
	private final static int TILE_HEIGHT = 32;
	private NPC[] npcs;
	private TiledMap map;

	private Tile[][] tiles;

	public void initMap(TiledMap map) {
		this.tiles = new Tile[map.getHeight()][map.getWidth()];
		this.map = map;
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				//System.out.println(map.getTileId(j, i, 2));
				if (map.getTileId(j, i, 0) == 178) {
					tiles[j][i] = new GrassTile();
				}
			}
		}
	}
	
	public TiledMap getMap() {
		return this.map;
	}

	public void checkTile(int x, int y) {
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		boolean bool = tiles[xPos][yPos].hasMonster();
	}

	public void changeTile(int x, int y) {
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		if (map.getTileId(xPos, yPos, 2) != 257) {
			map.setTileId(xPos, yPos, 2, 257);
		}
	}

	public void lootAll(int x, int y) {
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		if (map.getTileId(xPos, yPos, 2) == 257) {
			System.out.println("Här finns loot");
		}
	}

	private int getX(float x) {
		return (int)Math.floor(x / TILE_WIDTH);
	}
	
	private int getY(float y) {
		return (int)Math.floor(y / TILE_HEIGHT);
	}
	
	public boolean addItemToTile(float x, float y, Item item) {
		if(!this.withinBounds(x, y)) {
			return false;
		}
		
		return this.tiles[this.getX(x)][this.getY(y)].addItem(item);
	}
	
	private boolean withinBounds(float x, float y) {
		int xPos = this.getX(x);
		int yPos = this.getY(y);
		
		return xPos >= 0 && xPos < this.tiles[0].length && yPos >= 0 && xPos < this.tiles.length;
	}

	public boolean hasTileItems(float x, float y) {
		if(!this.withinBounds(x, y)) {
			return false;
		}
		return this.tiles[this.getX(x)][this.getY(y)].hasItem();
	}
	
	public Inventory getTileInventory(float x, float y) { 
		if(!this.withinBounds(x,y)) {
			return null;
		}
		
		return this.tiles[this.getX(x)][this.getY(y)].getInventory();
	}
}
