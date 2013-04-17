package edu.cth.tmnd.vanaheim.tests;

import static org.junit.Assert.*;
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
		this.owner = new Player(32f, 32f, 300, new Inventory(2), 100);
		this.target = new Spider(40f, 40f, 300, new Inventory(0), 100);
		this.weapon = new Axe(this.owner);
		this.potion = new HealthPotion(this.owner);
		
	}
	
	@After
	public void tearDown() {
		this.weapon = new Axe(this.owner);
		this.potion = new HealthPotion(this.owner);
		this.owner = new Player(32f, 32f, 300, new Inventory(2), 100);
		this.target = new Spider(40f, 40f, 300, new Inventory(0), 100);
	}

	@Test
	public void HealthPotionTest() {
		this.owner.damage(5);
		Assert.assertEquals(95, this.owner.getHP());
		this.potion.use();
		Assert.assertEquals(100, this.owner.getHP());
		
		this.potion = new HealthPotion(this.owner);
		
		this.target.damage(6);
		Assert.assertEquals(94, this.target.getHP());
		this.potion.use(target);
		Assert.assertEquals(99, this.target.getHP());
	}

}
