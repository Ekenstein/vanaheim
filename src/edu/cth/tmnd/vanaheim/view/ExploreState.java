package edu.cth.tmnd.vanaheim.view;

import java.awt.Font;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import edu.cth.tmnd.vanaheim.controller.Controller;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class ExploreState extends BasicGameState implements PropertyChangeListener {

	private StateBasedGame game;

	public static final int ID = 2;

	private Controller controller;

	//Tiled maps
	private TiledMap map = null;
	private TiledMap questHouse = null;

	//Player info
	private Animation sprite, up, down, left, right;
	private int prevX, prevY, currX, currY;
	private float x = 400f, y = 400f;

	//Inventory
	private Image inventory_bg;
	private Image inventory_title;
	private final Map<Integer, Image> itemIDMap = new HashMap<Integer, Image>();

	//Quests
	private Image quests_title;

	//Input field
	private TextField inputField;

	//Console
	private String message = "";
	private String reply = "";
	private Image console;
	private boolean consoleToggled = false;

	//Fonts
	TrueTypeFont titleFont;
	TrueTypeFont descriptionFont;

	public ExploreState(Controller controller) {
		this.controller = controller;
		this.controller.addMessageBufferListener(this);
	}

	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		container.setTargetFrameRate(120);

		//Init fonts
		titleFont = new TrueTypeFont(new Font("Arial", Font.BOLD, 22), false);
		descriptionFont = new TrueTypeFont(new Font("Arial", Font.PLAIN, 18), false);

		//Init images
		inventory_bg = new Image("data/inventory_paper2.png");
		inventory_title = new Image("data/inventory_title.png");
		quests_title = new Image("data/quests_title.png");
		console = new Image("data/inventory_paper.png");

		//Init item map
		itemIDMap.put(0, new Image("data/coins.png"));
		itemIDMap.put(1, new Image("data/axe_steel.png"));
		itemIDMap.put(2, new Image("data/potion.png"));

		//Init tiled maps
		try {
			map = new TiledMap("data/map.tmx");
			questHouse = new TiledMap("data/questHouse.tmx");
		} catch (final SlickException e) {
			e.printStackTrace();
		}

		//Let the world class initialize tiled maps as well
		controller.initMap(0, map);
		controller.initHouse(15, 18, 1, questHouse);

		//Player animation
		final Image [] movementUp = {new Image("data/wizUp1.png"), new Image("data/wizUp2.png")};
		final Image [] movementDown = {new Image("data/wizDown1.png"), new Image("data/wizDown2.png")};
		final Image [] movementLeft = {new Image("data/wizLeft1.png"), new Image("data/wizLeft2.png")};
		final Image [] movementRight = {new Image("data/wizRight1.png"), new Image("data/wizRight2.png")};
		final int [] duration = {300, 300};
		up = new Animation(movementUp, duration, false);
		down = new Animation(movementDown, duration, false);
		left = new Animation(movementLeft, duration, false);
		right = new Animation(movementRight, duration, false);

		//Animation to use
		sprite = right;

		//Init input field
		inputField = new TextField(container,
				new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 32), false),
				384, 736, 256, 32, new ComponentListener() {

			@Override
			public void componentActivated(final AbstractComponent source) {
				message = inputField.getText();
				//TODO Handle the message
				inputField.setText("");
			}
		});
		inputField.setFocus(true);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		//Player location
		int x = controller.getPlayerLoc().x;
		int y = controller.getPlayerLoc().y;
		
		//Check which map is active and render it
		controller.getMap((int)x, (int)y).render(0, 0);
		
		//Draw the player sprite
		sprite.draw(x, y);
		
		//Draw the console and its messages
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
		
		//Render input field
		context.setColor(Color.white);
		inputField.render(container, context);
		
		//Text in the inventory and quest log is black
		context.setColor(Color.black);
		
		//Draw inventory
		if (controller.isInventoryToggled()) {
			context.drawImage(inventory_bg, 1024-inventory_bg.getWidth(), 768-inventory_bg.getHeight());
			context.drawImage(inventory_title, 1024-inventory_bg.getWidth()-64, 768-inventory_bg.getHeight() - inventory_title.getHeight()/2);

			List<Item> items = controller.getItems();
			for (int i = 0; i < items.size(); i++) {
				context.drawImage(itemIDMap.get(items.get(i).getItemID()), 1024-inventory_bg.getWidth()+16 + i * 64, 768-inventory_bg.getHeight()+16 + i / 4 * 64);
			}
		}
		
		//Draw the quest log and its quests
		int maxQuestWidth = 248;
		List<String> description = new ArrayList<String>();
		String curText = "";
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