package edu.cth.tmnd.vanaheim.model.quests;

import java.util.HashMap;

import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

final public class GoldQuest extends Quest {

	
	public GoldQuest() {

		super("Gold mining", "Mine three good lugs of gold and then return to me for your reward", new HashMap<String,Integer>());
		this.addNeededItemCount("Gold", 3);
	}


}
