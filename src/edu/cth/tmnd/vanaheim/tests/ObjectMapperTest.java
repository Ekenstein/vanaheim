package edu.cth.tmnd.vanaheim.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.ObjectMapper.Type;
import edu.cth.tmnd.vanaheim.model.creatures.monsters.Spider;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;

public class ObjectMapperTest {

	private ObjectMapper objectMapper;
	
	@Before
	public void setUp() throws Exception {
		this.objectMapper = ObjectMapper.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		this.objectMapper.clear();
	}

	@Test
	public void RegisterTest() {
		Assert.assertTrue(this.objectMapper.isEmpty());
		Assert.assertEquals(0, this.objectMapper.size());
		Player p = new Player(1f, 1f, 400, 100, "Harald");

		Assert.assertFalse(this.objectMapper.isEmpty());
		Assert.assertTrue(this.objectMapper.isRegistered(p.getName()).equals(Type.REGISTERED));
		Assert.assertTrue(this.objectMapper.getObject(p.getName()) instanceof Player);
		Assert.assertEquals(p, this.objectMapper.getObject(p.getName()));
		
		this.objectMapper.registerObject("@", p);
		
		Assert.assertTrue(this.objectMapper.isRegistered("@").equals(Type.REGISTERED));
		Assert.assertEquals(p, this.objectMapper.getObject("@"));
		
		Spider s = new Spider(1f, 1f, 400, 100);
		
		Assert.assertEquals(s, this.objectMapper.getObject(s.getName()));
		
		Assert.assertTrue(this.objectMapper.isRegistered("NOT IN THE OBJECT MAPPER") == Type.UNKNOWN);
	}

}
