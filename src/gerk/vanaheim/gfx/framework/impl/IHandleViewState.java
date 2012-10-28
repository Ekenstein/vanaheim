package gerk.vanaheim.gfx.framework.impl;

public interface IHandleViewState {
	/**
	 * Changes the state to the given state.
	 * @param s	the state to change to
	 */
	public void setState(ViewState s);
	
	public void nextState();
	public void prevState();
}
