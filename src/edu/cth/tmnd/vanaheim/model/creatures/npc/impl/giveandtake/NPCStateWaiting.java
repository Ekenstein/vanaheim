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
	public void process(Human human, NPC npc, String s, Quest quest, Item item) {
		if(human.hasQuest(quest.getName())){
			if(human.isQuestCompleted(quest.getName())){
				StateHandler.getInstance().push(StateHandler.State.TALKING, npc);
				MessageBuffer.getInstance().append("Well done! You have finished the quest " + quest.getName() + 
								". Here is your reward. A really fine " + item.getItemName() + " if i may say so myself.");
				human.addItem(item);
				npc.destroyItem(item);
				npc.removeQuest(quest.getName());
				npc.changeToNextState();
			}
			else{
				MessageBuffer.getInstance().append("You have not finished your quest! " + quest.getDescription());
			}
		}
		
	}

}
