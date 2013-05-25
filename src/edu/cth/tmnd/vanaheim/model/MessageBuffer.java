package edu.cth.tmnd.vanaheim.model;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * MessageBuffer contains a buffer of messages
 * produced by the model. It will push out messages
 * to its respective listeners whenever a message is
 * appended onto the buffer.
 * 
 * It's a singleton class in order to prevent the object
 * from being instantiated all over the application.
 * 
 * @author Gabriel Ekblad
 */
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

	private final ArrayList<String> buffer;

	private MessageBuffer() {
		this.buffer = new ArrayList<String>();
		this.listeners = new PropertyChangeSupport(this);
	}

	/**
	 * Appends the given string to the buffer. Will
	 * push out the message to all the listeners.
	 * @param s	the string to append on the buffer.
	 * 			If the string is null, nothing will happen.
	 */
	public void append(final String s) {
		if(s != null) {
			this.buffer.add(s);
			this.listeners.firePropertyChange(NEW_MESSAGE_ADDED, null, s);
		}
	}
	
	/**
	 * Adds listeners to the message buffer. These listeners
	 * will receive new messages whenever they are appended to
	 * the buffer.
	 * @param listener	listener of the message buffer.
	 */
	public void addListener(final PropertyChangeListener listener) {
		this.listeners.addPropertyChangeListener(listener);
	}
	
	/**
	 * Clears the buffer from all its messages.
	 */
	public void clear() {
		this.buffer.clear();
	}
	
	/**
	 * Returns the size of the buffer. That is,
	 * the amount of messages in the buffer.
	 * @return	the amount of messages in the buffer.
	 */
	public int size() {
		return this.buffer.size();
	}
	
	/**
	 * Returns the %amount% latest messages from the
	 * message buffer.
	 * @param amount
	 * @return
	 */
	public List<String> getLatestMessages(int amount) {
		List<String> tmp = new ArrayList<String>();
		for(int i = 0; i < amount && i < this.buffer.size(); i++) {
			tmp.add(this.buffer.get(this.buffer.size() - (i+1)));
		}
		
		return tmp;
	}
}
