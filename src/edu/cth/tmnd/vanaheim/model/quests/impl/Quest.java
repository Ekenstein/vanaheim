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
		this.itemsCount = requiredItems;
	}
	
	/*
	 * Add a value for each item that need to be reached to complete the quest
	 */
	public void addNeededItemCount(String itemName, Integer value) {
		itemsCount.put(itemName, value);
	}
	
	public int getNumberOfDifferentItemsNeeded(){
		return itemsCount.size();
	}
	
	public int getItemsLeft(String itemName){
			return itemsCount.get(itemName);
		
	}
	
	public void removeItemFromQuest(String item){
		itemsCount.remove(item);
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
		if(itemsCount.containsKey(item.getItemName())){
			if(itemsCount.get(item.getItemName()) > 0){
				itemsCount.put(item.getItemName(), itemsCount.get(item.getItemName()) - 1);
				isComplete = true;
				for(String items: itemsCount.keySet()){
					isComplete = itemsCount.get(items) > 0 ? false : true;
					if(!isComplete){
						break;
					}
				}
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
