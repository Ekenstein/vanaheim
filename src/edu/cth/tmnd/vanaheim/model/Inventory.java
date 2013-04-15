package edu.cth.tmnd.vanaheim.model;

import java.util.ArrayList;
import java.util.List;

import edu.cth.tmnd.vanaheim.model.items.impl.Item;

final public class Inventory {
	private final int slots;
	private int slotsLeft;

	private final List<Item> items;

	public Inventory(final int slots) {
		this.slots = this.slotsLeft = slots;
		this.items = new ArrayList<Item>(slots);
	}

	public int getSlots() {
		return this.slots;
	}

	/**
	 * Adds an item to the inventory if
	 * there are any slots free.
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
	 * an item i such that item.equals(i),
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
			this.slotsLeft++;
			return true;
		}

		return false;
	}

	public boolean destroyItem(final String itemName) {
		return true;
	}

	/**
	 * Will drop an item.<br />
	 * More formally, if there exist an item i
	 * such that item.equals(i), it will set the
	 * item's owner to null and remove the item i
	 * from the inventory and return the item.
	 * @return	the item that was dropped or null
	 * 			if it didn't exist or if the given
	 * 			item was null.
	 */
	public Item dropItem(final Item item) {
		if(item == null) {
			return null;
		}

		if(this.isEmpty()) {
			return null;
		}

		final int index = this.items.indexOf(item);

		if(index == -1) {
			return null;
		}

		// TODO remove the owner of the item
		return this.items.remove(index);
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
	 * Returns the number of slots left in the
	 * inventory.
	 * @return	number of slots left.
	 */
	public int getSlotsLeft() {
		return this.slotsLeft;
	}
}
