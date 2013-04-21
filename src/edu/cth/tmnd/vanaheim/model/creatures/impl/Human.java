package edu.cth.tmnd.vanaheim.model.creatures.impl;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.quests.impl.QuestBook;

public abstract class Human extends Creature {
	
	private QuestBook questBook;
	
	public Human(final float x, final float y, final int velocity,final Inventory inventory, final int maxHP, final String creatureName) {
		super(x, y, velocity, inventory, maxHP, creatureName);
		questBook = new QuestBook();
	}

	public abstract void talk(Human human, String talk);
	
	public QuestBook getQuestBook(){
		return this.questBook;
	}

}
