package edu.cth.tmnd.vanaheim.model.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

public class World {

	private NPC[] npcs;
	private Tile[][] tiles;
	private TiledMap map;
	
	public World(){
		
	}
	
	public void render(GameContainer container, Graphics context) throws SlickException {
		for(Tile[] tiles : this.tiles) {
			for(Tile tile : tiles) {
				tile.render(container, context);
			}
		}
	}
	

	public void update(GameContainer container) throws SlickException {
		
	}
	
	public void init(GameContainer container) {
		// TODO hämta hem alla tiles från TiledMap.
		// TODO skapa Tile objekt efter vilket grupp id det är.
		// TODO populera objekten med deras respektive bilder
		// TODO map.getObjectImage(groupID, objectID); här är en sådan metod.
		// TODO populera objekten med deras respektive properties
		// TODO map.getTileProperty(tileID, propertyName, def);
		// TODO etc
	}
}
