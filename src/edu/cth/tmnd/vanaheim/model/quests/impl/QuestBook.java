package edu.cth.tmnd.vanaheim.model.quests.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.cth.tmnd.vanaheim.model.Container;
import edu.cth.tmnd.vanaheim.model.items.impl.QuestItem;

public class QuestBook extends Container {

	private Map<String, Quest> quests;
	
	public QuestBook() {
		quests = new HashMap<String, Quest>();
	}
	
	public Map<String, Integer> getQuestObjectives(String quest) {
		Quest q = this.quests.get(quest);
		
		if(q != null) {
			return q.getItemsCount();
		}
		
		return null;
	}
	
	public Map<String, String> showCompleteQuests() {
		Map<String, String> completeQuests = new HashMap<String, String>();
		
		for(Entry<String, Quest> quest : this.quests.entrySet()) {
			if(quest.getValue().isComplete()) {
				completeQuests.put(quest.getKey(), quest.getValue().getDescription());
			}
		}
		
		return completeQuests;
	}
	
	public Map<String, String> getQuests() {
		Map<String, String> quests = new HashMap<String, String>();
		
		for(Entry<String, Quest> quest : this.quests.entrySet()) {
			quests.put(quest.getKey(), quest.getValue().getDescription());
		}
		
		return quests;
	}
	
	public Map<String, String> showIncompleteQuests(){
		Map<String, String> incompleteQuests = new HashMap<String, String>();
		
		for(Entry<String, Quest> quest: quests.entrySet()){
			if(!quest.getValue().isComplete()){
				incompleteQuests.put(quest.getKey(),quest.getValue().getDescription());
			}
		}
		return incompleteQuests;
	}
	
	public boolean isComplete(String questName){
		Quest q = this.quests.get(questName);
		
		if(q == null) {
			return false;
		}
		
		if(q.isComplete()) {
			return true;
		}
		
		return false;
	}
	
	public void addQuest(Quest q){
		if(!quests.containsKey(q.getName())){
			quests.put(q.getName(), q);
		}
	}
	
	public void removeQuest(String questName){
			quests.remove(questName);
		
	}
	
	public void addQuestItem(String questName, QuestItem item) {
		Quest q = this.quests.get(questName);
		
		if(q != null) {
			q.process(item);
		}
	}
	
	public boolean hasQuest(String questName){
		if(quests.containsKey(questName)){
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(){
		return quests.isEmpty();
	}
	
	public int size(){
		return quests.size();
	}
	
	public String getQuestDescription(String questName) {
		Quest q = this.quests.get(questName);
		
		if(q == null) {
			return null;
		}
		
		return q.getDescription();
	}

	public int getItemsleftOnQuest(String questName, String itemName){
		return this.quests.get(questName).getItemsLeft(itemName);
	}
}
