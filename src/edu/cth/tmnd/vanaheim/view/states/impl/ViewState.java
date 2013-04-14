package edu.cth.tmnd.vanaheim.view.states.impl;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.view.impl.IDisplayContainer;

public abstract class ViewState {
	protected IDisplayContainer parent;

	public ViewState(final IDisplayContainer containerParent) {
		this.parent = containerParent;
	}

	public abstract void render(GameContainer container, Graphics context) throws SlickException;
	public abstract void init(GameContainer container) throws SlickException;
	public abstract void update(GameContainer container, int delta) throws SlickException;
}
