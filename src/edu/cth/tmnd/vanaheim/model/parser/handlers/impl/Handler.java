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
	protected Player p;
	protected Controller c;
	private boolean initialized = false;
	
	public void handle(final Object[] args) {
		if(!this.initialized) {
			this.init();
		}
		if(!this.checkArgs(args)) {
			this.msgBuffer.unknownCommand();
		}

		this.handleArgs(args);
	}
	
	private void init() {
		try {
			this.c = Controller.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SlickException e) {
		}
		
		this.p = (Player) ObjectMapper.getInstance().getObject(Constants.PLAYER_OBJECT_NAME);
		
		this.initialized = true;
	}

	protected abstract boolean checkArgs(Object[] args);
	protected abstract void handleArgs(Object[] args);
}
