package edu.cth.tmnd.vanaheim.view.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.controller.Controller;
import edu.cth.tmnd.vanaheim.view.states.impl.ViewState;

public class GameState extends ViewState {

	private Controller ctrl;
	
	public GameState(){
		super(null);
	}

	@Override
	public void render(GameContainer container, Graphics context)
			throws SlickException {
		
		this.ctrl.render(container, context);
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		this.ctrl.update(container);
	}
}
