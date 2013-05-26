package edu.cth.tmnd.vanaheim.controller;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.model.Battle;
import edu.cth.tmnd.vanaheim.model.MessageBuffer;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.StateHandler;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature.Direction;
import edu.cth.tmnd.vanaheim.model.creatures.npc.Gram.Gram;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.parser.Parser;
import edu.cth.tmnd.vanaheim.model.world.World;
import edu.cth.tmnd.vanaheim.model.Console;

/**
 * Handles all the communication between the view and
 * the model itself.
 * 
 * @author Gabriel Ekblad
 *
 */
public class Controller {
	private Player player;
	private World world;
	private ObjectMapper objectMapper;
	private final MessageBuffer msgBuffer;
	private Parser parser;
	private Battle currentBattle = null;
	private Console console = new Console();
	
	private static Controller theInstance = null;

	/**
	 * @return If instance of class is already created then return this instance,
	 * 	else create a new controller and return this.
	 * @throws IOException
	 * @throws SlickException 
	 */
	public static Controller getInstance() throws IOException, SlickException {
		if(theInstance == null) {
			theInstance = new Controller();
		}

		return theInstance;
	}

	/**
	 * Constructor used by getInstance() to create a new controller.
	 * Controller is the intermediary part of the game.
	 * @throws SlickException 
	 */
	private Controller() throws SlickException {
		this.objectMapper = ObjectMapper.getInstance();
		this.msgBuffer = MessageBuffer.getInstance();
		
		this.init();
	}
	
