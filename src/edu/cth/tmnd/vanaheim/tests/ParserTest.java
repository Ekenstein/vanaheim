package edu.cth.tmnd.vanaheim.tests;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	}

	@Test
	public void splitTest() {
		try {
			Method splitMethod = Parser.class.getDeclaredMethod("split", String.class);
			splitMethod.setAccessible(true);
			String[] actuals = (String[])splitMethod.invoke(this.p, "use crude axe on super duper ogre");
			String[] expected = new String[] {"use", "crude axe", "on", "super duper ogre"};
			Assert.assertTrue(Arrays.deepEquals(actuals, expected));
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void prefixesTest() {
		try {
			Field prefixesField = Parser.class.getDeclaredField("prefixes");
			prefixesField.setAccessible(true);
			
			ArrayList<String> actuals = (ArrayList<String>) prefixesField.get(this.p);
			ArrayList<String> expected = new ArrayList<String>();
			expected.add("use");
			expected.add("on");
			expected.add("eat");
			expected.add("drink");
			expected.add("hit");
			expected.add("with");
			
			Assert.assertEquals(expected.size(), actuals.size());
			
			for(String s : expected) {
				Assert.assertTrue(actuals.contains(s));
			}
			
			
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void testParser() {
		// registers spider to the object mapper
		Spider s = new Spider(1f, 1f, 400, 100);
		
		// registers player to the object mapper and all his items.
		Player p = new Player(1f, 1f, 400, 100, "Harald");
		ObjectMapper.getInstance().registerObject(Constants.PLAYER_OBJECT_NAME, p);
		
		this.p.parse("hit furious spider with crude axe");
		
		Assert.assertTrue(s.getCurrentHP() == 90);
	}
}
