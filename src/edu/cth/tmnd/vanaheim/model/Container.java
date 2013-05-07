package edu.cth.tmnd.vanaheim.model;

public abstract class Container {
	protected final ObjectMapper objectMapper = ObjectMapper.getInstance();
	private boolean toggle = false;
	
	public void toggle() {
		this.toggle = !this.toggle;
	}
	
	public boolean isToggled() {
		return this.toggle;
	}
}
