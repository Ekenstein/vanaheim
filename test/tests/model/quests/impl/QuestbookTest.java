package tests.model.quests.impl;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.MessageBuffer;
import edu.cth.tmnd.vanaheim.model.items.Gold;
import edu.cth.tmnd.vanaheim.model.items.Silver;
import edu.cth.tmnd.vanaheim.model.items.impl.QuestItem;
import edu.cth.tmnd.vanaheim.model.quests.GoldQuest;
import edu.cth.tmnd.vanaheim.model.quests.SilverQuest;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;
import edu.cth.tmnd.vanaheim.model.quests.impl.QuestBook;

public class QuestbookTest {

	private QuestBook questBook;
	private Quest quest1;
	private Quest quest2;
	private QuestItem item1;
	private QuestItem item2;
	
	@Before
	public void setUp() throws Exception {
		this.questBook = new QuestBook();
		this.quest1 = new GoldQuest();
		this.quest2 = new SilverQuest();
		this.item1 = new Gold();
		this.item2 = new Silver();
	}

	@After
	public void tearDown() throws Exception {
		MessageBuffer.getInstance().clear();
	}

	@Test
	public void addQuestTest() {
		this.questBook.addQuest(this.quest1);
		Assert.assertTrue(this.questBook.hasQuest(this.quest1.getName()));
		this.questBook.addQuest(this.quest1);
		this.questBook.removeQuest(this.quest1.getName());
		Assert.assertFalse(this.questBook.hasQuest(this.quest1.getName()));
	}
	
	@Test
	public void removeQuestTest() {
		this.questBook.addQuest(this.quest1);
		Assert.assertTrue(this.questBook.hasQuest(this.quest1.getName()));
		this.questBook.removeQuest(this.quest1.getName());
		Assert.assertFalse(this.questBook.hasQuest(this.quest1.getName()));
	}
	
	@Test
	public void getQuestDescriptionTest() {
		Assert.assertNull(this.questBook.getQuestDescription("Doesn't exist!"));
		
		this.questBook.addQuest(this.quest1);
		
		Assert.assertEquals(this.quest1.getDescription(), this.questBook.getQuestDescription(this.quest1.getName()));
		
	}
	
	@Test
	public void sizeTest() {
		Assert.assertEquals(0, this.questBook.size());
		Assert.assertTrue(this.questBook.isEmpty());
		this.questBook.addQuest(this.quest1);
		Assert.assertEquals(1, this.questBook.size());
		Assert.assertFalse(this.questBook.isEmpty());
	}

	@Test
	public void addQuestItemTest() {
		this.questBook.addQuest(this.quest1);
		this.questBook.addQuestItem(this.quest1.getName(), this.item1);
		java.util.Map<String, Integer> current = this.questBook.getQuestObjectives(this.quest1.getName());
		
		for(java.util.Map.Entry<String, Integer> e : current.entrySet()) {
			Assert.assertTrue(1 == e.getValue());
		}
		
		this.questBook.addQuestItem(this.quest1.getName(), this.item1);
		current = this.questBook.getQuestObjectives(this.quest1.getName());
		
		for(java.util.Map.Entry<String, Integer> e : current.entrySet()) {
			Assert.assertTrue(2 == e.getValue());
		}
		
		this.questBook.addQuestItem(this.quest1.getName(), this.item1);
		current = this.questBook.getQuestObjectives(this.quest1.getName());
		
		for(java.util.Map.Entry<String, Integer> e : current.entrySet()) {
			Assert.assertTrue(3 == e.getValue());
		}
		
		this.questBook.addQuestItem(this.quest1.getName(), this.item1);
		current = this.questBook.getQuestObjectives(this.quest1.getName());
		
		for(java.util.Map.Entry<String, Integer> e : current.entrySet()) {
			Assert.assertTrue(3 == e.getValue());
		}
	}
	
	@Test
	public void isCompleteTest() {
		Assert.assertFalse(this.questBook.isComplete(this.quest1.getName()));
		this.questBook.addQuest(this.quest1);
		Assert.assertFalse(this.questBook.isComplete(this.quest1.getName()));
		
		int limit = this.questBook.getRequiredItems(this.quest1.getName(), this.item1.getItemName());
		
		for(int i = 0; i < limit; i++) {
			this.questBook.addQuestItem(this.quest1.getName(), this.item1);
		}
		
		Assert.assertTrue(this.questBook.isComplete(this.quest1.getName()));
	}
	
	@Test
	public void showIncompleteQuestsTest(){
		Assert.assertEquals(0, this.questBook.showIncompleteQuests().size());
		
		this.questBook.addQuest(this.quest1);
		Assert.assertEquals(1, this.questBook.showIncompleteQuests().size());
		Assert.assertTrue(this.questBook.showIncompleteQuests().containsKey(this.quest1.getName()));
		
		int limit = this.quest1.getRequiredItems(this.item1.getItemName());
		
		for(int i = 0; i < limit; i++) {
			this.questBook.addQuestItem(this.quest1.getName(), this.item1);
		}
		
		Assert.assertEquals(0, this.questBook.showIncompleteQuests().size());
		Assert.assertFalse(this.questBook.showIncompleteQuests().containsKey(this.quest1.getName()));
	}
	
	@Test
	public void showCompleteQuestsTest(){
		Assert.assertEquals(0, this.questBook.showCompleteQuests().size());
		
		this.questBook.addQuest(this.quest1);
		Assert.assertEquals(0, this.questBook.showCompleteQuests().size());
		Assert.assertFalse(this.questBook.showCompleteQuests().containsKey(this.quest1.getName()));
		
		int limit = this.quest1.getRequiredItems(this.item1.getItemName());
		
		for(int i = 0; i < limit; i++) {
			this.questBook.addQuestItem(this.quest1.getName(), this.item1);
		}
		
		Assert.assertEquals(1, this.questBook.showCompleteQuests().size());
		Assert.assertTrue(this.questBook.showCompleteQuests().containsKey(this.quest1.getName()));
	}
	
	@Test
	public void getQuestsTest(){
		Assert.assertEquals(0, this.questBook.getQuests().size());
		this.questBook.addQuest(this.quest1);
		Assert.assertEquals(1, this.questBook.getQuests().size());
		Assert.assertTrue(this.questBook.getQuests().containsKey(quest1.getName()));
		this.questBook.addQuest(this.quest2);
		Assert.assertEquals(2, this.questBook.getQuests().size());
		Assert.assertTrue(this.questBook.getQuests().containsKey(quest1.getName()));
		Assert.assertTrue(this.questBook.getQuests().containsKey(quest2.getName()));
		this.questBook.removeQuest(quest2.getName());
		Assert.assertTrue(this.questBook.getQuests().containsKey(quest1.getName()));
		Assert.assertFalse(this.questBook.getQuests().containsKey(quest2.getName()));
	}
}
