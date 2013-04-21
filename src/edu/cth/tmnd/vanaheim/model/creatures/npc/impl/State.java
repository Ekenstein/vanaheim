package edu.cth.tmnd.vanaheim.model.creatures.npc.impl;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public abstract class State {

	
	public abstract void process(Human human, NPC npc, String s, Quest q);
}
