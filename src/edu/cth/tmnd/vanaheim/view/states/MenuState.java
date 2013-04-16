package edu.cth.tmnd.vanaheim.view.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.view.impl.IContainer;
import edu.cth.tmnd.vanaheim.view.states.impl.ViewState;

public class MenuState extends ViewState implements IContainer{

	MenuSettingsState menuSettings;
	
	public MenuState(){
		super(null);
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

	@Override
	public void render(GameContainer container, Graphics context)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
