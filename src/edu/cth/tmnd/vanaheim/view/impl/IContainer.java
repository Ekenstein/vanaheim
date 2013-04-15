package edu.cth.tmnd.vanaheim.view.impl;

import edu.cth.tmnd.vanaheim.view.states.impl.ViewState;

public interface IContainer {
	
	public ViewState nextState();
	public ViewState prevState();

}
