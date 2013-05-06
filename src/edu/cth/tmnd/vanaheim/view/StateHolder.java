package edu.cth.tmnd.vanaheim.view;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import edu.cth.tmnd.vanaheim.controller.Controller;
import edu.cth.tmnd.vanaheim.view.states.ExploreState;
import edu.cth.tmnd.vanaheim.view.states.FightState;
import edu.cth.tmnd.vanaheim.view.states.LoadingState;
import edu.cth.tmnd.vanaheim.view.states.MenuState;

public class StateHolder extends StateBasedGame {
	
	private Controller controller;

	public StateHolder() {
		super("Vanaheim");
		try {
			this.controller = Controller.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer container) {
		addState(new LoadingState());
		addState(new MenuState());
		addState(new ExploreState(controller));
		addState(new FightState(controller));
	}
}