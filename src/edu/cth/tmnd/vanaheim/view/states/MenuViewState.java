package edu.cth.tmnd.vanaheim.view.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import edu.cth.tmnd.vanaheim.view.impl.IDisplayContainer;
import edu.cth.tmnd.vanaheim.view.states.impl.ViewState;

/**
 * MenuViewState handles the drawing of the menu and are also
 * in the role of containing other view states. That is,
 * MenuViewState holds several different view states such as Settings
 * and Game
 * @author Gabriel Ekblad
 *
 */
public final class MenuViewState extends ViewState implements IDisplayContainer {

	public MenuViewState(final IDisplayContainer parent) {
		super(parent);
	}

	@Override
	public void nextState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void prevState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(final GameContainer container, final Graphics context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(final GameContainer container) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(final GameContainer container, final int delta) {
		// TODO Auto-generated method stub

	}

}
