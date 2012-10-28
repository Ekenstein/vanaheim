package gerk.vanaheim.init;

import gerk.vanaheim.gfx.framework.DisplayContainer;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				DisplayContainer.getInstance().run();
			}
		});
	}
}
