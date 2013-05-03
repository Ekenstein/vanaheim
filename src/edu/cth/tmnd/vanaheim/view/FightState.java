package edu.cth.tmnd.vanaheim.view;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.Timer;

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

	private Controller controller;

	private Image fightScreenBg;

	private Image inventory_bg;
	private Image inventory_title;
	private final int[][] inventory = new int[4][6];
	
	private Image barForegroundImage;
	private Image healthBarImage;
	private Image attackTimerBarImage;
	private Image combatLogImage;
	private Image playerImage;
	private Image spiderImage;

	private final Map<Integer, Image> itemIDMap = new HashMap<Integer, Image>();

	private TextField inputField;
	private String message = "";

	private TrueTypeFont titleFont;
	private TrueTypeFont descriptionFont;
	
	public static Timer enemyAttackTimer;
	private int startTime = 10;
	
	private int health = 100;
	private int enemyHealth = 100;
	
	private boolean fightEnded = false;
	
	private String enemyLog = "";
	private String playerLog = "";

	private StateBasedGame game;

	public FightState(Controller controller) {
		this.controller = controller;
		this.controller.addMessageBufferListener(this);
		enemyAttackTimer = new Timer(1000, new CountdownTimerListener());
	}
	
	class CountdownTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	startTime--;
            if (startTime == 0) {
                //TODO Opponent is attacking. Call some method!
            	enemyLog += "Spider hits you for 20 damage!\n";
            	health -= 20;
            	if (health <= 0) {
            		System.out.println("You died");
            		enemyLog += "You died :(";
            		enemyAttackTimer.stop();
            		game.enterState(ExploreState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            	}
                startTime = 10;
            }
        }
    }

	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;

		titleFont = new TrueTypeFont(new Font("Arial", Font.BOLD, 24), false);
		descriptionFont = new TrueTypeFont(new Font("Arial", Font.PLAIN, 18), false);

		//Inventory
		inventory_bg = new Image("data/inventory_paper2.png");
		inventory_title = new Image("data/inventory_title.png");
		
		itemIDMap.put(0, new Image("data/coins.png"));
		itemIDMap.put(1, new Image("data/axe_steel.png"));
		itemIDMap.put(2, new Image("data/potion.png"));

		container.setTargetFrameRate(120);

		fightScreenBg = new Image("data/fightScreenBg.png");
		
		barForegroundImage = new Image("data/barForeground.png");
		healthBarImage = new Image("data/healthBar.png");
		attackTimerBarImage = new Image("data/attackTimerBar.png");
		combatLogImage = new Image("data/combatLogBg.png");
		playerImage = new Image("data/female_wizard.png");
		spiderImage = new Image("data/ugly_spider.png");

		inputField = new TextField(container,
				new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 32), false),
				384, 736, 256, 32, new ComponentListener() {

			@Override
			public void componentActivated(final AbstractComponent source) {
				message = inputField.getText();
				FightState.this.controller.parseCommand(message);
				if (message.equals("hit")) {
					enemyHealth -= 50;
				} else if (message.equals("hide inventory")) {
					//showInventory = false;
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
					//consoleToggled = true;
					//reply = "Thalia: My husband got lost in a battle! Pleeeease help me find him.";
				}
				inputField.setText("");
			}
		});
		inputField.setFocus(true);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(fightScreenBg, 0, 0);
		
		context.drawImage(playerImage, 100, 200);
		context.drawImage(spiderImage, 732, 350);
		
		//Player health bar
		healthBarImage.draw(130, 458, 2*health, 16);
		context.drawImage(barForegroundImage, 100, 450);
		
		//Enemy health bar
		healthBarImage.draw(698, 458, 2*enemyHealth, 16);
		context.drawImage(barForegroundImage, 668, 450);
		
		//Enemy attack timer
		attackTimerBarImage.draw(698, 490, 20*startTime, 16);
		context.drawImage(barForegroundImage, 668, 482);
		
		context.drawImage(combatLogImage, 0, 564);
		context.drawString(enemyLog, 16, 580);
		context.drawImage(combatLogImage, 736, 564);
		context.drawString(playerLog, 752, 580);
		
		context.setColor(Color.white);
		inputField.render(container, context);
		
		if (controller.isInventoryToggled()) {
			context.drawImage(inventory_bg, 384, 555);
			context.drawImage(inventory_title, 320, 507);

			List<Item> items = controller.getItems();
			for (int i = 0; i < items.size(); i++) {
				context.drawImage(itemIDMap.get(items.get(i).getItemID()), 400 + i * 64, 571 + i / 4 * 64);
			}
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (enemyHealth <= 0) {
			controller.setLoot(0);
			enemyAttackTimer.stop();
			enemyHealth = 100;
			game.enterState(ExploreState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		
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