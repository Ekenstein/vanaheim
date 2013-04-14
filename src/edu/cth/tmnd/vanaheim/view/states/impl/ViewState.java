package edu.cth.tmnd.vanaheim.view.states.impl;

import edu.cth.tmnd.vanaheim.view.impl.IContainer;

public abstract class ViewState {
	protected IContainer parent;

	public ViewState(final IContainer parent) {
		this.parent = parent;
	}

	public abstract void render();
}
