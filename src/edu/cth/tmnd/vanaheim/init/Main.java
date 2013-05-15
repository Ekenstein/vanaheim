package edu.cth.tmnd.vanaheim.init;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.view.StateHolder;

/**
 * Initialization of the game.<br />
 * Creates a new thread for the game itself
 * to run in. Will initiate the view framework.
 *
 * @author Gabriel Ekblad
 *
 */
public class Main {

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;

	public static void main(final String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new StateHolder());
			app.setDisplayMode(WIDTH, HEIGHT, true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
