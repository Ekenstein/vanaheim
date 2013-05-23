package tests.model.quests;

import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.items.Gold;
import edu.cth.tmnd.vanaheim.model.items.Silver;
import edu.cth.tmnd.vanaheim.model.quests.GoldQuest;
import edu.cth.tmnd.vanaheim.model.quests.SilverQuest;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class QuestTest {
	Quest q;
	
	@Before
	public void setUp() throws Exception {
		q = new GoldQuest();
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testGetNumberOfDifferentItemsNeeded(){
		Quest q = new GoldQuest();
		Assert.assertEquals(1, q.getNumberOfDifferentItemsNeeded());
	}
	
	@Test
	public void testGetItemsLeft(){
		Assert.assertEquals(3, q.getItemsLeft("Gold"));
		
		q.process(new Gold());
		Assert.assertEquals(2, q.getItemsLeft("Gold"));
		
		q.process(new Gold());
		Assert.assertEquals(1, q.getItemsLeft("Gold"));
		
		q.process(new Gold());
		Assert.assertEquals(0, q.getItemsLeft("Gold"));
		
		q.process(new Gold());
		Assert.assertEquals(0, q.getItemsLeft("Gold"));
	}
	
	@Test
	public void testRemoveItemFromQuest(){
		Quest q1 = new SilverQuest();
		q.removeItemFromQuest("Silver");
		q.addNeededItemCount("Silver", 1);
		Assert.assertEquals(2, q.getNumberOfDifferentItemsNeeded());
		
		q.removeItemFromQuest("Silver");
		Assert.assertEquals(1, q.getNumberOfDifferentItemsNeeded());
		
		q.removeItemFromQuest("Gold");
		Assert.assertEquals(0, q.getNumberOfDifferentItemsNeeded());
		
		q.removeItemFromQuest("Gold");
		Assert.assertEquals(0, q.getNumberOfDifferentItemsNeeded());
	}
	
	@Test
	public void testGetItemsCount(){
		Map<String, Integer> things = q.getItemsCount();
		
		Assert.assertEquals(1, things.size());
		Assert.assertTrue(3 == things.get("Gold"));
		
		q.process(new Gold());
		Assert.assertEquals(1, things.size());
		Assert.assertTrue(2 == things.get("Gold"));
		
		q.process(new Gold());
		Assert.assertEquals(1, things.size());
		Assert.assertTrue(1 == things.get("Gold"));
		
		q.process(new Gold());
		Assert.assertEquals(1, things.size());
		Assert.assertTrue(0 == things.get("Gold"));
		
		q.process(new Gold());
		Assert.assertEquals(1, things.size());
		Assert.assertTrue(0 == things.get("Gold"));
		
	}
	
	@Test
	public void testSetAndGetName(){
		Assert.assertEquals("Gold mining", q.getName());
		
		q.setName("Not silver");
		Assert.assertNotSame("Gold mining", q.getName());
		Assert.assertEquals("Not silver", q.getName());
	}
	
	@Test
	public void testIsComplete(){
		Assert.assertEquals(3, q.getItemsLeft("Gold"));
		Assert.assertEquals(false, q.isComplete());
		
		q.process(new Gold());
		Assert.assertEquals(2, q.getItemsLeft("Gold"));
		Assert.assertEquals(false, q.isComplete());
		
		q.process(new Gold());
		Assert.assertEquals(1, q.getItemsLeft("Gold"));
		Assert.assertEquals(false, q.isComplete());
		
		q.process(new Gold());
		Assert.assertEquals(0, q.getItemsLeft("Gold"));
		Assert.assertEquals(true, q.isComplete());
		
		q.process(new Gold());
		Assert.assertEquals(0, q.getItemsLeft("Gold"));
		Assert.assertEquals(true, q.isComplete());
	}
	
	@Test
	public void testGetAndSetDescription(){
		Assert.assertEquals("Mine three good lugs of gold and then return to me for your reward",
							q.getDescription());
		
		q.setDescription("Dont mine");
		Assert.assertEquals("Dont mine", q.getDescription());
	}
	
	@Test
	public void testEquals(){
		Assert.assertTrue(q.equals(q));
		
		Assert.assertFalse(q.equals(null));
		Assert.assertFalse(q.equals(new SilverQuest()));
		
		q.setName(null);
		Assert.assertFalse(q.equals(new GoldQuest()));
		
		Quest g = new GoldQuest();
		q.setName("Gold mining");
		g.setName("Not same");
		Assert.assertFalse(q.equals(g));
		
		g.setName("Gold mining");
		q.setName("Gold mining");
		Assert.assertTrue(q.equals(g));
	}
	
	@Test
	public void testHashCode(){
		Assert.assertEquals(q.hashCode(), q.hashCode());
		
		Assert.assertNotSame(q.hashCode(), new GoldQuest().hashCode());
		
		Assert.assertNotSame(q.hashCode(), new SilverQuest().hashCode());
		
		Assert.assertNotSame(q.hashCode(), new Silver().hashCode());
	}
}
