package edu.cth.tmnd.vanaheim.model.creatures.npc.impl.giveandtake;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.State;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class NPCStateStart extends State {


	
	public NPCStateStart(){
		
	}
	
	public void process(Human human, NPC npc, String s, Quest q){
		human.talk(npc, s);
		human.talk(npc, q.getDescription());
		human.getQuestBook().addQuest(q);
		npc.changeToNextState();
		
	}

}

