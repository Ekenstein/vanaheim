package edu.cth.tmnd.vanaheim.model.parser;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import edu.cth.tmnd.vanaheim.model.GameObject;

public class Trie {

	private TrieNode root;
	private TrieNode current;

	public Trie(){

	}

	public void interpret(final String command, final GameObject target){

	}

	public void init(final File command){

	}

	private class TrieNode {
		private final String key;
		private final Map<String, TrieNode> children = new HashMap<String, TrieNode>();
		private final TrieNode parent;
		private final String method;
		private final String clazz;
		private Object o;

		public TrieNode(final String key, final TrieNode parent, final String clazz, final String method) {
			this.key = key;
			this.parent = parent;
			this.clazz = clazz;
			this.method = method;
		}

		public boolean setObject(final Object o) {
			try {
				final Class<?> cls = Class.forName(this.clazz);

				if(cls.isInstance(o)) {
					this.o = o;
					return true;
				} else {
					return false;
				}
			} catch(final ClassCastException e) {
				return false;
			} catch (final ClassNotFoundException e) {
				return false;
			}
		}

		public void run() {
			try {
				// we need to get all the arguments to the method from the parents.
				final ArrayList<Class<?>> parentClazzes = new ArrayList<Class<?>>();
				final ArrayList<Object> parentObjects = new ArrayList<Object>();

				// fetch parents objects and clazzes.
				for(TrieNode parent = this.parent; parent != null; parent = parent.parent) {
					parentClazzes.add(parent.o.getClass());
					parentObjects.add(parent.o);
				}

				// If there are more than one parent, we need to reverse
				// the list since we are invoking down to up on their objects.
				if(parentClazzes.size() > 1) {
					Collections.reverse(parentClazzes);
					Collections.reverse(parentObjects);
				}

				// cast the list to arrays so we can use it when invoking
				// and creating methods through reflection
				final Class<?>[] methodClazzes = new Class<?>[parentClazzes.size()];
				final Object[] methodObjects = new Object[parentObjects.size()];

				parentClazzes.toArray(methodClazzes);
				parentObjects.toArray(methodObjects);

				// get the method
				final Method m = this.o.getClass().getDeclaredMethod(this.method, methodClazzes);

				// invoke the method
				m.invoke(this.o, methodObjects);
			} catch(final ClassCastException e) {
				return;
			} catch (final NoSuchMethodException e) {
				e.printStackTrace();
			} catch (final SecurityException e) {
				e.printStackTrace();
			} catch (final IllegalAccessException e) {
				e.printStackTrace();
			} catch (final IllegalArgumentException e) {
				e.printStackTrace();
			} catch (final InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean hasNode(final String key) {
		return false;
	}
}
