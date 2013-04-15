package edu.cth.tmnd.vanaheim.model.creatures.NPC;

import edu.cth.tmnd.vanaheim.model.creatures.Human;
import edu.cth.tmnd.vanaheim.model.creatures.State;

public class NPC {

	State s;
	State next;
	
	public NPC(){
		
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
