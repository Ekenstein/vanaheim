package edu.cth.tmnd.vanaheim.model.quests;

import java.util.HashMap;

import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

final public class GoldQuest extends Quest {

	
	public GoldQuest() {

		super("Gold mining", "Kill three spiders and gather gold from their tusks", new HashMap<String,Integer>());
		this.addNeededItemCount("Gold", 3);
	}


}
