package edu.cth.tmnd.vanaheim.view.states.impl;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.view.impl.IContainer;
import edu.cth.tmnd.vanaheim.view.impl.IContainer;

public abstract class ViewState {
	protected IContainer parent;

	public ViewState(final IContainer containerParent) {
		this.parent = containerParent;
	}

	public abstract void render(GameContainer container, Graphics context) throws SlickException;
	public abstract void init(GameContainer container) throws SlickException;
	public abstract void update(GameContainer container, int delta) throws SlickException;
}
