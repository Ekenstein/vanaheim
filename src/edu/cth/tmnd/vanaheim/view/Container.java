package edu.cth.tmnd.vanaheim.view;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Container extends BasicGame implements IContainer  {
	
	private ViewState[] states;
	private ViewState cstate;

	public Container(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
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
