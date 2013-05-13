package edu.cth.tmnd.vanaheim.model;

/**
 * Representation of an object in the game
 * @author Daniel
 *
 */

public abstract class GameObject {
	protected float x, y;
	protected String objectName;
	protected final ObjectMapper objectMapper;

	/** Create a game object on a certain position
	 * @param x Coordinate x of the object
	 * @param y Coordinate y of the object
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

	/**Get the x position of the object
	 * @return Coordinate x
	 */
	public float getX() {
		return this.x;
	}

	/**Get the y position of the object
	 * @return Coordinate y
	 */
	public float getY() {
		return this.y;
	}

	/**Set the objects x position
	 * @param x Position x of the object
	 */
	public void setX(final float x) {
		this.x = x;
	}

	/**Set the objects x position
	 * @param y Position y of the object
	 */
	public void setY(final float y) {
		this.y = y;
	}
	
	public void register() {
		this.objectMapper.registerObject(this.getName(), this);
	}
	
	public void unregister() {
		this.objectMapper.removeObject(this.getName());
	}
	
	public String getName() {
		return this.objectName;
	}
}
