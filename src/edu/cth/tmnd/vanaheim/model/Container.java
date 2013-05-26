package edu.cth.tmnd.vanaheim.model;

/**
 * Abstract class for handling container's.
 * It will just toggle the container. How you
 * use the toggle is up to the programmer.
 * 
 *
 */
public abstract class Container {
	protected final ObjectMapper objectMapper = ObjectMapper.getInstance();
	private boolean toggle = false;
	
	/**
	 * Toggle's the container. If the container is
	 * already toggled, the container will be untoggled and
	 * vice versa.
	 */
	public void toggle() {
		this.toggle = !this.toggle;
	}
	
	/**
	 * Checks if the container is toggled or not.
	 * @return	if the container is toggled, return true,
	 * 			otherwise false.
	 */
	public boolean isToggled() {
		return this.toggle;
	}
	
	public void setToggle(boolean flag) {
		this.toggle = flag;
	}
}
