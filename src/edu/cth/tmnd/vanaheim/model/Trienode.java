package edu.cth.tmnd.vanaheim.model;

import java.util.Map;

public class Trienode {

	Trienode parent;
	String word;
	String method;
	Map<String, Trienode> children;
	
	public Trienode(){
		
	}
	
	public void invoke(GameObject target){
		
	}
}
