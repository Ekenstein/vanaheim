package edu.cth.tmnd.vanaheim.view.states.impl;

public class MenuState extends ViewState implements IContainer{

	MenuSettingsState menuSettings;
	
	public MenuState(){
		menuSettings = new MenuSettingsState();
	}

	@Override
	public ViewState nextState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViewState prevState() {
		// TODO Auto-generated method stub
		return null;
	}
}
