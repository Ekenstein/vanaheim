package edu.cth.tmnd.vanaheim.model.creatures.npc;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.State;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.GoldQuest;

public class Gram extends NPC {
	
	private Item reward;

	public Gram(float x, float y, int velocity, Inventory inventory, int maxHp, String NPCName, State[] states) {
		super(x, y, velocity, inventory, maxHp, NPCName, states);
		this.getQuestBook().addQuest(new GoldQuest(this));
		this.reward = new Axe(this);
	}

	
	@Override
	public void talk(Human human, String talk) {
		this.getCurrentState().process(human, this, "Hello my friend. I can see you found the promised land.",
									   	this.getQuestBook().getQuest("Gold mining"), reward);
	}
	
}
