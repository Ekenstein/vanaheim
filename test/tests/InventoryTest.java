package tests;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.Gold;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public class InventoryTest {

	private Inventory inventory;
	private int slots;
	private Item item1;
	private Item item2;
	
	@Before
	public void setUp() throws Exception {
		this.slots = 2;
		this.inventory = new Inventory(slots);
		this.item1 = new Axe();
		this.item2 = new HealthPotion();
	}

	@After
	public void tearDown() throws Exception {
		ObjectMapper.getInstance().clear();
	}

	@Test
	public void addItemTest() {
		Assert.assertTrue(this.inventory.isEmpty());
		Assert.assertEquals(this.slots, this.inventory.getSlots());
		Assert.assertEquals(this.slots, this.inventory.getSlotsLeft());
		
		Assert.assertFalse(this.inventory.addItem(null));
		Assert.assertEquals(this.slots, this.inventory.getSlotsLeft());
		
		Assert.assertTrue(this.inventory.addItem(this.item1));
		Assert.assertEquals(1, this.inventory.getSlotsLeft());
		
		Assert.assertTrue(this.inventory.addItem(this.item2));
		Assert.assertEquals(0, this.inventory.getSlotsLeft());
		Assert.assertFalse(this.inventory.hasSlots());
		Assert.assertFalse(this.inventory.addItem(this.item1));
		Assert.assertFalse(this.inventory.isEmpty());
	}
	
	@Test
	public void getSlotsTest() {
		Assert.assertEquals(this.slots, this.inventory.getSlots());
		Assert.assertTrue(this.inventory.isEmpty());
	}
	
	@Test
	public void getItemsTest() {
		Assert.assertEquals(0, this.inventory.getItems().size());
		
		this.inventory.addItem(this.item1);
		
		Assert.assertEquals(1, this.inventory.getItems().size());
		Assert.assertEquals(1, this.inventory.getSlotsLeft());
		
		this.inventory.addItem(this.item2);
		
		List<Item> items = this.inventory.getItems();
		
		Assert.assertEquals(0, this.inventory.getSlotsLeft());
		
		Assert.assertEquals(this.slots, items.size());
		
		Assert.assertEquals(this.item1, items.get(0));
		Assert.assertEquals(this.item2, items.get(1));
	}

	@Test
	public void retreiveItemsTest() {
		this.inventory.addItem(this.item1);
		this.inventory.addItem(this.item2);
		Assert.assertEquals(this.slots-2, this.inventory.getSlotsLeft());
		
		List<Item> items = this.inventory.retreiveItems();
		
		Assert.assertEquals(this.slots, items.size());
		
		Assert.assertEquals(this.slots, this.inventory.getSlotsLeft());
		
		Assert.assertEquals(this.item1, items.get(0));
		Assert.assertEquals(this.item2, items.get(1));
		
		Assert.assertEquals(this.slots-2, this.inventory.retreiveItems().size());
	}
	
	@Test
	public void retreiveItemTest() {
		this.inventory.addItem(this.item1);
		this.inventory.addItem(this.item2);
		Assert.assertEquals(this.slots-2, this.inventory.getSlotsLeft());
		
		Assert.assertEquals(this.item1, this.inventory.retrieveItem(this.item1));
		Assert.assertEquals(this.slots-1, this.inventory.getSlotsLeft());
		
		Assert.assertEquals(this.item2, this.inventory.retrieveItem(this.item2));
		Assert.assertEquals(this.slots, this.inventory.getSlotsLeft());
		
		Assert.assertEquals(null, this.inventory.retrieveItem(this.item1));
		Assert.assertEquals(this.slots, this.inventory.getSlotsLeft());
		
		Assert.assertEquals(null, this.inventory.retrieveItem(null));
	}
	
	@Test
	public void getItemTest() {
		this.inventory.addItem(this.item1);
		this.inventory.addItem(this.item2);
		Assert.assertEquals(this.slots-2, this.inventory.getSlotsLeft());
		
		Assert.assertEquals(this.item1, this.inventory.getItem(this.item1));
		Assert.assertEquals(this.slots-2, this.inventory.getSlotsLeft());
		
		Assert.assertEquals(this.item2, this.inventory.getItem(this.item2));
		Assert.assertEquals(this.slots-2, this.inventory.getSlotsLeft());
		
		Assert.assertEquals(null, this.inventory.getItem(new Gold()));
		
		Assert.assertEquals(this.item1, this.inventory.getItem(this.item1.getItemName()));
		Assert.assertEquals(this.slots-2, this.inventory.getSlotsLeft());
		
		Assert.assertEquals(this.item2, this.inventory.getItem(this.item2.getItemName()));
		Assert.assertEquals(this.slots-2, this.inventory.getSlotsLeft());
		
		this.inventory.retreiveItems();
		
		Assert.assertEquals(null, this.inventory.getItem(this.item1.getItemName()));
	}
	
	@Test
	public void addItemIsRegisteredTest() {
		Item item1 = new Axe();
		
		ObjectMapper objMapper = ObjectMapper.getInstance();
		
		Assert.assertFalse(objMapper.isRegistered(item1.getItemName()));
		
		this.inventory.addItem(item1);
		
		Assert.assertTrue(objMapper.isRegistered(item1.getItemName()));
	}
}
