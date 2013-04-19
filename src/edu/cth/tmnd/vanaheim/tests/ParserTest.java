package edu.cth.tmnd.vanaheim.tests;

import java.io.File;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.monsters.Spider;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;
import edu.cth.tmnd.vanaheim.model.parser.Parser;

public class ParserTest {

	private Parser p;
	private Player owner;
	private Spider target;
	private ObjectMapper om;
	private HealthPotion item1;
	private Axe item2;

	@Before
	public void setUp() throws Exception {
		this.p = Parser.getInstance(new File("data/commands"));
		this.owner = new Player(32f, 32f, 100, new Inventory(20), 100, "Harald");
		this.target = new Spider(32f, 32f, 100, new Inventory(20), 100, "spider");
		this.item1 = new HealthPotion(this.owner);
		this.item2 = new Axe(this.owner);
		this.om = ObjectMapper.getInstance();
		this.om.registerCreature(this.target);
		this.om.registerItem(this.item1);
		this.om.registerItem(this.item2);
	}

	@After
	public void tearDown() throws Exception {
		this.target = new Spider(32f, 32f, 100, new Inventory(20), 100, "spider");
	}

	@Test
	public void UseCommandTest() {
		this.p.parse("use crude axe on spider");

		Assert.assertEquals(90, this.target.getHP());

		this.p.parse("use healing potion on spider");

		Assert.assertEquals(95, this.target.getHP());
	}

	@Test
	public void AttackCommandTest() {
		this.p.parse("hit spider with crude axe");

		Assert.assertEquals(90, this.target.getHP());
	}
}