	private void init() throws SlickException {
		this.world = new World();
		this.player = new Player(400f, 400f, 300, 100, "Harald");
		this.objectMapper.registerObject("Gram", new Gram(176f, 688f));
		this.objectMapper.registerObject(Constants.PLAYER_OBJECT_NAME, this.player);
		this.objectMapper.registerObject("console", this.console);
		try {
			this.parser = Parser.getInstance(Constants.COMMAND_FILE);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a map of quests where the key is the quest name
	 * and the value is the quest description.
	 * @return	quests
	 */
	public Map<String, String> getQuests() {
		return this.player.getQuests();
	}
	
	/**
	 * Returns the player's equipped item.
	 * @return	the player's equipped item.
	 */
	public Item getPlayerEquippedItem(){
		return this.player.getEquippedItem();
	}
	
	public void questAccepted(int x, int y) {
		this.world.questAccepted(x, y);
	}

	/** 
	 * Get the items from the players inventory.
	 * Will not remove the items from the inventory.
	 * @return Items in players inventory.
	 */
	public List<Item> getItems() {
		return this.player.getItems();
	}
	
	public List<String> getLatestMessages(int amount) {
		return this.msgBuffer.getLatestMessages(amount);
	}

	/**
	 * Check if the inventory is toggled.
	 * @return True if the inventory is toggled else false.
	 */
	public boolean isInventoryToggled() {
		return this.player.isInventoryToggled();
	}

	/**
	 * Check if the questbook is toggled.
	 * @return True if questbook is toggled else false.
	 */
	public boolean isQuestBookToggled() {
		return this.player.isQuestBookToggled();
	}
	
	public boolean isConsoleToggled() {
		return this.console.isToggled();
	}

	/**
	 * Add a listener to the message buffer.
	 * @param listener Listener to add to the buffer.
	 */
	public void addMessageBufferListener(final PropertyChangeListener listener) {
		this.msgBuffer.addListener(listener);
	}
	
	/**
	 * Add a listener to the state handler.
	 * @param listener	listener to be added to the state handler.
	 */
	public void addStateHandlerListener(final PropertyChangeListener listener) {
		StateHandler.getInstance().addListeners(listener);
	}
	
	public Map<String, Integer> getPlayerQuestObjectives(String quest) {
		return this.player.getQuestObjectives(quest);
	}

	/**
	 * Get the players location.
	 * @return Players current position.
	 */
	public Point getPlayerLoc() {
		final int x = (int)this.player.getX();
		final int y = (int)this.player.getY();
		return new Point(x, y);
	}

	/**
	 * Set the players location
	 * @param p The location you want to put the player.
	 */
	public void setPlayerLoc(Point p) {
		this.player.setX(p.x);
		this.player.setY(p.y);
	}

	/**
	 * Check if a tile is blocked
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @return If location x,y is blocked return true else false
	 */
	public boolean isBlocked(final int x, final int y) {
		return this.world.isBlocked(x, y);
	}

	/**
	 * Get the map for a certain location in the world
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @return The map given by the coordinates
	 */
	public TiledMap getMap(int x, int y) {
		return this.world.getMap(x, y);
	}

	/**
	 * Initiate the map
	 * @param mapID Id on the map to initiate
	 * @param map Map to initiate
	 * @throws SlickException 
	 */
	public void initMap(int mapID, TiledMap map) throws SlickException {
		this.world.initMap(mapID, map);
	}

	/**
	 * Initiate a house
	 * @param x Coordinate x to initiate the house
	 * @param y Coordinate y to initiate the house
	 * @param mapID Map id to put the house
	 * @param map Map to put the house
	 */
	public void initHouse(int mapID, TiledMap map) {
		this.world.initHouse(mapID, map);
	}
	
	public void initHouseEntrances(int x, int y) {
		this.world.initHouseEntrances(x, y);
	}

	/**
	 * Checks if the current tile the player stands on
	 * contains a monster or not.
	 * @param x Position x to check if it has monster
	 * @param y Position y to check if it has monster
	 * @return True if the position x,y has monster on it else false
	 */
	public boolean hasMonster(final int x, final int y) {
		Battle b = this.world.hasMonster(x, y);
		
		if(b != null) {
			this.currentBattle = b;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Adds a list of items to the tile the player stands on.
	 * @param items	items to be added to the tile the player
	 * 				stands on.
	 */
	public void addItemsToPlayerTile(List<Item> items){
		this.world.addItemsToTile(this.getPlayerLoc().x, this.getPlayerLoc().y, items);
	}
	
	/**
	 * If a battle has been initiated, return the
	 * monster's current HP.
	 * @return	
	 */
	public int getBattleCurrentMonsterHP() {
		if(this.currentBattle == null) {
			return 0;
		}
		
		return this.currentBattle.getMonsterCurrentHP();
	}
	
	public int getBattleCurrentMonsterHPPercentage() {
		assert this.currentBattle != null;
		
		return this.currentBattle.getMonsterHealthPercentage();
	}
	
	public int getBattleMaxMonsterHP() {
		assert this.currentBattle != null;
		
		return this.currentBattle.getMonsterMaxHP();
	}
	
	public int getPlayerCurrentHP() {
		return this.player.getCurrentHP();
	}
	
	public int getPlayerMaxHP() {
		return this.player.getMaxHP();
	}
	
	public void destroyBattle(boolean drop) {
		assert this.currentBattle != null;
		
		this.currentBattle.destruct(drop);
		
		//this.currentBattle = null;
	}
	
	public boolean isActiveBattle() {
		return this.currentBattle != null;
	}
	
	public void monsterHitPlayer() {
		assert this.currentBattle != null;
		
		this.currentBattle.hitPlayer();
	}
	
	public boolean hasBattleEnded() {
		if(this.currentBattle == null) {
			return true;
		}
		
		return this.currentBattle.hasEnded();
	}
	
	public String getMonsterName() {
		assert this.currentBattle != null;
		
		return this.currentBattle.getMonsterName();
	}
	
	

	/**
	 * @param mapID
	 */
	public void setLoot(int mapID) {
		this.world.setLoot(getPlayerLoc().x, getPlayerLoc().y, mapID);
	}
	
	public void removeLoot(int mapID) {
		this.world.removeLoot(getPlayerLoc().x, getPlayerLoc().y, mapID);
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
		final Item i = this.player.retrieveItem(item);

		if(i != null) {
			final float x = this.player.getX();
			final float y = this.player.getY();

			if(!(this.world.addItemToTile(x, y, i))) {
				this.player.addItem(i);
			} else {
				this.objectMapper.removeObject(i.getItemName());
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
	public List<Item> getTileItems(final float x, final float y) {
		if(this.hasTileItems(x, y)) {
			return this.world.getTileItems(x, y);
		}

		return null;
	}
	
	/**
	 * Will loot the items from the given tile.
	 * If there were no items on the tile, null will be returned.
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Item> lootTileItems(final float x, final float y) {
		return this.world.retrieveTileItems(x, y);
	}

	/**Get the players direction
	 * @return The players current direction
	 */
	public Direction getPlayerDirection() {
		return this.player.getDirection();
	}
	
	public int getCurrentMap() {
		return this.world.getCurrentMap();
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

