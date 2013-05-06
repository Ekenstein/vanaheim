package edu.cth.tmnd.vanaheim.model.creatures.npc.impl.giveandtake;

import edu.cth.tmnd.vanaheim.model.MessageBuffer;
import edu.cth.tmnd.vanaheim.model.StateHandler;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.State;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class NPCStateStart extends State {
	
	public void process(Human human, NPC npc, String s, Quest q, Item item){
		MessageBuffer msgBuffer = MessageBuffer.getInstance();
		StateHandler.getInstance().push(StateHandler.State.TALKING, npc);
		msgBuffer.append(s);
		msgBuffer.append(q.getDescription());
		human.getQuestBook().addQuest(q);
		StateHandler.getInstance().push(StateHandler.State.QUEST_ACCEPTED, npc);
		npc.changeToNextState();
	}

}

