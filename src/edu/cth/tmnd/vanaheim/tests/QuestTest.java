package edu.cth.tmnd.vanaheim.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.creatures.monsters.Spider;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.Gold;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.quests.ExploreQuest;
import edu.cth.tmnd.vanaheim.model.quests.GoldQuest;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class QuestTest {
	
	Quest q1;
	Creature owner;
	

	@Before
	public void setUp() throws Exception {
		this.owner = new NPC(0f,0f,0,new Inventory(20),20,"Putte");
		this.q1 = new GoldQuest(owner);
		
	}
	
	@After
	public void tearDown() {
		owner = null;
		q1 = null;
	}
	

	
	@Test
	public void testAddNewItem(){
		Assert.assertEquals(1, q1.getNumberOfDifferentItemsNeeded());
		Assert.assertEquals(3, q1.getItemsLeft("Gold"));
		q1.addNeededItemCount("Gold", 1);
		Assert.assertEquals(1, q1.getNumberOfDifferentItemsNeeded());
		
		q1.addNeededItemCount("Silver", 3);
		Assert.assertEquals(2, q1.getNumberOfDifferentItemsNeeded());
		Assert.assertEquals(1, q1.getItemsLeft("Gold"));
		Assert.assertEquals(3, q1.getItemsLeft("Silver"));
		
		q1.addNeededItemCount("Copper", 8);
		Assert.assertEquals(3, q1.getNumberOfDifferentItemsNeeded());
		Assert.assertEquals(1, q1.getItemsLeft("Gold"));
		
		
		Assert.assertEquals(3, q1.getItemsLeft("Silver"));
		Assert.assertEquals(8, q1.getItemsLeft("Copper"));
	}
	
	@Test
	public void testRemoveItem(){
		Assert.assertEquals(1, q1.getNumberOfDifferentItemsNeeded());
		q1.removeItemFromQuest("Gold");
		Assert.assertEquals(0, q1.getNumberOfDifferentItemsNeeded());
		
		q1.removeItemFromQuest("Gold");
		Assert.assertEquals(0, q1.getNumberOfDifferentItemsNeeded());
		
		q1.addNeededItemCount("Tree", 2);
		Assert.assertEquals(1, q1.getNumberOfDifferentItemsNeeded());
		q1.removeItemFromQuest("Tree");
		Assert.assertEquals(0, q1.getNumberOfDifferentItemsNeeded());
	}
	
	@Test
	public void testProcessItem(){
		Assert.assertEquals(3, q1.getItemsLeft("Gold"));
		q1.process(new Gold(owner));
		Assert.assertEquals(2, q1.getItemsLeft("Gold"));
		
		q1.process(new Gold(owner));
		Assert.assertEquals(1, q1.getItemsLeft("Gold"));
		
		q1.process(new Gold(owner));
		Assert.assertEquals(0, q1.getItemsLeft("Gold"));
		
		q1.process(new Gold(owner));
		Assert.assertEquals(0, q1.getItemsLeft("Gold"));
		
		q1.addNeededItemCount("Gold", 1);
		Assert.assertEquals(1, q1.getItemsLeft("Gold"));
		
		q1.process(new Gold(owner));
		Assert.assertEquals(0, q1.getItemsLeft("Gold"));

	}

	@Test
	public void testCompleteQuest(){
		Assert.assertFalse(q1.isComplete());
		q1.process(new Gold(owner));
		Assert.assertFalse(q1.isComplete());
		
		q1.process(new Gold(owner));
		Assert.assertFalse(q1.isComplete());
		
		q1.process(new Gold(owner));
		Assert.assertTrue(q1.isComplete());
		
		q1.process(new Gold(owner));
		Assert.assertTrue(q1.isComplete());
	}
}
