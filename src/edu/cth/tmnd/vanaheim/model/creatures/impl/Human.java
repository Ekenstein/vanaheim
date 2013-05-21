package edu.cth.tmnd.vanaheim.model.creatures.impl;

import java.util.Map;

import edu.cth.tmnd.vanaheim.model.items.impl.QuestItem;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;
import edu.cth.tmnd.vanaheim.model.quests.impl.QuestBook;

public abstract class Human extends Creature {
	
	protected QuestBook questBook;
	
	public Human(final float x, final float y, final int velocity, final int maxHP, final int dmg, final String creatureName, boolean register) {
		super(x, y, velocity, maxHP, dmg, creatureName, register);
		questBook = new QuestBook();
	}

	public abstract void talk(Human human);
	
	public void addQuestItem(String questName, QuestItem item) {
		this.questBook.addQuestItem(questName, item);
	}
	
	public Map<String, String> getQuests() {
		return this.questBook.getQuests();
	}
	
	public boolean hasQuest(String questName) {
		return this.questBook.hasQuest(questName);
	}
	
	public void addQuest(Quest q) {
		this.questBook.addQuest(q);
	}
	
	public boolean isQuestCompleted(String questName) {
		return this.questBook.isComplete(questName);
	}
	
	public String getQuestDescription(String questName) {
		return this.questBook.getQuestDescription(questName);
	}
	
	public void removeQuest(String questName) {
		this.questBook.removeQuest(questName);
	}
	
	public int getItemsleftOnQuest(String questName, String itemName){
		for(String q: this.questBook.getQuests().keySet()){
			if(q.equals(questName)){
				return questBook.getItemsleftOnQuest(questName, itemName);
			}
		}
		return 0;
	}
}
