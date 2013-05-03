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

public class ExploreState extends BasicGameState implements PropertyChangeListener {

	public static final int ID = 2;

	private Controller controller;

	private TiledMap map = null;
	private TiledMap questHouse = null;

	private Image fightScreenBg;
	private boolean fightActive = false;

	private Animation sprite, up, down, left, right;
	private float x = 400f, y = 400f;
	private int prevX, prevY, currX, currY;

	private Image inventory_bg;
	private Image inventory_title;
	private boolean showInventory = false;
	private final int[][] inventory = new int[4][6];
	private boolean isInventoryShown = false;

	private Image quests_title;
	private boolean isQuestsShown = false;

	private static final int POTION_GREEN = 1;
	private static final int DAGGER = 2;
	private static final int HELMET = 3;
	private Image potionGreenImg;
	private Image daggerImg;
	private Image helmetImg;

	private final Map<Integer, Image> itemIDMap = new HashMap<Integer, Image>();

	private Image coins;

	private TextField inputField;
	private String message = "";
	private String reply = "";
	private Image console;
	private boolean consoleToggled = false;

	TrueTypeFont titleFont;
	TrueTypeFont descriptionFont;

	private StateBasedGame game;

	public ExploreState(Controller controller) {
		this.controller = controller;
		this.controller.addMessageBufferListener(this);
	}

	public int getID() {
		return ID;
	}

