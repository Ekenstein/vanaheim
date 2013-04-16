package edu.cth.tmnd.vanaheim.model;

import org.newdawn.slick.Animation;

public abstract class GameObject {
	
	protected float x, y;
	protected Animation[] animation;
	
	public GameObject(float x, float y, Animation[] animation) {
		this.x = x;
		this.y = y;
		this.animation = animation;
	}
	
	public Animation[] getAnimation() {
		return this.animation;
	}
	
	public void setAnimation(Animation[] animation) {
		this.animation = animation;
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
