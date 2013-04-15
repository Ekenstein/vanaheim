package edu.cth.tmnd.vanaheim.init;

import java.awt.EventQueue;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.view.Container;
import edu.cth.tmnd.vanaheim.view.Container;

/**
 * Initialization of the game.<br />
 * Creates a new thread for the game itself
 * to run in. Will initiate the view framework.
 *
 * @author Gabriel Ekblad
 *
 */
public class Main {
	public static void main(final String[] args) {
		try{
			AppGameContainer app = new AppGameContainer(new Container("Vanaheim"));
			app.setDisplayMode(512, 512, false);
			app.start();
		}
		
		catch(SlickException e){
			e.printStackTrace();
		}
	}
}
