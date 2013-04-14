package edu.cth.tmnd.vanaheim.view.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.controller.Controller;
import edu.cth.tmnd.vanaheim.view.impl.IDisplayContainer;
import edu.cth.tmnd.vanaheim.view.states.impl.ViewState;

public final class GameViewState extends ViewState {

	private Controller controller;

	public GameViewState(final IDisplayContainer parent) {
		super(parent);
	}

	@Override
	public void render(final GameContainer container, final Graphics context) throws SlickException {
		this.controller.render(container, context);

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
