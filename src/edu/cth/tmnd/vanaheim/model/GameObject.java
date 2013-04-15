package edu.cth.tmnd.vanaheim.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class GameObject {

	protected float x;
	protected float y;

	public GameObject(final float x, final float y) {
		this.x = x;
		this.y = y;
	}

	public abstract void render(GameContainer container, Graphics context) throws SlickException;

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public void setX(final float x) {
		this.x = x;
	}

	public void setY(final float y) {
		this.y = y;
	}
}
