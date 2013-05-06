package edu.cth.tmnd.vanaheim.model.quests.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.cth.tmnd.vanaheim.model.Container;



public class QuestBook extends Container {

	private Map<String, Quest> quests;
	
	public QuestBook() {
		quests = new HashMap<String, Quest>();
	}
	
	public Map<String, Quest> showCompleteQuests(){
		Map<String, Quest> completeQuests = new HashMap<String, Quest>();
		
		for(Entry<String, Quest> quest: quests.entrySet()){
			if(quest.getValue().isComplete()){
				completeQuests.put(quest.getKey(),quest.getValue());
			}
		}
		return completeQuests;
		
	}
	
	public Map<String, Quest> getQuests() {
		return this.quests;
	}
	
	public Map<String, Quest> showIncompleteQuests(){
		Map<String, Quest> incompleteQuests = new HashMap<String, Quest>();
		
		for(Entry<String, Quest> quest: quests.entrySet()){
			if(!quest.getValue().isComplete()){
				incompleteQuests.put(quest.getKey(),quest.getValue());
			}
		}
		return incompleteQuests;
	}
	
	public boolean isComplete(){
		for(Entry<String, Quest> quest: quests.entrySet()){
			if(!quest.getValue().isComplete()){
				return false;
			}
		}
		return true;
	}
	
	public boolean isComplete(Quest q){
		if(this.getQuest(q.getName()).isComplete()){
			return true;
		}
		else 
			return false;
	}
	
	public void addQuest(Quest q){
		if(!quests.containsKey(q.getName())){
			System.out.println("Lägger till quest");
			quests.put(q.getName(), q);
		}
	}
	
	public void removeQuest(String questName){
			quests.remove(questName);
		
	}
	
	public Quest getQuest(String questName){
		return quests.get(questName);
	}
	
	public boolean hasQuest(Quest q){
		if(quests.containsKey(q.getName())){
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
}
