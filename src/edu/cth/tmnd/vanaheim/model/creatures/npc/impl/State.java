package edu.cth.tmnd.vanaheim.model.creatures.npc.impl;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;

public abstract class State {

	private State nextState;
	
	public abstract void process(Human h,Human npc);
}
