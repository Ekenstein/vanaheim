package edu.cth.tmnd.vanaheim.model.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.world.tiles.GrassTile;
import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

public class World {

	private NPC[] npcs;
	private TiledMap map;
	
	private Tile[][] tiles;
	
	public World(){
		tiles = new Tile[32][24];
	}
	
	public void initTiles(TiledMap map) {
		this.map = map;
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				//System.out.println(map.getTileId(j, i, 1));
				if (map.getTileId(j, i, 0) == 178) {
					tiles[j][i] = new GrassTile();
				}
			}
		}
	}
	
	public void checkTile(int x, int y) {
		int xPos = (int)Math.floor(x / 32);
		int yPos = (int)Math.floor(y / 32);
		boolean bool = tiles[xPos][yPos].hasMonster();
	}
}
