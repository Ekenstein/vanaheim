package edu.cth.tmnd.vanaheim.model.creatures.impl;

import edu.cth.tmnd.vanaheim.model.quests.impl.QuestBook;

public abstract class Human extends Creature {
	
	protected QuestBook questBook;
	
	public Human(final float x, final float y, final int velocity, final int maxHP, final String creatureName, boolean register) {
		super(x, y, velocity, maxHP, creatureName, register);
		questBook = new QuestBook();
	}

	public abstract void talk(Human human);
	
	public QuestBook getQuestBook(){
		return this.questBook;
	}

}
