package edu.cth.tmnd.vanaheim.model.creatures.npc.impl;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.quests.GoldQuest;

public class NPC extends Human {

	private State[] states;
	private int state;
	private State currentState;
	
	
	public NPC(float x, float y, int velocity, Inventory inventory, int maxHp, String NPCName, State[] states){
		super(x, y, velocity, inventory, maxHp,NPCName);
		state = 0;
		currentState = states[state];
		this.states = states;
	}
	
	public State getNextState(){
		if(state < states.length)
			return states[state + 1];
		else 
			return currentState;
	}
	
	public State getCurrentState(){
		return currentState;
		
	}
	
	public void changeToNextState(){
		if(state < states.length){
			state += 1;
			currentState = states[state];
		}
	}

	@Override
	public void talk(Human human, String talk) {
	}
	
}
