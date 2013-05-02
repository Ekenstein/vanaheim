package edu.cth.tmnd.vanaheim.model.world;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.tiled.TiledMap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.world.tiles.GrassTile;
import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

public class World {
	private final static int TILE_WIDTH = 32;
	private final static int TILE_HEIGHT = 32;
	private NPC[] npcs;
	private TiledMap worldMap;
	private TiledMap currentMap;

	private Tile[][] tiles;
	
	private boolean[][] blocked;
	
	private final int HOUSE_ENTRANCE = 377;
	
	private Map<Point, TiledMap> houseEntrances = new HashMap<Point, TiledMap>();

	public void initMap(TiledMap map) {
		this.worldMap = map;
		currentMap = worldMap;
		System.out.println(worldMap.getHeight());
		this.tiles = new Tile[worldMap.getWidth()][worldMap.getHeight()];
		this.blocked = new boolean[worldMap.getWidth()][worldMap.getHeight()];
		for (int i = 0; i < worldMap.getHeight(); i++) {
			for (int j = 0; j < worldMap.getWidth(); j++) {
				//System.out.println(map.getTileId(j, i, 0));
				
				//Create tiles
				
				if (map.getTileId(j, i, 6) != 0 || map.getTileId(j, i, 2) != 0) {
					blocked[j][i] = true;
				}
			}
		}
	}
	
	public void initHouse(int x, int y, TiledMap map) {
		Point p = new Point(x, y);
		Point p2 = new Point(x+1, y);
		houseEntrances.put(p, map);
		houseEntrances.put(p2, map);
	}
	
	public boolean isBlocked(int x, int y) {
		if (currentMap != worldMap) {
			return false;
		}
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		return blocked[xPos][yPos];
	}
	
	public TiledMap getMap(int x, int y) {
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		System.out.println(""+xPos + ", " + yPos);
		TiledMap houseMap = houseEntrances.get(new Point(xPos, yPos));
		if (houseMap != null && currentMap == worldMap) {
			System.out.println("Change to house map");
			currentMap = houseMap;
		} else if (currentMap != worldMap) {
			if ((xPos == 15 && yPos == 20) || (xPos == 16 && yPos == 20)) {
				System.out.println("Change to world map");
				currentMap = worldMap;
			}
		}
		return currentMap;
	}

	public void checkTile(int x, int y) {
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		//System.out.println("" + xPos + ", " + yPos);
		if (tiles[xPos][yPos] != null) {
			boolean bool = tiles[xPos][yPos].hasMonster();
		}
	}

	public void changeTile(int x, int y) {
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		if (worldMap.getTileId(xPos, yPos, 2) != 257) {
			worldMap.setTileId(xPos, yPos, 2, 257);
		}
	}

//	public void lootAll(int x, int y) {
//		int xPos = (int)Math.floor(x / 32);
//		int yPos = (int)Math.floor(y / 32);
//		if (map.getTileId(xPos, yPos, 2) == 257) {
//			System.out.println("Här finns loot");
//		}
//	}

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
