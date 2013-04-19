package edu.cth.tmnd.vanaheim.model.Trie;

import java.io.File;
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

		public String getClazz() {
			return this.clazz;
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

				for(TrieNode parent = this.parent; parent != null; parent = parent.parent) {
					parentClazzes.add(parent.o.getClass());
				}

				Collections.reverse(parentClazzes);
				final Class<?>[] methodArguments = new Class<?>[parentClazzes.size()];
				parentClazzes.toArray(methodArguments);
				final Method m = this.o.getClass().getMethod(this.method, methodArguments);
				//m.invoke(this.o);
			} catch(final ClassCastException e) {
				return;
			} catch (final NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for(TrieNode parent = this.parent; parent != null; parent = parent.parent) {

			}
		}
	}

	public boolean hasNode(final String key) {
		return false;
	}
}
