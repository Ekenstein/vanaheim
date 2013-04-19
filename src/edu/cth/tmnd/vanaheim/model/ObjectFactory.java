package edu.cth.tmnd.vanaheim.model;

import java.util.HashMap;
import java.util.Map;

import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public final class ObjectFactory {

	public static enum Type { ITEM, CREATURE, UNKNOWN };

	private static ObjectFactory theInstance = null;
	private final Map<String, Item> registeredItems;
	private final Map<String, Creature> registeredCreatures;

	public static ObjectFactory getInstance() {
		if(ObjectFactory.theInstance == null) {
			ObjectFactory.theInstance = new ObjectFactory();
		}

		return ObjectFactory.theInstance;
	}

	private ObjectFactory() {
		this.registeredCreatures = new HashMap<String, Creature>();
		this.registeredItems = new HashMap<String, Item>();
	}

	public void registerItem(final Item i) {
		this.registeredItems.put(i.getItemName(), i);
	}

	public void registerCreature(final Creature c) {
		this.registeredCreatures.put(c.getCreatureName(), c);
	}

	/**
	 * Checks if a given String is registered in the object mappings.
	 * @param name	The name of the object.
	 * @return		The type of the object. If it doesn't exist it will return
	 * 				Type.UNKNOWN, otherwise Type.{ITEM | CREATURE}
	 */
	public Type isRegistered(final String name) {
		if(name == null) {
			return Type.UNKNOWN;
		}

		if(this.registeredItems.containsKey(name)) {
			return Type.ITEM;
		} else if(this.registeredCreatures.containsKey(name)) {
			return Type.CREATURE;
		}

		return Type.UNKNOWN;
	}

	public Item getRegisteredItem(final String name) {
		if(name == null) {
			return null;
		}

		return this.registeredItems.get(name);
	}

	public Creature getRegisteredCreature(final String name) {
		if(name == null) {
			return null;
		}

		return this.registeredCreatures.get(name);
	}
}
