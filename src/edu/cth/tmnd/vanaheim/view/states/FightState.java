package edu.cth.tmnd.vanaheim.view.states;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import edu.cth.tmnd.vanaheim.controller.Controller;

public class FightState extends BasicGameState implements PropertyChangeListener {

	private StateBasedGame game;

	public static final int ID = 3;

	private Controller controller;

	//Background image
	private Image fightScreenBg;
	
	private Music battleSong;
	private Sound slashSound;

	//Inventory
	private Image inventory_bg;
	private Image inventory_title;
	private final Map<Integer, Image> itemIDMap = new HashMap<Integer, Image>();

	//Fight related images
	private Image barForegroundImage;
	private Image healthBarImage;
	private Image attackTimerBarImage;
	private Image combatLogImage;
	private Image playerImage;
	private Image spiderImage;

	//Command field
	private TextField inputField;
	private String message = "";

	//Timer used by the opponent to determine when to attack
	public static Timer enemyAttackTimer;
	private int startTime = 10;

	//Healths
	private int playerHealth;
	private int enemyHealth;

	//Logs
	private String enemyLog = "";
	private String playerLog = "";
	
	//Fonts
	private TrueTypeFont titleFont;
	private TrueTypeFont descriptionFont;

	public FightState(Controller controller) {
		this.controller = controller;
		this.controller.addMessageBufferListener(this);
		enemyAttackTimer = new Timer(1000, new CountdownTimerListener());
	}

	class CountdownTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			startTime--;
			if (startTime == 0) {
				controller.monsterHitPlayer();
				if (controller.hasBattleEnded()) {
					System.out.println("Battle ended");
					enemyAttackTimer.stop();
					if(controller.battleWinner() == "PLAYER"){
						controller.addItemToPlayerTile(controller.getBattleCurrentMonsterItem());
					}
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
		container.setTargetFrameRate(120);
		
		//battleSong = new Music("data/sfx/Battle_Theme.ogg");
		//slashSound = new Sound("data/sfx/Kill_Enemy.ogg");
		
		//Init fonts
		titleFont = new TrueTypeFont(new Font("Arial", Font.BOLD, 22), false);
		descriptionFont = new TrueTypeFont(new Font("Arial", Font.PLAIN, 18), false);

		//Init images
		inventory_bg = new Image("data/inventory_paper2.png");
		inventory_title = new Image("data/inventory_title.png");
		fightScreenBg = new Image("data/fightScreenBg.png");
		barForegroundImage = new Image("data/barForeground.png");
		healthBarImage = new Image("data/healthBar.png");
		attackTimerBarImage = new Image("data/attackTimerBar.png");
		combatLogImage = new Image("data/combatLogBg.png");
		playerImage = new Image("data/female_wizard.png");
		spiderImage = new Image("data/ugly_spider.png");

		//Init item map
		itemIDMap.put(0, new Image("data/coins.png"));
		itemIDMap.put(1, new Image("data/axe_steel.png"));
		itemIDMap.put(2, new Image("data/potion.png"));
		
		//Init input field
		inputField = new TextField(container,
				new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 32), false),
				384, 736, 256, 32, new ComponentListener() {

			@Override
			public void componentActivated(final AbstractComponent source) {
				message = inputField.getText();
				controller.parseCommand(message);
				inputField.setText("");
				//slashSound.play();
			}
		});
		inputField.setFocus(true);
	}

	
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		
		playerHealth = controller.getPlayerCurrentHP();
		enemyHealth = controller.getBattleCurrentMonsterHP();
		
		context.setFont(descriptionFont);
		
		//Draw the background
		context.drawImage(fightScreenBg, 0, 0);

		//Draw contestants
		context.drawImage(playerImage, 100, 200);
		context.drawImage(spiderImage, 732, 350);

		//Draw health and attack bars
		healthBarImage.draw(130, 458, 2*playerHealth, 16);
		context.drawImage(barForegroundImage, 100, 450);
		healthBarImage.draw(698, 458, 2*enemyHealth, 16);
		context.drawImage(barForegroundImage, 668, 450);
		attackTimerBarImage.draw(698, 490, 20*startTime, 16);
		context.drawImage(barForegroundImage, 668, 482);

		//Draw logs
		context.drawImage(combatLogImage, 0, 564);
		context.drawString(enemyLog, 16, 580);
		context.drawImage(combatLogImage, 736, 564);
		context.drawString(playerLog, 752, 580);

		//Render input field
		context.setColor(Color.white);
		inputField.render(container, context);
		
		//Draw inventory
		context.drawImage(inventory_bg, 384, 555);
		context.drawImage(inventory_title, 320, 507);
//		List<Item> items = controller.getItems();
//		for (int i = 0; i < items.size(); i++) {
//			context.drawImage(itemIDMap.get(items.get(i).getItemID()), 400 + i * 64, 571 + i / 4 * 64);
//		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		/*if (!battleSong.playing()) {
			battleSong.play();
			battleSong.setVolume(0f);
			battleSong.fade(1000, 1f, false);
		}
		*/
	}
	
	public void enter(GameContainer gc , StateBasedGame sbg)
            throws SlickException {
		inputField.setText("");
		inputField.setFocus(true);
    }

	public void keyReleased(int key, char c) {
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}
}