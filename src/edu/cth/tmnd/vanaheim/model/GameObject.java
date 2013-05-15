package edu.cth.tmnd.vanaheim.model;

/**
 * Representation of a game object in the game.
 * Handles the most basic information of a game object
 * like the position of the object and what the name
 * of the object is.
 * 
 * @author Gabriel Ekblad
 */

public abstract class GameObject {
	protected float x, y;
	protected String objectName;
	protected final ObjectMapper objectMapper;

	/** 
	 * Create a game object on a certain position
	 * @param x Coordinate x of the object
	 * @param y Coordinate y of the object
	 * @param objectName	The name of the object
	 * @param register		If the object should be registered
	 * 						to the object mapper or not.
	 */
	public GameObject(final float x, final float y, String objectName, boolean register) {
		this.x = x;
		this.y = y;
		this.objectName = objectName;
		this.objectMapper = ObjectMapper.getInstance();
		
		if(register) {
			this.register();
		}
	}

	/**
	 * Get the x position of the object
	 * @return Coordinate x
	 */
	public float getX() {
		return this.x;
	}

	/**
	 * Get the y position of the object
	 * @return Coordinate y
	 */
	public float getY() {
		return this.y;
	}

	/**
	 * Set the object's x position
	 * @param x Position x of the object
	 */
	public void setX(final float x) {
		this.x = x;
	}

	/**
	 * Set the object's x position
	 * @param y Position y of the object
	 */
	public void setY(final float y) {
		this.y = y;
	}
	
	/**
	 * Registers the object to the object mapper.
	 * The object will then be associated with its
	 * given object name in the object mapper.
	 */
	public void register() {
		this.objectMapper.registerObject(this.getName(), this);
	}
	
	/**
	 * Will unregister the object from the object mapper.
	 * 
	 */
	public void unregister() {
		this.objectMapper.removeObject(this.getName());
	}
	
	/**
	 * Returns the object's name.
	 * @return	the object's name.
	 */
	public String getName() {
		return this.objectName;
	}
}
