package edu.cth.tmnd.vanaheim.view;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CrossStateTransition;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends BasicGameState {
	/** The ID given to this state */
	public static final int ID = 1;
	/** The game holding this state */
	private StateBasedGame game;

	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.white);
		g.drawString("Welcome to Vanaheim. The most awesome medieval game ever created!", 100, 100);
		g.drawString("Press 1 to start a new game", 150, 300);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
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

			game.enterState(ExploreState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	}
}