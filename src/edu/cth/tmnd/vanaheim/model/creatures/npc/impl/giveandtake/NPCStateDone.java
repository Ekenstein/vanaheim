package edu.cth.tmnd.vanaheim.model.creatures.npc.impl.giveandtake;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.State;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class NPCStateDone extends State {


	
	public NPCStateDone() {

	}

	@Override
	public void process(Human human, NPC npc, String s, Quest q, Item item) {
		human.talk(npc, "Hello " + human.getCreatureName() + ". Are you lost?");
		
	}

	
}
