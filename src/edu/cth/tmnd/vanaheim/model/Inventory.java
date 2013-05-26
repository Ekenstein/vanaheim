package edu.cth.tmnd.vanaheim.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.cth.tmnd.vanaheim.model.items.impl.Item;

/**
 * Inventory holds a list of items.
 * Each item added to the inventory will
 * be registered to the ObjectMapper.
 * 
 * @author Gabriel Ekblad
 *
 */
final public class Inventory extends Container {
	private final int slots;
	private int slotsLeft;

	private final List<Item> items;

	/**
	 * Instantiates the inventory with the given
	 * amount of slots.
	 * @param slots
	 */
	public Inventory(final int slots) {
		this.slots = this.slotsLeft = slots;
		this.items = new ArrayList<Item>(slots);
	}
	
	/**
	 * Returns the number of slots the inventory have.
	 * @return	number of slots.
	 */
	public int getSlots() {
		return this.slots;
	}
	
	/**
	 * Returns a list of items from the inventory.
	 * @return	list of items.
	 */
	public List<Item> getItems() {
		return this.items;
	}

	/**
	 * Adds an item to the inventory if
	 * there are any slots free and registers the item to the
	 * Object Mapper.
	 * @param item	the item to be added
	 * @return	true if there was any slots free,
	 * 			otherwise false if the item was
	 * 			null or if there weren't any slots left.
	 */
	public boolean addItem(final Item item) {
		if(item == null) {
			return false;
		}

		if(!this.hasSlots()) {
			return false;
		}

		super.objectMapper.registerObject(item.getItemName(), item);
		this.items.add(item);
		this.slotsLeft--;

		return true;
	}
	
	/**
	 * Will retrieve all the items from the inventory.
	 * Will remove the items from the inventory.
	 * @return	list of the retreived items.
	 */
	public List<Item> retreiveItems() {
		List<Item> items = new ArrayList<Item>();
		items.addAll(this.items);
		this.items.clear();
		this.slotsLeft = this.slots;
		
		return items;
	}

	/**
	 * Checks if there are any slots left in the
	 * inventory.
	 * @return	True if there were any slots left,
	 * 			otherwise false.
	 */
	public boolean hasSlots() {
		return this.slotsLeft > 0;
	}

	/**
	 * Checks if the inventory is empty or not.
	 * @return	true if the inventory is empty,
	 * 			otherwise false.
	 */
	public boolean isEmpty() {
		return this.slotsLeft == this.slots;
	}
	
	/**
	 * Returns the number of slots left in the inventory.
	 * @return	slots left in the inventory.
	 */
	public int getSlotsLeft() {
		return this.slotsLeft;
	}

	/**
	 * Will retrieve the given item from inventory,
	 * that is, it will remove the item from the inventory.
	 * More formally, will return an item if there exist
	 * an item i such that item.equals(i)
	 * @param item
	 * @return	the retreived item or null if the item didn't
	 * 			exist.
	 */
	public Item retrieveItem(final Item item) {
		int index = this.getItemIndex(item);
		
		if(index == -1) {
			return null;
		}
		
		this.slotsLeft++;
		return this.items.remove(index);
	}
	
	private int getItemIndex(Item item) {
		if(item == null) {
			return -1;
		}
		
		if(this.isEmpty()) {
			return -1;
		}
		
		return this.items.indexOf(item);
	}
	
	/**
	 * Gets the given item from the inventory.
	 * If the item exists the item will be returned,
	 * otherwise null.
	 * Will not remove the item from the inventory.
	 * @param item	the item to get.
	 * @return	the item to get.
	 */
	public Item getItem(Item item) {
		int index = this.getItemIndex(item);
		
		if(index == -1) {
			return null;
		}
		
		return this.items.get(index);
	}
	
	/**
	 * Returns the item associated to the given string.
	 * If the item didn't exist, null will be returned,
	 * otherwise the item object.
	 * Will not remove the item from the inventory.
	 * @param item	the item name
	 * @return	the item associated to the name.
	 */
	public Item getItem(String item){
		Iterator<Item> it = items.iterator();
		while(it.hasNext())
		{
			 Item i = it.next();
			 if(i.getItemName().toLowerCase().equals(item.toLowerCase())){
				 return i;
			 }
		}
		return null;
	}

	public void clear() {
		this.items.clear();
		this.slotsLeft = this.slots;
		
	}

}
