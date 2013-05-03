package edu.cth.tmnd.vanaheim.controller;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.MessageBuffer;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature.Direction;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.parser.Parser;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;
import edu.cth.tmnd.vanaheim.model.world.World;

/**
 * Controller is the intermediary part of the game. It controls the flow 
 * and direct actions to the appropriate part of game.
 * @author Daniel Jonsson
 *
 */
public class Controller {
	private final static File COMMAND_FILE = new File("data/commands");
	private final Player player;
	private final World world;
	private final ObjectMapper objectMapper;
	private final MessageBuffer msgBuffer;
	private Parser parser;
	
	
	
	private static Controller theInstance = null;

	/**
	 * @return If instance of class is already created then return this instance,
	 * 	else create a new controller and return this.
	 * @throws IOException
	 */
	public static Controller getInstance() throws IOException {
		if(theInstance == null) {
			theInstance = new Controller();
		}

		return theInstance;
	}

	/**
	 * Constructor used by getInstance() to create a new controller.
	 * Controller is the intermediary part of the game.
	 */
	private Controller() {
		this.world = new World();
		this.player = new Player(400f, 400f, 300, new Inventory(20), 100, "Harald");
		this.objectMapper = ObjectMapper.getInstance();
		this.msgBuffer = MessageBuffer.getInstance();

		try {
			this.parser = Parser.getInstance(COMMAND_FILE);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**Get the quests for the player. Both the accomplished and non-accomplished quests that reside in his questbook.
	 * @return All quests for the player.
	 */
	public Map<String, Quest> getQuests() {
		return this.player.getQuests();
	}

	/** Get the items from the players inventory.
	 * @return Items in players inventory.
	 */
	public List<Item> getItems() {
		return this.player.getItems();
	}

	/**Check if the inventory is toggled.
	 * @return True if the inventory is toggled else false.
	 */
	public boolean isInventoryToggled() {
		return this.player.isInventoryToggled();
	}

	/**Check if the questbook is toggled.
	 * @return True if questbook is toggled else false.
	 */
	public boolean isQuestBookToggled() {
		return this.player.isQuestBookToggled();
	}

	/**Add a listener to the message buffer.
	 * @param listener Listener to add to the buffer.
	 */
	public void addMessageBufferListener(final PropertyChangeListener listener) {
		this.msgBuffer.addListener(listener);
	}

	/**Get the players location.
	 * @return Players current position.
	 */
	public Point getPlayerLoc() {
		final int x = (int)this.player.getX();
		final int y = (int)this.player.getY();
		return new Point(x, y);
	}

	/**Set the players location
	 * @param p The location you want to put the player.
	 */
	public void setPlayerLoc(Point p) {
		this.player.setX(p.x);
		this.player.setY(p.y);
	}

	/**Check if a tile is blocked
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @return If location x,y is blocked return true else false
	 */
	public boolean isBlocked(final int x, final int y) {
		return this.world.isBlocked(x, y);
	}

	/**Get the map for a certain location in the world
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @return The map given by the coordinates
	 */
	public TiledMap getMap(int x, int y) {
		return this.world.getMap(x, y);
	}

	/**Initiate the map
	 * @param mapID Id on the map to initiate
	 * @param map Map to initiate
	 */
	public void initMap(int mapID, TiledMap map) {
		this.world.initMap(mapID, map);
	}

	/**Initiate a house
	 * @param x Coordinate x to initiate the house
	 * @param y Coordinate y to initiate the house
	 * @param mapID Map id to put the house
	 * @param map Map to put the house
	 */
	public void initHouse(int x, int y, int mapID, TiledMap map) {
		this.world.initHouse(x, y, mapID, map);
	}

	/**
	 * @param x Position x to check if it has monster
	 * @param y Position y to check if it has monster
	 * @return True if the position x,y has monster on it else false
	 */
	public boolean hasMonster(final int x, final int y) {
		return this.world.hasMonster(x, y);
	}

	/**
	 * @param mapID
	 */
	public void setLoot(int mapID) {
		this.world.setLoot(getPlayerLoc().x, getPlayerLoc().y, mapID);
	}

	//	public void changeTile(final int x, final int y) {
	//		this.world.changeTile(x, y);
	//	}

	//	public void lootAll(final int x, final int y) {
	//		this.world.lootAll(x, y);
	//	}

	/**Remove item if any from player and add to the tile located at players position
	 * @param item Item to drop
	 */
	public void dropItem(final Item item) {
		final Item i = this.player.getItem(item);

		if(i != null) {
			final float x = this.player.getX();
			final float y = this.player.getY();

			if(this.world.addItemToTile(x, y, i)) {
				this.player.removeItem(i);
			}
		}
	}

	/**
	 * @param x Position x to check
	 * @param y Position y to check
	 * @return True if tile got item(s) else false
	 */
	public boolean hasTileItems(final float x, final float y) {
		return this.world.hasTileItems(x, y);
	}

	/**
	 * @param x Position x of tile
	 * @param y Position y of tile
	 * @return The tiles inventory if it got any items in it else return null
	 */
	public Inventory getTileItems(final float x, final float y) {
		if(this.hasTileItems(x, y)) {
			return this.world.getTileInventory(x, y);
		}

		return null;
	}

	/**Get the players direction
	 * @return The players current direction
	 */
	public Direction getPlayerDirection() {
		return this.player.getDirection();
	}

	/**Set the players direction
	 * @param d Direction which the player should face
	 */
	public void setPlayerDirection(final Direction d) {
		this.player.setDirection(d);
	}

	/**Parse a command
	 * @param Command string to parse 
	 */
	public void parseCommand(final String command) {
		this.parser.parse(command);
	}
}