	/**
	 * @see org.newdawn.slick.state.BasicGameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;

		titleFont = new TrueTypeFont(new Font("Arial", Font.BOLD, 22), false);
		descriptionFont = new TrueTypeFont(new Font("Arial", Font.PLAIN, 18), false);

		//Inventory
		inventory_bg = new Image("data/inventory_paper2.png");
		inventory_title = new Image("data/inventory_title.png");

		itemIDMap.put(0, new Image("data/coins.png"));
		itemIDMap.put(1, new Image("data/axe_steel.png"));
		itemIDMap.put(2, new Image("data/potion.png"));

		//Quest log
		quests_title = new Image("data/quests_title.png");

		container.setTargetFrameRate(120);
		coins = new Image("data/coins.png");

		try {
			map = new TiledMap("data/map.tmx");
			questHouse = new TiledMap("data/questHouse.tmx");
		} catch (final SlickException e) {
			e.printStackTrace();
		}

		controller.initMap(0, map);
		controller.initHouse(15, 18, 1, questHouse);

		fightScreenBg = new Image("data/fightScreenBg.png");

		final Image [] movementUp = {new Image("data/wizUp1.png"), new Image("data/wizUp2.png")};
		final Image [] movementDown = {new Image("data/wizDown1.png"), new Image("data/wizDown2.png")};
		final Image [] movementLeft = {new Image("data/wizLeft1.png"), new Image("data/wizLeft2.png")};
		final Image [] movementRight = {new Image("data/wizRight1.png"), new Image("data/wizRight2.png")};
		final int [] duration = {300, 300};

		up = new Animation(movementUp, duration, false);
		down = new Animation(movementDown, duration, false);
		left = new Animation(movementLeft, duration, false);
		right = new Animation(movementRight, duration, false);

		sprite = right;
		
		console = new Image("data/inventory_paper.png");

		inputField = new TextField(container,
				new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 32), false),
				384, 736, 256, 32, new ComponentListener() {

			@Override
			public void componentActivated(final AbstractComponent source) {
				message = inputField.getText();
				ExploreState.this.controller.parseCommand(message);
				if (message.equals("show inventory")) {
					showInventory = true;
				} else if (message.equals("hide inventory")) {
					showInventory = false;
				} else if (message.equals("drop item")) {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							final int item = inventory[i][j];
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
							final int item = inventory[i][j];
							if (item == 0) {
								final Random rand = new Random();
								final int a = rand.nextInt(4);
								inventory[i][j] = a;
								inputField.setText("");
								return;
							}
						}
					}
				} else if (message.equals("talk to thalia")) {
					//TODO Can't talk to Thalia all over the map!
					consoleToggled = true;
					reply = "Thalia: My husband got lost in a battle! Pleeeease help me find him.";
				}
				inputField.setText("");
			}
		});
		inputField.setFocus(true);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		
		int x = controller.getPlayerLoc().x;
		int y = controller.getPlayerLoc().y;

		if (fightActive) {
			context.drawImage(fightScreenBg, 0, 0);
		} else {
			controller.getMap((int)x, (int)y).render(0, 0);
			Point p = controller.getPlayerLoc();
			sprite.draw(x, y);
		}
		
		int maxReplyWidth = 376;
		List<String> consoleMessages = new ArrayList<String>();
		String replyText = "";
		if (consoleToggled) {
			context.drawImage(console, 320, 464);
			String[] strArray = reply.split(" ");
			for (int i = 0; i < strArray.length; i++) {
				if (descriptionFont.getWidth(replyText + " " + strArray[i]) > maxReplyWidth) {
					consoleMessages.add(replyText);
					replyText = "";
				}
				replyText += strArray[i] + " ";
			}
			consoleMessages.add(replyText);
			for (int i = 0; i < consoleMessages.size(); i++) {
				context.drawString(consoleMessages.get(i), (1024-console.getWidth())/2+8, 768-console.getHeight() - 24 + 16*i);
			}
		}
		
		context.setColor(Color.white);
		inputField.render(container, context);

		if (controller.isInventoryToggled()) {
			context.drawImage(inventory_bg, 1024-inventory_bg.getWidth(), 768-inventory_bg.getHeight());
			context.drawImage(inventory_title, 1024-inventory_bg.getWidth()-64, 768-inventory_bg.getHeight() - inventory_title.getHeight()/2);

			List<Item> items = controller.getItems();
			for (int i = 0; i < items.size(); i++) {
				context.drawImage(itemIDMap.get(items.get(i).getItemID()), 1024-inventory_bg.getWidth()+16 + i * 64, 768-inventory_bg.getHeight()+16 + i / 4 * 64);
			}
		}

		int maxQuestWidth = 248;
		List<String> description = new ArrayList<String>();
		String curText = "";

		context.setColor(org.newdawn.slick.Color.black);

		if (controller.isQuestBookToggled()) {
			context.drawImage(inventory_bg, 0, 768-inventory_bg.getHeight());
			context.drawImage(quests_title, -64, 768-inventory_bg.getHeight()-quests_title.getHeight()/2);

			Map<String, Quest> quests = controller.getQuests();
			for (String questName : quests.keySet()) {
				String str = quests.get(questName).getDescription();
				String[] strArray = str.split(" ");
				for (int i = 0; i < strArray.length; i++) {
					if (descriptionFont.getWidth(curText + " " + strArray[i]) > maxQuestWidth) {
						description.add(curText);
						curText = "";
					}
					curText += strArray[i] + " ";
				}
				description.add(curText);
				int titleLength = titleFont.getWidth(questName);
				context.setFont(titleFont);
				context.drawString(questName, (256 - titleLength) / 2, 768 - inventory_bg.getHeight() + 32);
				context.setFont(descriptionFont);
				for (int i = 0; i < description.size(); i++) {
					context.drawString(description.get(i), 8, 768 - inventory_bg.getHeight() + 64 + i * 16);
				}
			}
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		prevX = currX;
		prevY = currY;
		final Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_UP))
		{
			sprite = up;
			if (!controller.isBlocked((int)x, (int)(y - delta * 0.1f)))
			{
				sprite.update(delta);
				// The lower the delta the slowest the sprite will animate.
				y -= delta * 0.1f;
			}
			consoleToggled = false;
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			sprite = down;
			if (!controller.isBlocked((int)x, (int)(y + 32f + delta * 0.1f)))
			{
				sprite.update(delta);
				y += delta * 0.1f;
			}
			consoleToggled = false;
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			sprite = left;
			if (!controller.isBlocked((int)(x - delta * 0.1f), (int)(y + 32f)))
			{
				sprite.update(delta);
				x -= delta * 0.1f;
			}
			consoleToggled = false;
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			sprite = right;
			if (!controller.isBlocked((int)(x + 32f + delta * 0.1f), (int)(y + 32f)))
			{
				sprite.update(delta);
				x += delta * 0.1f;
			}
			consoleToggled = false;
		}
		currX = (int)Math.floor(x / 32);
		currY = (int)Math.floor(y / 32);
		controller.setPlayerLoc(new Point((int)x, (int)y));
		if (prevX != currX || prevY != currY) {
			if (controller.hasMonster(currX, currY)) {
				FightState.enemyAttackTimer.start();
				game.enterState(FightState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
			}
		}
		//controller.lootAll((int)x, (int)y);
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			game.enterState(Menu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}