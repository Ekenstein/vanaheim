package edu.cth.tmnd.vanaheim.model.creatures;

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
