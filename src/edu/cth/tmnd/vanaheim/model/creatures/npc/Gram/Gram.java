package edu.cth.tmnd.vanaheim.model.creatures.npc.Gram;

import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.State;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.giveandtake.NPCStateDone;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.giveandtake.NPCStateStart;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.giveandtake.NPCStateWaiting;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.quests.GoldQuest;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class Gram extends NPC {
	
	
	private static final State[] states = {new NPCStateStart(), new NPCStateWaiting(), new NPCStateDone()};
	private Quest quest;
	private Axe reward;

	public Gram(float x, float y) throws SlickException {
		super(x, y, 0, 1, "Gram", states);
		this.quest = new GoldQuest();
		this.reward = new Axe();
		this.addItem(this.reward);
		super.setDirection(Direction.RIGHT);
	}

	
	@Override
	protected void talkative(Human human) {
		this.getCurrentState().process(
				human, 
				this, 
				"Gram: Hello my friend. I can see you found the promised land.",
				this.quest, this.reward);
	}
	
}
