package edu.cth.tmnd.vanaheim.model.creatures.npc.impl;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;

public class NPCStateStart {


	
	public NPCStateStart(){
		
	}
	
	public void process(Human human, Human npc){
		human.talk(npc, "Hello my young fellow, I can see you have learned to navigate thru the world. " +
						"It's time for your first quest");
		human.talk(npc, "");
	}

}

