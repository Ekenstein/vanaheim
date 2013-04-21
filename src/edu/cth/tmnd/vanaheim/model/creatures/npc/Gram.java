package edu.cth.tmnd.vanaheim.model.creatures.npc;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.State;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.giveandtake.NPCStateDone;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.giveandtake.NPCStateStart;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.giveandtake.NPCStateWaiting;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.GoldQuest;

public class Gram extends NPC {
	
	
	private static final State[] states = {new NPCStateStart(), new NPCStateWaiting(), new NPCStateDone()};

	public Gram(float x, float y) {
		super(x, y, 0, new Inventory(10), 1, "Gram", states);
		this.getQuestBook().addQuest(new GoldQuest(this));
		this.getInventory().addItem(new Axe(this));
	}

	
	@Override
	public void talk(Human human, String talk) {
		this.getCurrentState().process(human, this, "Hello my friend. I can see you found the promised land.",
									   	this.getQuestBook().getQuest("Gold mining"), this.getItem("Crude axe"));
	}
	
}
