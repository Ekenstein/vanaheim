package edu.cth.tmnd.vanaheim.view;

import java.awt.Font;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.AppletGameContainer.ContainerPanel;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tests.xml.Entity;
import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.controller.Controller;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class FightState extends BasicGameState implements PropertyChangeListener {

	public static final int ID = 3;

	private final Controller controller;

	private Image fightScreenBg;

	private Image inventory_bg;
	private Image inventory_title;
	private final int[][] inventory = new int[4][6];

	private final Map<Integer, Image> itemIDMap = new HashMap<Integer, Image>();

	private TextField inputField;
	private String message = "";

	TrueTypeFont titleFont;
	TrueTypeFont descriptionFont;

	private StateBasedGame game;

	public FightState() {
		controller = new Controller();
		this.controller.addMessageBufferListener(this);
	}

	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;

		titleFont = new TrueTypeFont(new Font("Arial", Font.BOLD, 24), false);
		descriptionFont = new TrueTypeFont(new Font("Arial", Font.PLAIN, 18), false);

		//Inventory
		inventory_bg = new Image("data/inventory_paper.png");
		inventory_title = new Image("data/inventory_title.png");
		
		itemIDMap.put(0, new Image("data/coins.png"));
		itemIDMap.put(1, new Image("data/axe_steel.png"));
		itemIDMap.put(2, new Image("data/potion.png"));

		container.setTargetFrameRate(120);

		fightScreenBg = new Image("data/fightScreenBg.png");

		inputField = new TextField(container,
				new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 32), false),
				384, 704, 256, 64, new ComponentListener() {

			@Override
			public void componentActivated(final AbstractComponent source) {
				message = inputField.getText();
				FightState.this.controller.parseCommand(message);
				inputField.setText("");
			}
		});
		inputField.setFocus(true);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(fightScreenBg, 0, 0);

		inputField.render(container, context);

		if (controller.isInventoryToggled()) {
			context.drawImage(inventory_bg, 640, 496);
			context.drawImage(inventory_title, 640, 464);

			List<Item> items = controller.getItems();
			for (int i = 0; i < items.size(); i++) {
				context.drawImage(itemIDMap.get(items.get(i).getItemID()), 656 + i * 64, 512 + i / 4 * 64);
			}
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			game.enterState(Menu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}
}