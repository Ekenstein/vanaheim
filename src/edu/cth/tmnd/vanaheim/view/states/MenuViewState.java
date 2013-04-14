package edu.cth.tmnd.vanaheim.view.states;

import edu.cth.tmnd.vanaheim.view.impl.IContainer;
import edu.cth.tmnd.vanaheim.view.states.impl.ViewState;

/**
 * MenuViewState handles the drawing of the menu and are also
 * in the role of containing other view states. That is,
 * MenuViewState holds several different view states such as Settings
 * and Game
 * @author Gabriel Ekblad
 *
 */
public final class MenuViewState extends ViewState implements IContainer {

	public MenuViewState(final IContainer parent) {
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
	public void render() {

	}

}
