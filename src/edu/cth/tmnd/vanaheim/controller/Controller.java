package edu.cth.tmnd.vanaheim.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.Trie.Trie;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.world.World;



public class Controller {
	private Player player;
	private Trie lexicon;
	private World currentWorld;
	private World[] worlds;
	
	public void render(GameContainer container, Graphics context) throws SlickException {
		this.player.render(container, context);
	}
	
	public void update(GameContainer container) throws SlickException {
		this.player.update(container);
	}
}

