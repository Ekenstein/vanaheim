package tests.model;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.Gold;
import edu.cth.tmnd.vanaheim.model.items.HealthPotion;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public class ObjectMapperTest {

	@Before
	public void setUp() throws Exception {
		ObjectMapper.getInstance().clear();
	}

	@After
	public void tearDown() throws Exception {
		ObjectMapper.getInstance().clear();
	}

	@Test
	public void getInstanceTest() {
		ObjectMapper objMapper = ObjectMapper.getInstance();
		
		Assert.assertTrue(objMapper != null);
		
		Assert.assertEquals(objMapper, ObjectMapper.getInstance());
		Assert.assertTrue(objMapper == ObjectMapper.getInstance());
	}

	@Test
	public void registerObjectTest() {
		ObjectMapper objMapper = ObjectMapper.getInstance();
		
		Item item1 = new Axe();
		
		objMapper.registerObject(item1.getItemName(), item1);
		Assert.assertTrue(objMapper.isRegistered(item1.getItemName()));
		Item fetchedItem = (Item) objMapper.getObject(item1.getItemName());
		Assert.assertTrue(fetchedItem != null);
		Assert.assertEquals(item1, fetchedItem);
		Assert.assertTrue(objMapper.isRegistered(item1.getItemName()));
		Assert.assertEquals(1, objMapper.size());
		
		Item item2 = new HealthPotion();
		objMapper.registerObject(item1.getItemName(), item2);
		Assert.assertTrue(objMapper.isRegistered(item1.getItemName()));
		fetchedItem = (Item) objMapper.getObject(item1.getItemName());
		Assert.assertTrue(fetchedItem != null);
		Assert.assertEquals(item2, fetchedItem);
		Assert.assertEquals(1, objMapper.size());
	
		Gold g = new Gold();
		
		objMapper.registerObject(null, g);
		Assert.assertEquals(1, objMapper.size());
	
		objMapper.registerObject(g.getItemName(), null);
		Assert.assertEquals(1, objMapper.size());
		
		objMapper.registerObject(item1.getItemName(), item1);
		objMapper.registerObject(item2.getItemName(), item2);
		
		Assert.assertEquals(2, objMapper.size());
		
		objMapper.registerObject("Test", item1);
		Assert.assertEquals(3, objMapper.size());
		
		Assert.assertTrue(objMapper.isRegistered("Test"));
		Assert.assertEquals(item1, objMapper.getObject("Test"));
	}
	
	@Test
	public void isRegisteredTest() {
		ObjectMapper objMapper = ObjectMapper.getInstance();
		Item item1 = new Axe();
		Item item2 = new HealthPotion();
		Item item3 = new Gold();
		
		objMapper.registerObject(item1.getItemName(), item1);
		objMapper.registerObject(item2.getItemName(), item2);
		
		Assert.assertTrue(objMapper.isRegistered(item1.getItemName()));
		Assert.assertTrue(objMapper.isRegistered(item2.getItemName()));
		
		Assert.assertFalse(objMapper.isRegistered(null));
		Assert.assertFalse(objMapper.isRegistered(item3.getItemName()));
	}
	
	@Test
	public void getObjectTest() {
		ObjectMapper objMapper = ObjectMapper.getInstance();
		Item item1 = new Axe();
		Item item2 = new HealthPotion();
		Item item3 = new Gold();
		
		objMapper.registerObject(item1.getItemName(), item1);
		objMapper.registerObject(item2.getItemName(), item2);
		
		Assert.assertEquals(null, objMapper.getObject(null));
		Assert.assertEquals(item1, objMapper.getObject(item1.getItemName()));
		Assert.assertEquals(item1, objMapper.getObject(item1.getItemName()));
		Assert.assertEquals(null, objMapper.getObject(item3.getItemName()));
	}
	
	@Test
	public void removeObjectTest() {
		ObjectMapper objMapper = ObjectMapper.getInstance();
		Item item1 = new Axe();
		Item item2 = new HealthPotion();
		Item item3 = new Gold();
		
		objMapper.registerObject(item1.getItemName(), item1);
		objMapper.registerObject(item2.getItemName(), item2);
		
		Assert.assertEquals(2, objMapper.size());
		
		objMapper.removeObject(item1.getItemName());
		Assert.assertEquals(1, objMapper.size());
	
		objMapper.removeObject(null);
		Assert.assertEquals(1, objMapper.size());
		
		objMapper.removeObject(item3.getItemName());
		Assert.assertEquals(1, objMapper.size());
		
		objMapper.removeObject(item2.getItemName());
		Assert.assertEquals(0, objMapper.size());
		
		objMapper.removeObject(item2.getItemName());
		Assert.assertEquals(0, objMapper.size());
		
	}
	
	@Test
	public void isEmptyTest() {
		ObjectMapper objMapper = ObjectMapper.getInstance();
		Item item1 = new Axe();
		Item item2 = new HealthPotion();
		Item item3 = new Gold();
		
		Assert.assertTrue(objMapper.isEmpty());
		
		objMapper.registerObject(item1.getItemName(), item1);
		
		Assert.assertFalse(objMapper.isEmpty());
	}
}
