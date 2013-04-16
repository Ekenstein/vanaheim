package edu.cth.tmnd.vanaheim.controller;

import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.Trie.Trie;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.world.World;



public class Controller {
	private Player player;
	private Trie lexicon;
	private World world;
	
	public Controller() {
		world = new World();
		player = new Player();
	}
	
	public void init(final GameContainer container) throws SlickException {
		world.init(container);
		player.init(container);
	}
	
	public void render(GameContainer container, Graphics context) throws SlickException {
		//this.player.render(container, context);
		world.render(container, context);
		player.render(container, context);
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		player.update(container, delta);
		Point p = player.getLoc();
		int xPos = (int) Math.floor(p.x / 32);
		int yPos = (int) Math.floor(p.y / 32);
		world.checkTile(xPos, yPos);
	}
}

