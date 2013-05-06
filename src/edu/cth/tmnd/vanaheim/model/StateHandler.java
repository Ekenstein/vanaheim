package edu.cth.tmnd.vanaheim.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import edu.cth.tmnd.vanaheim.constants.Constants;

public final class StateHandler {
	private static StateHandler theInstance = null;
	
	public static StateHandler getInstance() {
		if(theInstance == null) {
			theInstance = new StateHandler();
		}
		
		return theInstance;
	}
	
	public static enum State { TALKING, FIGHTING, SHOW, QUEST_ACCEPTED };
	
	private final PropertyChangeSupport listeners;
	
	private StateHandler() {
		this.listeners = new PropertyChangeSupport(this);
	}
	
	public void push(State s, Object o) {
		this.listeners.firePropertyChange(Constants.STATE_CHANGED, s, o);
	}
	
	public void addListeners(PropertyChangeListener listener) {
		this.listeners.addPropertyChangeListener(listener);
	}
}