package edu.cth.tmnd.vanaheim.model.quests;

import java.util.HashMap;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class ExploreQuest extends Quest {

	public ExploreQuest(Creature owner) {

		super("Explore the new world", "Talk to old man Enuk down by the campfire and give me his compass", owner, new HashMap<String,Integer>());
		this.addNeededItemCount("Compass", 1);
	} 
}
