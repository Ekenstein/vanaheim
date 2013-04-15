package edu.cth.tmnd.vanaheim.tests;

import junit.framework.Assert;

import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;

public class InventoryTest {


	@Test
	public void addingItemTest() {
		final Inventory inv = new Inventory(1);
		final HealthPotion item = new HealthPotion();
		final Axe item2 = new Axe();

		Assert.assertEquals(1, inv.getSlots());
		Assert.assertEquals(inv.getSlots(), inv.getSlotsLeft());
		Assert.assertTrue(inv.isEmpty());
		Assert.assertTrue(inv.hasSlots());

		Assert.assertTrue(inv.addItem(item));
		Assert.assertEquals(0, inv.getSlotsLeft());
		Assert.assertFalse(inv.hasSlots());

		Assert.assertFalse(inv.addItem(item2));
		Assert.assertEquals(0, inv.getSlotsLeft());

		Assert.assertFalse(inv.addItem(item));
		Assert.assertEquals(0, inv.getSlotsLeft());
	}

	@Test
	public void destroyingItemTest() {
		final Inventory inv = new Inventory(1);
		final HealthPotion item1 = new HealthPotion();
		final Axe item2 = new Axe();


		inv.addItem(item1);
		Assert.assertFalse(inv.destroyItem(item2));
		Assert.assertFalse(inv.hasSlots());
		Assert.assertFalse(inv.isEmpty());
		Assert.assertEquals(0, inv.getSlotsLeft());

		Assert.assertTrue(inv.destroyItem(item1));
		Assert.assertTrue(inv.hasSlots());
		Assert.assertTrue(inv.isEmpty());
		Assert.assertEquals(1, inv.getSlotsLeft());

		Assert.assertFalse(inv.destroyItem(item1));
		Assert.assertEquals(1, inv.getSlotsLeft());
	}

}
