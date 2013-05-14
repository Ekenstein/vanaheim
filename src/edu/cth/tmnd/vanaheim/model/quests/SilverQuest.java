package edu.cth.tmnd.vanaheim.model.quests;

import java.util.HashMap;

import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

final public class SilverQuest extends Quest {

	
	public SilverQuest() {

		super("Silver mining", "Mine three good lugs of silver and then return to me for your reward", new HashMap<String,Integer>());
		this.addNeededItemCount("Gold", 3);
	}


}