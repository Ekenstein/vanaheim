package edu.cth.tmnd.vanaheim.model.world;

import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.world.tiles.GrassTile;
import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

public class World {

	private NPC[] npcs;
	private TiledMap map;

	private Tile[][] tiles;

	public World(){
		tiles = new Tile[32][24];
	}

	public void initMap(TiledMap map) {
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

	public void addItemToTile(float x, float y, Item item) {
		this.tiles[(int)x][(int)y].addItem(item);
	}

	public boolean hasTileItems(float x, float y) {
		return this.tiles[(int)x][(int)y].hasItem();
	}
}
