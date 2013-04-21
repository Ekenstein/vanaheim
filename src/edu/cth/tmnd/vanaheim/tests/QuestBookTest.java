package edu.cth.tmnd.vanaheim.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Human;
import edu.cth.tmnd.vanaheim.model.creatures.npc.impl.NPC;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.quests.GoldQuest;
import edu.cth.tmnd.vanaheim.model.quests.SilverQuest;
import edu.cth.tmnd.vanaheim.model.quests.impl.Quest;
import edu.cth.tmnd.vanaheim.model.quests.impl.QuestBook;

public class QuestBookTest {

	private Quest q1;
	private Quest q2;
	private Quest q3;
	private Human user;
	private Human npc1;
	private Human npc2;
	

	@Before
	public void setUp() throws Exception {
		this.npc1 = new NPC(0f,0f,0,new Inventory(20),20,"Putte",null);
		this.npc2 = new NPC(0f,0f,0,new Inventory(20),20,"Fnutte",null);
		this.user = new Player(0f,0f,0,new Inventory(20),20,"Gurdi");
		this.q1 = new GoldQuest(npc1);
		this.q2 = new GoldQuest(npc2);
		this.q3 = new SilverQuest(npc2);
		
		
	}
	
	@After
	public void tearDown() {

	}
	
	
	@Test
	public void testAddQuest(){
		Assert.assertTrue(user.getQuestBook().isEmpty());
		
		user.getQuestBook().addQuest(q1);
		Assert.assertFalse(user.getQuestBook().isEmpty());
		Assert.assertEquals(1, user.getQuestBook().size());
		for(Quest quest: user.getQuestBook().showIncompleteQuests().values())
			Assert.assertSame(q1, quest);
		
		user.getQuestBook().addQuest(q1);
		Assert.assertFalse(user.getQuestBook().isEmpty());
		Assert.assertEquals(1, user.getQuestBook().size());
		for(Quest quest: user.getQuestBook().showIncompleteQuests().values())
			Assert.assertSame(q1, quest);
		
		user.getQuestBook().addQuest(q3);
		Assert.assertFalse(user.getQuestBook().isEmpty());
		Assert.assertEquals(2, user.getQuestBook().size());
		
		
	}
	
	
	
	
}	
