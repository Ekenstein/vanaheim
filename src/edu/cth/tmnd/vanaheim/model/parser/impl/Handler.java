package edu.cth.tmnd.vanaheim.model.parser.impl;

import edu.cth.tmnd.vanaheim.model.MessageBuffer;

public abstract class Handler {
	protected MessageBuffer msgBuffer = MessageBuffer.getInstance();

	public void handle(final Object[] args) {
		if(!this.checkArgs(args)) {
			this.msgBuffer.unknownCommand();
		}

		this.handleArgs(args);
	}

	protected abstract boolean checkArgs(Object[] args);
	protected abstract void handleArgs(Object[] args);
}
