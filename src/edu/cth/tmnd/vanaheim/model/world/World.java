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
	
	public void render(GameContainer container, Graphics context) throws SlickException {
		map.render(0, 0);
	}
	

	public void update(GameContainer container) throws SlickException {
		
	}
	
	public void checkTile(int x, int y) {
		boolean bool = tiles[x][y].hasMonster();
	}
	
	public void init(GameContainer container) {
		try {
			map = new TiledMap("data/map.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				//System.out.println(map.getTileId(j, i, 1));
				if (map.getTileId(j, i, 0) == 178) {
					tiles[j][i] = new GrassTile();
				}
			}
		}
	}
}
