package edu.cth.tmnd.vanaheim.model;

import java.util.HashMap;
import java.util.Map;

public final class ObjectMapper {

	public static enum Type { REGISTERED, UNKNOWN };

	private static ObjectMapper theInstance = null;
	private final Map<String, Object> objects;

	public static ObjectMapper getInstance() {
		if(ObjectMapper.theInstance == null) {
			ObjectMapper.theInstance = new ObjectMapper();
		}

		return ObjectMapper.theInstance;
	}

	private ObjectMapper() {
		this.objects = new HashMap<String, Object>();
	}

	public void registerObject(final String name, final Object o) {
		if(name != null && o != null) {
			this.objects.put(name.toLowerCase(), o);
		}
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

		return this.objects.containsKey(name.toLowerCase()) ? Type.REGISTERED : Type.UNKNOWN;
	}

	public Object getObject(final String name) {
		if(name == null) {
			return null;
		}

		return this.objects.get(name.toLowerCase());
	}
}
