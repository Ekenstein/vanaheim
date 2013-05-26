package edu.cth.tmnd.vanaheim.model.quests.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

import edu.cth.tmnd.vanaheim.model.items.impl.QuestItem;


/*
 * Still implementing this class
 */

public abstract class Quest {

	private String name;
	private String description;
	
	private final Map<String, QuestObjective> objectives; 
	
	private class QuestObjective {
		private final int required;
		private int current = 0;
		
		public QuestObjective(int required) {
			this.required = required;
		}
	}
	
	public Quest(String name, String description, Map<String, Integer> requiredItems){
		this.name = name;
		this.description = description;
		this.objectives = new HashMap<String, QuestObjective>();
	}
	
	/*
	 * Add a value for each item that need to be reached to complete the quest
	 */
	public void addNeededItemCount(String itemName, int value) {
		QuestObjective qo = new QuestObjective(value);
		this.objectives.put(itemName, qo);
	}
	
	public int getCurrentItemCount(String itemName) {
		QuestObjective qo = this.objectives.get(itemName);
		
		if(qo != null) {
			return qo.current;
		}
		
		return 0;
	}
	
	public Map<String, Integer> getQuestObjectives() {
		Map<String, Integer> questObjectives = new HashMap<String, Integer>();
		
		for(Entry<String, QuestObjective> s : this.objectives.entrySet()) {
			questObjectives.put(s.getKey(), s.getValue().current);
		}
		
		return questObjectives;
	}
	
	public int getRequiredItems(String itemName) {
		QuestObjective qo = this.objectives.get(itemName);
		
		if(qo != null) {
			return qo.required;
		}
		
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isComplete() {
		boolean[] complete = new boolean[this.objectives.size()];
		int i = 0;
		for(Entry<String, QuestObjective> s : this.objectives.entrySet()) {
			QuestObjective q = s.getValue();
			
			if(q.current == q.required) {
				complete[i] = true;
			}
			i++;
		}
		
		return and(complete);
	}
	
	public static boolean and(boolean[] flags) {
		for(boolean flag : flags) {
			if(!flag) {
				return false;
			}
		}
		
		return true;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription(){
		return description;
		
	}
	
	public boolean process(QuestItem item) {
		QuestObjective qo = this.objectives.get(item.getItemName());
		
		if(qo != null) {
			if(qo.current < qo.required) {
				qo.current++;
				return true;
			}
		}
		
		return false;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quest other = (Quest) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}