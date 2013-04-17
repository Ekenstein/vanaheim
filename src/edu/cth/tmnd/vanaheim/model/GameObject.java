package edu.cth.tmnd.vanaheim.model;

import org.newdawn.slick.Animation;

public abstract class GameObject {
	
	protected float x, y;
	
	public GameObject(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
}
