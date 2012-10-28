package gerk.vanaheim.misc;

import gerk.vanaheim.gfx.framework.impl.ViewState;

public class ViewStateList {
	private class ViewStateNode {
		private ViewStateNode parent, next;
		private ViewState content;
		
		public ViewStateNode(ViewStateNode parent, ViewStateNode next, ViewState content) {
			this.parent = parent;
			this.next = next;
			this.content = content;
		}
	}
	
	private ViewStateNode root = new ViewStateNode(null, null, null);
	private ViewStateNode current = this.root;
	
	public ViewState get() {
		return this.current.content;
	}
	
	public void next() {
		this.current = this.current.next;
	}
	
	public void prev() {
		this.current = this.current.parent;
	}
	
	public void add(ViewState s) {
		this.current.next = new ViewStateNode(this.current, null, s);
		this.current = this.current.next;
	}
	
	public void remove() {
		this.current = this.current.parent;
		this.current.next = null;
	}
	
	public boolean isRoot() {
		return this.current == this.root;
	}
}
