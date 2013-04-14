package edu.cth.tmnd.vanaheim.view;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.view.impl.IDisplayContainer;
import edu.cth.tmnd.vanaheim.view.states.impl.ViewState;

/**
 * The main container that will draw all the
 * view states.
 * @author eken
 *
 */
public class DisplayContainer extends BasicGame implements IDisplayContainer {

	private ViewState currentViewState;

	public DisplayContainer(final String title) {
		super(title);
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
	public void render(final GameContainer container, final Graphics context) throws SlickException {
		this.currentViewState.render(container, context);

	}

	@Override
	public void init(final GameContainer container) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(final GameContainer container, final int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

}
