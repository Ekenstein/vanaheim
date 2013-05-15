package edu.cth.tmnd.vanaheim.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles mapping between object's and their desired name.
 * Each method handling objects and their respective mappings
 * are case insensitive. The names will be set to lower case.
 * @author Gabriel Ekblad
 *
 */
public final class ObjectMapper {

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

	/**
	 * Maps the given object to the given name.
	 * If a name is already used by another object,
	 * that name will be associated to the new object.
	 * @param name	the name of the object
	 * @param o		the object to map with the name.
	 */
	public void registerObject(final String name, final Object o) {
		if(name != null && o != null) {
			this.objects.put(name.toLowerCase(), o);
		}
	}

	/**
	 * Checks if a given String is registered in the object mappings.
	 * @param name	The name of the object.
	 * @return		True if the object was registered in the object mapper,
	 * 				otherwise false.
	 */
	public boolean isRegistered(final String name) {
		if(name == null) {
			return false;
		}

		return this.objects.containsKey(name.toLowerCase());
	}

	/**
	 * Returns the object mapped to the given name.
	 * @param name	the name of the object in the mapping.
	 * @return		The object mapped to the given name.
	 * 				If the name doesn't exist in the mapping,
	 * 				null will be returned.
	 */
	public Object getObject(final String name) {
		if(name == null) {
			return null;
		}

		return this.objects.get(name.toLowerCase());
	}
	
	/**
	 * Removes an object mapped to the given name.
	 * @param name	the name of the object that is desired to
	 * 				be removed. If the name is null.
	 */
	public void removeObject(final String name) {
		if(name == null) {
			return;
		}
		
		this.objects.remove(name.toLowerCase());
	}
	
	/**
	 * Checks if the mapper is empty.
	 * @return	True if the mapper is empty, otherwise false.
	 */
	public boolean isEmpty() {
		return this.objects.isEmpty();
	}
	
	/**
	 * Returns the size of the mapper. That is, the amount
	 * of objects mapped in the mapper.
	 * @return	the size of the mapper.
	 */
	public int size() {
		return this.objects.size();
	}
	
	/**
	 * Will clear the mapper from all the objects mapped.
	 */
	public void clear() { 
		this.objects.clear();
	}
}
