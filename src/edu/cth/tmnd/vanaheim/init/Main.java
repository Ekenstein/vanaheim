package edu.cth.tmnd.vanaheim.init;

import java.awt.EventQueue;

import edu.cth.tmnd.vanaheim.view.DisplayContainer;

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
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new DisplayContainer("Vanaheim");
			}
		});
	}
}
