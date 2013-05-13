package edu.cth.tmnd.vanaheim.model.world;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import edu.cth.tmnd.vanaheim.model.Battle;
import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.world.tiles.GrassTile;
import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

public class World {
	private final static int TILE_WIDTH = 32;
	private final static int TILE_HEIGHT = 32;
	private NPC[] npcs;
	public final static int WORLD_MAP = 0;
	public final static int HOUSES = 1;
	private int currentMap = WORLD_MAP;

	private Tile[][] tiles;
	
	private final int HOUSE_ENTRANCE = 377;
	private final int WILDERNESS = 803;
	private final int LOOT = 257;
	private final int QUEST_PROGRESS = 1218;
	
	private Map<Point, Integer> houseEntrances = new HashMap<Point, Integer>();
	
	private Map<Integer, boolean[][]> blockingArrays = new HashMap<Integer, boolean[][]>();
	private Map<Integer, TiledMap> maps = new HashMap<Integer, TiledMap>();

	public void initMap(int mapID, TiledMap map) throws SlickException {
		currentMap = WORLD_MAP;
		this.tiles = new Tile[map.getWidth()][map.getHeight()];
		boolean[][] blocked = new boolean[map.getWidth()][map.getHeight()];
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				//System.out.println(map.getTileId(j, i, 0));
				
				int tileID = map.getTileId(j, i, 0);
				if (tileID == WILDERNESS) {
					tiles[j][i] = new GrassTile();
				}
				
				if (map.getTileId(j, i, 6) != 0 || map.getTileId(j, i, 2) != 0) {
					blocked[j][i] = true;
				}
			}
		}
		blockingArrays.put(mapID, blocked);
		maps.put(mapID, map);
	}
	
	public void initHouse(int mapID, TiledMap map) {
		boolean[][] blocked = new boolean[map.getWidth()][map.getHeight()];
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				if (map.getTileId(j, i, 0) == 0 || map.getTileId(j, i, 1) != 0) {
					blocked[j][i] = true;
				}
			}
		}
		blockingArrays.put(mapID, blocked);
		maps.put(mapID, map);
	}
	
	public void initHouseEntrances(int x, int y) {
		Point p = new Point(x, y);
		Point p2 = new Point(x+1, y);
		houseEntrances.put(p, HOUSES);
		houseEntrances.put(p2, HOUSES);
	}
	
	public void setLoot(int x, int y, int mapID) {
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		TiledMap map = maps.get(mapID);
		map.setTileId(xPos, yPos+1, 5, LOOT);
	}
	
	public boolean isBlocked(int x, int y) {
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		boolean[][] blocked = blockingArrays.get(currentMap);
		return blocked[xPos][yPos];
	}
	
	public TiledMap getMap(int x, int y) {
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		Integer houseMap = houseEntrances.get(new Point(xPos, yPos));
		if (houseMap != null && currentMap == WORLD_MAP) {
			currentMap = houseMap;
		} else if (currentMap != WORLD_MAP) {
			if ((xPos == 15 && yPos == 19) || (xPos == 16 && yPos == 19) || xPos == 6 && yPos == 14) {
				currentMap = WORLD_MAP;
			}
		}
		return maps.get(currentMap);
	}
	
	public int getCurrentMap() {
		return this.currentMap;
	}
	
	public void questAccepted(int x, int y) {
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		maps.get(currentMap).setTileId(xPos, yPos-1, 7, QUEST_PROGRESS);
	}

	public Battle hasMonster(int x, int y) {
		Battle hasMonster = null;
		if (currentMap == WORLD_MAP) {
			if (tiles[x][y] != null) {
				System.out.println("H�r finns det gr�s");
				hasMonster = tiles[x][y].hasMonster();
			}
		}
		return hasMonster;
	}

//	public void changeTile(int x, int y) {
//		int xPos = (int)Math.floor(x / 32);
//		int yPos = (int)Math.floor(y / 32);
//		if (worldMap.getTileId(xPos, yPos, 2) != 257) {
//			worldMap.setTileId(xPos, yPos, 2, 257);
//		}
//	}

//	public void lootAll(int x, int y) {
//		int xPos = (int)Math.floor(x / 32);
//		int yPos = (int)Math.floor(y / 32);
//		if (map.getTileId(xPos, yPos, 2) == 257) {
//			System.out.println("H�r finns loot");
//		}
//	}

	private int getX(float x) {
		return (int)Math.floor(x / TILE_WIDTH);
	}
	
	private int getY(float y) {
		return (int)Math.floor(y / TILE_HEIGHT);
	}
	
	public boolean addItemsToTile(float x, float y, List<Item> item) {
		if(!this.withinBounds(x, y)) {
			return false;
		}
		
		return this.tiles[this.getX(x)][this.getY(y)].addItems(item);
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
