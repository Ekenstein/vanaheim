package edu.cth.tmnd.vanaheim.model.creatures.npc.impl;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;

public class NPC extends Human {

	State s;
	State next;
	
	public NPC(float x, float y, int velocity, Inventory inventory, int maxHp, String NPCName){
		super(x, y, velocity, inventory, maxHp,NPCName);
	}
	
	public State nextState(){
		return next;
		
	}
	
	public State thisState(){
		return s;
		
	}
	
	public void changeToNextState(){
		s = next;
	}

	@Override
	public void talk(Human human, String talk) {
		s.process(human,this);
	}
	
}
