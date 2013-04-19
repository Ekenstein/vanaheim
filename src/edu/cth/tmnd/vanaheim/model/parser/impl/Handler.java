package edu.cth.tmnd.vanaheim.model.parser.impl;

import edu.cth.tmnd.vanaheim.model.MessageBuffer;

public abstract class Handler {
	protected MessageBuffer commandHandler = MessageBuffer.getInstance();

	protected abstract int getArgumentSize();

	public void handle(final Object[] os) {
		if(this.getArgumentSize() > os.length) {
			this.commandHandler.append("Unknown command.");
			return;
		}

		this.handleHelper(os);
	}

	protected abstract void handleHelper(Object[] os);
}
