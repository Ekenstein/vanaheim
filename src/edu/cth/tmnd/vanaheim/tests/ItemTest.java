package edu.cth.tmnd.vanaheim.tests;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.creatures.monsters.Spider;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;

public class ItemTest {

	private Axe weapon;
	private HealthPotion potion;
	private Player owner;
	private Spider target;

	@Before
	public void setUp() throws Exception {
		this.owner = new Player(32f, 32f, 300, 100, "Harald");
		this.target = new Spider(40f, 40f, 300, 100, true);
		this.weapon = new Axe();
		this.potion = new HealthPotion();

	}

	@After
	public void tearDown() {
		this.weapon = new Axe();
		this.potion = new HealthPotion();
		this.owner = new Player(32f, 32f, 300, 100, "Harald");
		this.target = new Spider(40f, 40f, 300, 100,true);
	}

	@Test
	public void HealthPotionTest() {
		this.owner.damage(5);
		Assert.assertEquals(95, this.owner.getCurrentHP());
		this.potion.drink(owner);
		Assert.assertEquals(100, this.owner.getCurrentHP());
		Assert.assertEquals(0, this.potion.getCurrentDurability());

		this.potion.repair();
		Assert.assertEquals(this.potion.getDurability(), this.potion.getCurrentDurability());

		this.target.damage(6);
		Assert.assertEquals(94, this.target.getCurrentHP());
		this.potion.drink(owner);
		Assert.assertEquals(99, this.target.getCurrentHP());
	}

	@Test
	public void AxeTest() {
		this.weapon.attack(owner, this.target);

		Assert.assertEquals(90, this.target.getCurrentHP());
		Assert.assertEquals(this.weapon.getDurability()-1, this.weapon.getCurrentDurability());
		this.weapon.repair();
		Assert.assertEquals(this.weapon.getDurability(), this.weapon.getCurrentDurability());
	}

}
