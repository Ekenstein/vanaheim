package edu.cth.tmnd.vanaheim.model.quests;

import java.util.HashMap;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

final public class GunnarQuest extends Quest {

	
	public GunnarQuest(Creature owner) {

		super("Gold mining", "Mine three good lugs of gold and then return to me for your reward", owner, new HashMap<String,Integer>());
		this.addNeededItemCount("Gold", 3);
	}


}
