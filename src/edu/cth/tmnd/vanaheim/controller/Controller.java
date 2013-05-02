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

public class Controller {
	private final static File COMMAND_FILE = new File("data/commands");
	private final Player player;
	private final World world;
	private final ObjectMapper objectMapper;
	private final MessageBuffer msgBuffer;
	private Parser parser;

	public Controller() {
		this.world = new World();
		this.player = new Player(32f, 32f, 300, new Inventory(20), 100, "Harald");
		this.objectMapper = ObjectMapper.getInstance();
		this.msgBuffer = MessageBuffer.getInstance();

		try {
			this.parser = Parser.getInstance(COMMAND_FILE);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Quest> getQuests() {
		return this.player.getQuests();
	}
	
	public List<Item> getItems() {
		return this.player.getItems();
	}
	
	public boolean isInventoryToggled() {
		return this.player.isInventoryToggled();
	}
	
	public boolean isQuestBookToggled() {
		return this.player.isQuestBookToggled();
	}

	public void addMessageBufferListener(final PropertyChangeListener listener) {
		this.msgBuffer.addListener(listener);
	}

	public Point getPlayerLoc() {
		final int x = (int)this.player.getX();
		final int y = (int)this.player.getY();
		return new Point(x, y);
	}

	public void setPlayerLoc(final Point p) {
		this.player.setX(p.x);
		this.player.setY(p.y);
	}

	public boolean isBlocked(final int x, final int y) {
		return this.world.isBlocked(x, y);
	}

	public TiledMap getMap(int x, int y) {
		return this.world.getMap(x, y);
	}

	public void initMap(final TiledMap map) {
		this.world.initMap(map);
	}
	
	public void initHouse(int x, int y, TiledMap map) {
		this.world.initHouse(x, y, map);
	}

	public void checkTile(final int x, final int y) {
		this.world.checkTile(x, y);
	}

	public void changeTile(final int x, final int y) {
		this.world.changeTile(x, y);
	}

//	public void lootAll(final int x, final int y) {
//		this.world.lootAll(x, y);
//	}

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

	public boolean hasTileItems(final float x, final float y) {
		return this.world.hasTileItems(x, y);
	}

	public Inventory getTileItems(final float x, final float y) {
		if(this.hasTileItems(x, y)) {
			return this.world.getTileInventory(x, y);
		}

		return null;
	}

	public Direction getPlayerDirection() {
		return this.player.getDirection();
	}

	public void setPlayerDirection(final Direction d) {
		this.player.setDirection(d);
	}

	public void parseCommand(final String command) {
		this.parser.parse(command);
	}
}

