package tests.model.creatures.player;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;
import edu.cth.tmnd.vanaheim.model.items.impl.DrinkableItem;
import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public class TestPlayer {
	
	Player p;

	@Before
	public void setUp() throws SlickException{
		p = new Player(1.0f, 1.0f, 1, 1, "Tjosan");
	}
	
	@After
	public void tearDown(){
		p.clearInventory();
	}
	
	@Test
	public void testGetItems(){
		EquipableItem item1 = new Axe();
		DrinkableItem item2 = new HealthPotion();
		p.clearInventory();
		
		Assert.assertEquals(p.getInventorySlots(), p.getInventorySlotsLeft());
		
		p.addItem(item1);
		Assert.assertEquals(p.getItems().get(0), item1);
		Assert.assertEquals(1, p.getItems().size());
		
		p.addItem(item2);
		Assert.assertEquals(p.getItems().get(1), item2);
		Assert.assertEquals(2, p.getItems().size());
	}
	
	@Test
	public void testToggleInventory(){
		if(p.isInventoryToggled()){
			p.toggleInventory();
			Assert.assertFalse(p.isInventoryToggled());
		}
		if(!p.isInventoryToggled()){
			p.toggleInventory();
			Assert.assertTrue(p.isInventoryToggled());
		}
	}
	
	@Test
	public void testToggleQuestbook(){
		if(p.isQuestBookToggled()){
			p.toggleQuestBook();
			Assert.assertFalse(p.isInventoryToggled());
		}
		if(!p.isQuestBookToggled()){
			p.toggleQuestBook();
			Assert.assertTrue(p.isQuestBookToggled());
		}
	}
	
}
