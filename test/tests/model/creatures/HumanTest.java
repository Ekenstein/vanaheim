package tests.model.creatures;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.Gold;
import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;
import edu.cth.tmnd.vanaheim.model.items.impl.QuestItem;
import edu.cth.tmnd.vanaheim.model.quests.GoldQuest;
import edu.cth.tmnd.vanaheim.model.quests.SilverQuest;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;

public class HumanTest {
	
	private float x, y = 1.0f;
	private int velocity = 100;
	private int maxHP = 100;
	private int dmg = 10;
	private String humanName = "TestHumanName";
	private boolean register = true;
	private Human testClass;
	private ObjectMapper objMapper = ObjectMapper.getInstance();

	private class TestClassHuman extends Human {
		public TestClassHuman(float x, float y, int velocity, int maxHP,
				String creatureName, boolean register) {
			super(x, y, velocity, maxHP,dmg, creatureName, register);
		}

		@Override
		public void talk(Human human) {
			
		}
	}
	
	@Before
	public void setUp() throws Exception {
		this.testClass = new TestClassHuman(x, y, velocity, maxHP, humanName, register);
	}
	
	@After
	public void tearDown() {
		this.objMapper.clear();
	}
	
	@Test
	public void addQuestItemTest(){
		QuestItem item = new Gold();
		Quest quest = new GoldQuest();
		
		Assert.assertFalse(this.testClass.hasQuest("Noquest"));
		
		this.testClass.addQuestItem("Noquest", item);
		Assert.assertFalse(this.testClass.hasQuest("Noquest"));
		
		this.testClass.addQuest(quest);
		
		Assert.assertTrue(this.testClass.hasQuest(quest.getName()));
		
		this.testClass.addQuestItem(quest.getName(), item);
		Assert.assertEquals(3, this.testClass.getRequiredItems(quest.getName(), "Gold"));
		Map<String, Integer> current = this.testClass.getQuestObjectives(quest.getName());
		for(Entry<String, Integer> e : current.entrySet()) {
			String itemName = e.getKey();
			Assert.assertEquals(item.getItemName(), itemName);
			Assert.assertTrue(1 == e.getValue());
		}
		
		Assert.assertFalse(this.testClass.isQuestCompleted(quest.getName()));
		
		this.testClass.addQuestItem(quest.getName(), item);
		this.testClass.addQuestItem(quest.getName(), item);
		current = this.testClass.getQuestObjectives(quest.getName());
		for(Entry<String, Integer> e : current.entrySet()) {
			String itemName = e.getKey();
			Assert.assertEquals(item.getItemName(), itemName);
			Assert.assertTrue(3 == e.getValue());
		}
		
		Assert.assertTrue(this.testClass.isQuestCompleted(quest.getName()));
	}
	
	@Test
	public void testGetQuests(){
		Assert.assertTrue(this.testClass.getQuests().isEmpty());
		
		Quest quest = new GoldQuest();
		this.testClass.addQuest(quest);
		Map<String,String> quests = this.testClass.getQuests();
		Assert.assertFalse(quests.isEmpty());
		Assert.assertEquals(1, quests.size());
		Assert.assertTrue(quests.containsKey(quest.getName()));
		
		Quest quest1 = new SilverQuest();
		this.testClass.addQuest(quest1);
		quests = this.testClass.getQuests();
		Assert.assertFalse(quests.isEmpty());
		Assert.assertEquals(2, quests.size());
		Assert.assertTrue(quests.containsKey(quest1.getName()));
		Assert.assertTrue(quests.containsKey(quest.getName()));
		
		this.testClass.removeQuest(quest1.getName());
		quests = this.testClass.getQuests();
		Assert.assertEquals(1, quests.size());
		Assert.assertTrue(quests.containsKey(quest.getName()));
		
		
		this.testClass.removeQuest(quest.getName());
		quests = this.testClass.getQuests();
		Assert.assertEquals(0, quests.size());
		Assert.assertTrue(quests.isEmpty());
		
		this.testClass.removeQuest(quest.getName());
		quests = this.testClass.getQuests();
		Assert.assertEquals(0, quests.size());
		Assert.assertTrue(quests.isEmpty());
	}
	
	@Test
	public void testGetDescription(){
		Assert.assertNull(this.testClass.getQuestDescription("test"));
		
		Quest quest = new GoldQuest();
		this.testClass.addQuest(quest);
		
		Assert.assertEquals(quest.getDescription(), this.testClass.getQuestDescription(quest.getName()));
		
		this.testClass.removeQuest(quest.getName());
		Assert.assertNull(this.testClass.getQuestDescription(quest.getName()));
	}
}
