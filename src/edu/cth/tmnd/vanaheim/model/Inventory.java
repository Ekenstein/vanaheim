package edu.cth.tmnd.vanaheim.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.cth.tmnd.vanaheim.model.items.impl.Item;

final public class Inventory extends Container {
	private final int slots;
	private int slotsLeft;

	private final List<Item> items;

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
	 * Will destroy an item. That is, an item
	 * will have no relationship with anything,
	 * and will be removed from the inventory.<br />
	 * More formally, returns true and removes an
	 * item  if and only if there exist
	 * an item i such that item.equals(i).
	 * If item was succesfully removed, the item will be removed from
	 * the Object Mapper.
	 * @param item	the item to destroy
	 * @return	true if the item was succesfully destroyed,
	 * 			otherwise false.
	 * 			More precisely, it will return false if
	 * 			the inventory is empty or if the item didn't
	 * 			exist.
	 */
	public boolean destroyItem(final Item item) {
		if(this.isEmpty()) {
			return false;
		}

		final boolean itemRemoved = this.items.remove(item);

		if(itemRemoved) {
			super.objectMapper.removeObject(item.getItemName());
			this.slotsLeft++;
			return true;
		}

		return false;
	}
	
	/**
	 * Will retrieve the items from the inventory.
	 * Will remove the items from the inventory.
	 * @return
	 */
	public List<Item> retreiveItems() {
		
		//TODO kan innehålla samma referens = fubar
		List<Item> items = this.items;
		this.items.clear();
		
		return items;
	}

	/**
	 * Checks if there are any slots left in the
	 * inventory
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
	 * Will retrieve the given item from inventory,
	 * that is, it will remove the item from the inventory.
	 * More formally, will return an item if there exist
	 * an item i such that item.equals(i)
	 * @param item
	 * @return
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
	
	public Item getItem(Item item) {
		int index = this.getItemIndex(item);
		
		if(index == -1) {
			return null;
		}
		
		return this.items.get(index);
	}

	/**
	 * Returns the number of slots left in the
	 * inventory.
	 * @return	number of slots left.
	 */
	public int getSlotsLeft() {
		return this.slotsLeft;
	}
	
	public Item getItem(String item){
		Iterator<Item> it = items.iterator();
		while(it.hasNext())
		{
			 Item i = it.next();
			 if(i.getItemName().equals(item)){
				 return i;
			 }
		}
		return null;
	}
}
