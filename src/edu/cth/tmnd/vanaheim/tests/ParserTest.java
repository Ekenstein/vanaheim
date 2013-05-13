package edu.cth.tmnd.vanaheim.tests;

import java.io.File;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.model.Inventory;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.monsters.Spider;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;
import edu.cth.tmnd.vanaheim.model.parser.Parser;
import edu.cth.tmnd.vanaheim.model.parser.Parser.Segment;

public class ParserTest {

	private Parser p;
	
	private File f = new File("data/commands");
	
	@Before
	public void setUp() throws Exception {
		Player p = new Player(1f, 1f, 400, 100, "Harald");
		ObjectMapper.getInstance().registerObject(Constants.PLAYER_OBJECT_NAME, p);
		this.p = Parser.getInstance(this.f);
	}

	@After
	public void tearDown() throws Exception {
		Player p = (Player)ObjectMapper.getInstance().getObject(Constants.PLAYER_OBJECT_NAME);
		p.heal(p.getMaxHP());
	}
	
	@Test
	public void testAttack() throws SlickException {
		// registers spider to the object mapper
		Spider s = new Spider(1f, 1f, 400, 100, 10, true);
		
		this.p.parse("hit furious spider with crude axe");
		
		Assert.assertTrue(s.getCurrentHP() == 90);
	}
	
	@Test
	public void testDrinking() {
		Player p = (Player) ObjectMapper.getInstance().getObject(Constants.PLAYER_OBJECT_NAME);
		
		p.damage(4);
		Assert.assertTrue(p.getCurrentHP() == 96);
		
		this.p.parse("drink Healing Potion");
		
		Assert.assertTrue(p.getCurrentHP() == p.getMaxHP());
	}
}
