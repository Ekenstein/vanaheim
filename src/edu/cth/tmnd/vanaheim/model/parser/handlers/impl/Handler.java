package edu.cth.tmnd.vanaheim.model.parser.handlers.impl;

import java.io.IOException;

import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.controller.Controller;
import edu.cth.tmnd.vanaheim.model.MessageBuffer;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;

public abstract class Handler {
	protected MessageBuffer msgBuffer = MessageBuffer.getInstance();
	protected final Player p;
	protected Controller c;
	
	public Handler() {
		this.p = (Player) ObjectMapper.getInstance().getObject(Constants.PLAYER_OBJECT_NAME);
		try {
			this.c = Controller.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void handle(final Object[] args) {
		if(!this.checkArgs(args)) {
			this.msgBuffer.unknownCommand();
		}

		this.handleArgs(args);
	}

	protected abstract boolean checkArgs(Object[] args);
	protected abstract void handleArgs(Object[] args);
}
