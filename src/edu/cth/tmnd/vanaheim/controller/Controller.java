package edu.cth.tmnd.vanaheim.controller;

import java.awt.Point;
import java.util.StringTokenizer;

import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.ObjectFactory;
import edu.cth.tmnd.vanaheim.model.ObjectFactory.Type;
import edu.cth.tmnd.vanaheim.model.Trie.Trie;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature.Direction;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.world.World;

public class Controller {
	private final static int NODE_WORDS_LIMIT = 1;
	private final Player player;
	private Trie lexicon;
	private final World world;
	private final ObjectFactory objectMapper;

	public Controller() {
		this.world = new World();
		this.player = new Player(32f, 32f, 300, new Inventory(20), 100, "Harald");
		this.objectMapper = ObjectFactory.getInstance();
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

	public TiledMap getMap() {
		return this.world.getMap();
	}

	public void initMap(final TiledMap map) {
		this.world.initMap(map);
	}

	public void checkTile(final int x, final int y) {
		this.world.checkTile(x, y);
	}

	public void changeTile(final int x, final int y) {
		this.world.changeTile(x, y);
	}

	public void lootAll(final int x, final int y) {
		this.world.lootAll(x, y);
	}

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

	public void interpretCommand(final String command) {

		final StringTokenizer st = new StringTokenizer(command);

		final String currentNode = null;
		final StringBuilder sb = new StringBuilder();
		boolean nodeReached = false;
		int wordCount = 0;

		while(st.hasMoreTokens()) {
			final String token = st.nextToken();
			wordCount++;
			if(!nodeReached) {
				// if a node hasn't been reached,
				// check if the current token is longer than
				// NODE_WORDS_LIMIT. If it is, it is a parse error.
				if(wordCount > NODE_WORDS_LIMIT) {
					return;
				}

				// check if the current token is an object.
				// if it is, it is a parse error. There shouldn't
				// be a node that is related to an object
				if(this.objectMapper.isRegistered(token) != Type.UNKNOWN) {
					return;
				}

				// So we have a token that is unknown and is <= NODE_wORDS_LIMIT
				// We need to check if there exist a node in the trie tree that contains
				// this token.

				if(this.lexicon.hasNode(token)) {
					nodeReached = true;
				} else {
					// parse error.
					return;
				}
			}
		}
	}
}

