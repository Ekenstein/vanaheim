package edu.cth.tmnd.vanaheim.tests;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Animation;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public class InventoryTest {

	private Inventory inventory;
	private Axe item1;
	private HealthPotion item2;
	private int slots;
	private Player owner;
	
	@Before
	public void setUp() {
		this.slots = 2;
		this.inventory = new Inventory(this.slots);
		this.owner = new Player((float)4, (float)4, new Animation[4], (float)5, this.inventory, 5);
		this.item1 = new Axe(this.owner);
		this.item2 = new HealthPotion(this.owner);
	}
	
	@After
	public void tearDown() {
		this.inventory = new Inventory(this.slots);
	}

	@Test
	public void addingItemTest() {
		Assert.assertEquals(this.slots, this.inventory.getSlots());
		Assert.assertTrue(this.inventory.isEmpty());
		
		Assert.assertTrue(this.inventory.addItem(this.item1));
		Assert.assertEquals(1, this.inventory.getSlotsLeft());
		Assert.assertFalse(this.inventory.isEmpty());
		
		Assert.assertTrue(this.inventory.addItem(this.item2));
		Assert.assertFalse(this.inventory.isEmpty());
		Assert.assertEquals(0, this.inventory.getSlotsLeft());
	}
	
	@Test
	public void retreivingItemTest() {
		this.inventory.addItem(this.item1);
		this.inventory.addItem(this.item2);
		
		Item item = this.inventory.retrieveItem(this.item1);
		Assert.assertEquals(this.item1, item);
		Assert.assertEquals(1, this.inventory.getSlotsLeft());
		Assert.assertEquals(this.owner, item.getOwner());
		Assert.assertFalse(this.inventory.isEmpty());
		
		item = this.inventory.retrieveItem(this.item1);
		
		Assert.assertEquals(null, item);
		Assert.assertEquals(1, this.inventory.getSlotsLeft());
		Assert.assertFalse(this.inventory.isEmpty());
		
		item = this.inventory.retrieveItem(this.item2);
		Assert.assertEquals(this.item2, item);
		Assert.assertEquals(2, this.inventory.getSlotsLeft());
		Assert.assertEquals(this.owner, item.getOwner());
		Assert.assertTrue(this.inventory.isEmpty());
	}

	@Test
	public void destroyingItemTest() {
		this.inventory.addItem(this.item1);
		this.inventory.addItem(this.item2);
		
		Assert.assertFalse(this.inventory.hasSlots());
		Assert.assertTrue(this.inventory.destroyItem(this.item1));
		Assert.assertFalse(this.inventory.isEmpty());
		Assert.assertTrue(this.inventory.hasSlots());
		Assert.assertEquals(1 ,this.inventory.getSlotsLeft());
	
		Assert.assertFalse(this.inventory.destroyItem(this.item1));
		Assert.assertFalse(this.inventory.isEmpty());
		Assert.assertTrue(this.inventory.hasSlots());
		Assert.assertEquals(1 ,this.inventory.getSlotsLeft());
		
		Assert.assertTrue(this.inventory.destroyItem(this.item2));
		Assert.assertTrue(this.inventory.isEmpty());
		Assert.assertTrue(this.inventory.hasSlots());
		Assert.assertEquals(2 ,this.inventory.getSlotsLeft());
		
	}
	
	@Test
	public void dropItem() {
		
	}

}
