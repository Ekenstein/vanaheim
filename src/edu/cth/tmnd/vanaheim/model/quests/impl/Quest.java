package edu.cth.tmnd.vanaheim.model.quests.impl;


import java.util.Map;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;


/*
 * Still implementing this class
 */

public abstract class Quest {

	private String name;
	private String description;
	private Quest following;
	private boolean isComplete;
	private Creature owner;
	private Creature user;
	private Map<String,Integer> itemsCount;
	
	
	public Quest(String name, String description, Creature owner, Map<String, Integer> requiredItems){
		this.name = name;
		this.owner = owner;
		this.description = description;
		itemsCount = requiredItems;
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
	
	public Creature getOwner(){
		return owner;
	}
	
	public void setUser(Creature user){
		this.user = user;
	}
	
	public Creature getUser(){
		return user;
	}
	
	
	public boolean process(Item item) {
		if(itemsCount.containsValue(item.getItemName())){
			if(itemsCount.get(item.getItemName()) > 0){
				itemsCount.put(item.getItemName(), itemsCount.get(item.getItemName()) - 1);
				return true;
			}
		}
		return false;
	}
}
