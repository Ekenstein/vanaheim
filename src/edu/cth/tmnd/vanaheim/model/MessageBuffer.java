package edu.cth.tmnd.vanaheim.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public final class MessageBuffer {
	public final static String NEW_MESSAGE_ADDED = "New message added";
	private static MessageBuffer theInstance = null;

	private final PropertyChangeSupport listeners;

	public static MessageBuffer getInstance() {
		if(theInstance == null) {
			theInstance = new MessageBuffer();
		}

		return theInstance;
	}

	private final ArrayList<String> cache;

	private MessageBuffer() {
		this.cache = new ArrayList<String>();
		this.listeners = new PropertyChangeSupport(this);
	}

	public void append(final String s) {
		if(s != null) {
			this.cache.add(s);
			this.listeners.firePropertyChange(NEW_MESSAGE_ADDED, null, s);
		}
	}

	public void unknownCommand() {
		this.append("Unknown action.");
	}

	public void addListener(final PropertyChangeListener listener) {
		this.listeners.addPropertyChangeListener(listener);
	}
}
