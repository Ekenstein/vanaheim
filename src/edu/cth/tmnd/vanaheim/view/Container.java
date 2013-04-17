package edu.cth.tmnd.vanaheim.view;

import java.awt.Color;
import java.awt.Point;
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
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.controller.Controller;
import edu.cth.tmnd.vanaheim.view.impl.IContainer;

/**
 * The main container that will draw all the
 * view states.
 * @author eken
 *
 */
public class Container extends BasicGame {

	private Controller controller;

	private TiledMap map = null;

	private Animation sprite, up, down, left, right;
	private float x = 100f, y = 100f;

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

	private Image coins;

	private TextField inputField;
	private String message = "";

	private AppGameContainer app;

	public Container(final String title) {
		super(title);
		controller = new Controller();
	}

	@Override
	public void render(final GameContainer container, final Graphics context) throws SlickException {
		//this.currentViewState.render(container, context);

		controller.getMap().render(0, 0);

		Point p = controller.getPlayerLoc();
		sprite.draw((float)p.x, (float)p.y);

		inputField.render(container, context);

		/*
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
		 */
	}

	@Override
	public void init(final GameContainer container) throws SlickException {

		coins = new Image("data/coins.png");

		try {
			map = new TiledMap("data/map.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		controller.initMap(map);

		Image [] movementUp = {new Image("data/wizUp1.png"), new Image("data/wizUp2.png")};
		Image [] movementDown = {new Image("data/wizDown1.png"), new Image("data/wizDown2.png")};
		Image [] movementLeft = {new Image("data/wizLeft1.png"), new Image("data/wizLeft2.png")};
		Image [] movementRight = {new Image("data/wizRight1.png"), new Image("data/wizRight2.png")};
		int [] duration = {300, 300};

		up = new Animation(movementUp, duration, false);
		down = new Animation(movementDown, duration, false);
		left = new Animation(movementLeft, duration, false);
		right = new Animation(movementRight, duration, false);

		sprite = right;

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



		/*
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
		 */
	}

	@Override
	public void update(final GameContainer container, final int delta) throws SlickException {
		Input input = container.getInput();
        if (input.isKeyDown(Input.KEY_UP))
        {
            sprite = up;
            if (!controller.isBlocked((int)x, (int)(y - delta * 0.1f)))
            {
                sprite.update(delta);
                // The lower the delta the slowest the sprite will animate.
                y -= delta * 0.1f;
            }
        }
        else if (input.isKeyDown(Input.KEY_DOWN))
        {
            sprite = down;
            if (!controller.isBlocked((int)x, (int)(y + 32f + delta * 0.1f)))
            {
                sprite.update(delta);
                y += delta * 0.1f;
            }
        }
        else if (input.isKeyDown(Input.KEY_LEFT))
        {
            sprite = left;
            if (!controller.isBlocked((int)(x - delta * 0.1f), (int)(y + 32f)))
            {
                sprite.update(delta);
                x -= delta * 0.1f;
            }
        }
        else if (input.isKeyDown(Input.KEY_RIGHT))
        {
            sprite = right;
            if (!controller.isBlocked((int)(x + 32f + delta * 0.1f), (int)(y + 32f)))
            {
                sprite.update(delta);
                x += delta * 0.1f;
            }
        }
		controller.setPlayerLoc(new Point((int)x, (int)y));
		//controller.checkTile((int)x, (int)y);
		controller.lootAll((int)x, (int)y);
	}

}