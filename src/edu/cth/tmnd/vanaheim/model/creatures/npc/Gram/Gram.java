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

public class Gram extends NPC {
	
	
	private static final State[] states = {new NPCStateStart(), new NPCStateWaiting(), new NPCStateDone()};

	public Gram(float x, float y) throws SlickException {
		super(x, y, 0, 1, "Gram", states);
		this.getQuestBook().addQuest(new GoldQuest(this));
		this.getInventory().addItem(new Axe());
		super.setDirection(Direction.RIGHT);
	}

	
	@Override
	protected void talkative(Human human) {
		this.getCurrentState().process(human, this, "Hello my friend. I can see you found the promised land.",
									   	this.getQuestBook().getQuest("Gold mining"), this.getItem("Crude axe"));
	}
	
}
