package edu.cth.tmnd.vanaheim.view;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.AppletGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.view.impl.IContainer;
import edu.cth.tmnd.vanaheim.view.states.impl.ViewState;

/**
 * The main container that will draw all the
 * view states.
 * @author eken
 *
 */
public class Container extends BasicGame implements IContainer {

	private TiledMap map = null;

	private Animation sprite, up, down, left, right;
	private float x = 34f, y = 34f;

	private Image inventory_bg;
	private boolean showInventory = false;
	private int[][] inventory = new int[4][6];

	private static final int POTION_GREEN = 1;
	private static final int DAGGER = 2;
	private static final int HELMET = 3;
	private Image potionGreenImg;
	private Image daggerImg;
	private Image helmetImg;

	private Map<Integer, Image> items = new HashMap<Integer, Image>();

	private TextField inputField;
	private String message = "";

	private AppGameContainer app;

	private ViewState currentViewState;

	public Container(final String title) {
		super(title);
	}

	@Override
	public ViewState nextState() {
		return currentViewState;
		// TODO Auto-generated method stub

	}

	@Override
	public ViewState prevState() {
		return currentViewState;
		// TODO Auto-generated method stub

	}

	@Override
	public void render(final GameContainer container, final Graphics context) throws SlickException {
		//this.currentViewState.render(container, context);
		map.render(0, 0);
		if (showInventory == true) {
			context.drawImage(inventory_bg, 256, 350);
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					if (inventory[i][j] != 0) {
						context.drawImage(items.get(inventory[i][j]), 272 + j * 80, 366 + i * 80);
					}
				}
			}
		}
		inputField.render(container, context);
	}

	@Override
	public void init(final GameContainer container) throws SlickException {

		//Init the tiled map
		try {
			map = new TiledMap("data/map.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		//Init the inventory and all possible items
		inventory_bg = new Image("data/inventory_paper.png");
		potionGreenImg = new Image("data/potion_green.png");
		daggerImg = new Image("data/dagger.png");
		helmetImg = new Image("data/helmet.png");

		items.put(POTION_GREEN, potionGreenImg);
		items.put(DAGGER, daggerImg);
		items.put(HELMET, helmetImg);

		inventory[0][0] = 1;
		inventory[0][1] = 2;
		inventory[0][2] = 2;
		inventory[0][3] = 3;
		inventory[0][4] = 1;

		inputField = new TextField(container, 
				new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 32), false), 
				384, 704, 256, 64, new ComponentListener() {
			
			//Listen for different messages. Message is supposed to be sent to a controller and actions are
			//supposed to be performed whenever the GUI receives new information.
			public void componentActivated(AbstractComponent source) {
				message = inputField.getText();
				if (message.equals("show inventory")) {
					showInventory = true;
				} else if (message.equals("hide inventory")) {
					showInventory = false;
				} else if (message.equals("drop item")) {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							int item = inventory[i][j];
							if (item == 1 || item == 2 || item == 3) {
								inventory[i][j] = 0;
								inputField.setText("");
								return;
							}
						}
					}
				} else if (message.equals("loot")) {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							int item = inventory[i][j];
							if (item == 0) {
								Random rand = new Random();
								int a = rand.nextInt(4);
								inventory[i][j] = a;
								inputField.setText("");
								return;
							}
						}
					}
				}
				inputField.setText("");
			}
		});
		inputField.setFocus(true);
	}

	@Override
	public void update(final GameContainer container, final int delta) throws SlickException {
		//System.out.println("Åke");
	}

}