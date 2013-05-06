package edu.cth.tmnd.vanaheim.model.creatures.npc.impl.giveandtake;

import edu.cth.tmnd.vanaheim.model.MessageBuffer;
import edu.cth.tmnd.vanaheim.model.StateHandler;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.State;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class NPCStateWaiting extends State {

	@Override
	public void process(Human human, NPC npc, String s, Quest q, Item item) {
		if(human.getQuestBook().hasQuest(q)){
			if(human.getQuestBook().isComplete(q)){
				StateHandler.getInstance().push(StateHandler.State.TALKING, npc);
				MessageBuffer.getInstance().append("Well done! You have finished the quest " + q.getName() + 
								". Here is your reward. A really fine " + item.getItemName() + " if i may say so myself.");
				human.getInventory().addItem(item);
				npc.getInventory().removeItem(item);
				npc.getQuestBook().removeQuest(q.getName());
				npc.changeToNextState();
			}
			else{
				MessageBuffer.getInstance().append("You have not finished your quest! " + q.getDescription());
			}
		}
		
	}

}
