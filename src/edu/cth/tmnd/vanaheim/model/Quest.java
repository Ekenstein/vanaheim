package edu.cth.tmnd.vanaheim.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.cth.tmnd.vanaheim.model.creatures.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;


/*
 * Still implementing this class
 */

public class Quest {

	private String name;
	private String description;
	private Quest following;
	private boolean isComplete;
	private Creature owner;
	private Creature user;
	private Map<String,Integer> itemsCount;
	
	
	public Quest(String name, String description, Creature owner){
		this.name = name;
		this.owner = owner;
		this.description = description;
		itemsCount = new HashMap<String, Integer>();
	}
	
	/*
	 * Add a value for each item that need to be reached to complete the quest
	 */
	public void addNeededItemCount(String itemName, Integer value) {
		itemsCount.put(itemName, value);
	}

	public Map<String, Integer> getItemsCount() {
		return itemsCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Quest getFollowing() {
		return following;
	}

	public void setFollowing(Quest following) {
		this.following = following;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription(){
		return description;
		
	}
	
	public boolean hasFollowingQuest(){
		if(following != null){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public void process(Item item){
		String itemName = item.getItemName();
		boolean allComplete = true;
		
		if(item.isQuestItem() && !isComplete()){
			if(itemsCount.containsKey(itemName) && itemsCount.get(itemName) > 0){
				itemsCount.put(itemName, itemsCount.get(itemName) - 1);
				for(Entry<String,Integer> items: itemsCount.entrySet()){
					if(items.getValue() > 0){
						allComplete = false;
					}
				}
				isComplete = allComplete;
			}
			
		}
	}
}
