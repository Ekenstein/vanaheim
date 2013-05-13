package edu.cth.tmnd.vanaheim.model.creatures.npc.impl;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;

public abstract class NPC extends Human {
	
	private State[] states;
	private int state;
	private State currentState;
	
	
	public NPC(float x, float y, int velocity, int maxHp, String NPCName, State[] states){
		super(x, y, velocity, maxHp,NPCName, true);
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
	
	protected boolean withinRange(Human target) {
		float humanX = target.getX();
		float humanY = target.getY();
		
		if(!(Math.abs(super.x - humanX) <= Constants.NPC_WITHIN_RANGE_VALUE)) {
			return false;
		}
		
		if(!(Math.abs(super.y - humanY) <= Constants.NPC_WITHIN_RANGE_VALUE)) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public void talk(Human human) {
		if(this.withinRange(human)) {
			this.talkative(human);
		}
	}
	
	protected abstract void talkative(Human human);
}
