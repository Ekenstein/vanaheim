package gerk.vanaheim.gfx.framework.impl;

import org.lwjgl.input.Keyboard;

public abstract class ViewState {
	
	protected IHandleViewState parent;
	
	public ViewState(IHandleViewState parent) {
		this.parent = parent;
	}

	/**
	 * State specific rendering
	 */
	public abstract void render();
	
	/**
	 * State specific key handling
	 * @param keyEvent	the key event that has been triggered.
	 * @see #Keyboard
	 */
	public abstract void handleKeyEvent(int keyEvent);
	
	public void pollInput() {
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				this.handleKeyEvent(Keyboard.getEventKey());
			}
		}
	}
}
