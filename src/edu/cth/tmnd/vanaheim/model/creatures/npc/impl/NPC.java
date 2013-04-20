package edu.cth.tmnd.vanaheim.model.creatures.npc.impl;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;

public class NPC extends Human {

	State s;
	State next;
	
	public NPC(float x, float y, int velocity, Inventory inventory, int maxHp){
		super(x, y, velocity, inventory, maxHp);
	}
	
	public void talk(Human human){
		
	}
	
	public State nextState(){
		return next;
		
	}
	
	public State thisState(){
		return s;
		
	}
	
}
