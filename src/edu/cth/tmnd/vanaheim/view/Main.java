package edu.cth.tmnd.vanaheim.view;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.cth.tmnd.vanaheim.controller.Controller;

public class Main extends StateBasedGame {
	
	private Controller controller;

	public Main() {
		super("Vanaheim");
		try {
			this.controller = Controller.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initStatesList(GameContainer container) {
		addState(new Menu());
		addState(new ExploreState(controller));
		addState(new FightState(controller));
	}

	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new Main());
			container.setDisplayMode(1024,768,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}