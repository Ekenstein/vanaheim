package edu.cth.tmnd.vanaheim.view.impl;

/**
 * IContainer interface has the role
 * of describing a container that holds
 * several different ViewStates.
 * @author Gabriel Ekblad
 *
 */
public interface IContainer {
	/**
	 * Triggers the container to switch
	 * to the next state, providing that
	 * there are a next state. If there is
	 * no next state, nothing will happen.
	 */
	public abstract void nextState();

	/**
	 * Triggers the container to switch
	 * to the previous state, providing that
	 * there are a previous state.
	 */
	public abstract void prevState();
}
