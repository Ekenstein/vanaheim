package edu.cth.tmnd.vanaheim.view.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CrossStateTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MenuState extends BasicGameState {
	/** The ID given to this state */
	public static final int ID = 1;
	/** The game holding this state */
	private StateBasedGame game;
	
	private Music titleSong;
	
	private Image background;

	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		
		background = new Image("data/menuBg.png");
		titleSong = new Music("data/sfx/Title.ogg");
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(background, 0, 0);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (!titleSong.playing()) {
			titleSong.play();
			titleSong.setVolume(1f);
		}
	}

	public void keyReleased(int key, char c) {

		if (key == Input.KEY_1) {
			GameState target = game.getState(ExploreState.ID);

			final long start = System.currentTimeMillis();
			CrossStateTransition t = new CrossStateTransition(target) {            
				public boolean isComplete() {
					return (System.currentTimeMillis() - start) > 2000;
				}

				public void init(GameState firstState, GameState secondState) {
				}
			};
			titleSong.stop();
			game.enterState(ExploreState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}
}