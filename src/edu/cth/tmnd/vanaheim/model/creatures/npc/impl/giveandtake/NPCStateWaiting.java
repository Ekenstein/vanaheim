package edu.cth.tmnd.vanaheim.model.creatures.npc.impl.giveandtake;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.State;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class NPCStateWaiting extends State {

	
	public NPCStateWaiting(){
		
	}


	@Override
	public void process(Human human, NPC npc, String s, Quest q, Item item) {
		if(human.getQuestBook().hasQuest(q)){
			if(human.getQuestBook().isComplete(q)){
				human.talk(npc, "Well done! You have finished the quest" + q.getName() + ". Here is your reward");
				human.getInventory().addItem(item);
				npc.changeToNextState();
			}
			else{
				human.talk(npc, "You have not finished your quest!" + q.getDescription());
			}
		}
		
	}

}
