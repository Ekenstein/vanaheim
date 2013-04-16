package edu.cth.tmnd.vanaheim.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Anything that will be drawn should extend this class.<br />
 * Tunnel all rendering to these classes.
 * @author Gabriel Ekblad & Daniel Jonsson
 *
 */
public interface GameObject {

	/**
	 * Renders the game object. All rendering logic here for this particular object.
	 * @param container	If necessary, tunnel the GameContainer here.
	 * @param context	If necessary, tunnel the Graphics context here.
	 * @throws SlickException	In case of explosion.
	 */
	public abstract void render(GameContainer container, Graphics context) throws SlickException;
	
	/**
	 * Update logic here. Like input and stuff.
	 * @param container
	 * @throws SlickException
	 */
	public abstract void update(GameContainer container, int delta) throws SlickException;
	
	/**
	 * Init the object here.
	 * @param container
	 * @throws SlickException
	 */
	public abstract void init(GameContainer container) throws SlickException;
	
	public abstract float getX();
	
	public abstract float getY();
	
	public abstract void setX(float x);
	
	public abstract void setY(float y);
}
