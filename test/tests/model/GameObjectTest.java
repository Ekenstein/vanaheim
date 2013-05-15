package tests.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.GameObject;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;

public class GameObjectTest {

	private float x, y = 1.0f;
	private String objectName = "TestObjectName";
	private boolean register = true;
	private GameObject testClass;
	private ObjectMapper objMapper = ObjectMapper.getInstance();
	
	private class TestClass extends GameObject {
		public TestClass(float x, float y, String objectName, boolean register) {
			super(x, y, objectName, register);
		}}
	
	@Before
	public void setUp() throws Exception {
		this.objMapper.clear();
		this.testClass = new TestClass(this.x, this.y, this.objectName, this.register);
	}
	
	@After
	public void tearDown() {
		this.objMapper.clear();
	}

	@Test
	public void initializationTest() {
		GameObject o = new TestClass(this.x, this.y, this.objectName, true);
		
		Assert.assertTrue(this.objMapper.isRegistered(o.getName()));
		Assert.assertEquals(o, this.objMapper.getObject(this.objectName));
		
		this.objMapper.clear();
		
		o = new TestClass(this.x, this.y, this.objectName, false);
		
		Assert.assertFalse(this.objMapper.isRegistered(o.getName()));
		Assert.assertEquals(null, this.objMapper.getObject(this.objectName));
	}

	@Test
	public void getXTest() {
		Assert.assertEquals(this.x, this.testClass.getX(), 0.0f);
	}
	
	@Test
	public void getYTest() {
		Assert.assertEquals(this.y, this.testClass.getY(), 0.0f);
	}
	
	@Test
	public void setXTest() {
		Assert.assertEquals(this.x, this.testClass.getX(), 0.0f);
		this.testClass.setX(2.0f);
		Assert.assertEquals(2.0f, this.testClass.getX(), 0.0f);
	}
	
	@Test
	public void setYTest() {
		Assert.assertEquals(this.y, this.testClass.getY(), 0.0f);
		this.testClass.setY(2.0f);
		Assert.assertEquals(2.0f, this.testClass.getY(), 0.0f);
	}
	
	@Test
	public void unregisterTest() {
		Assert.assertTrue(this.objMapper.isRegistered(this.objectName));
		this.testClass.unregister();
		Assert.assertFalse(this.objMapper.isRegistered(this.objectName));
	}
	
	@Test
	public void getNameTest() {
		Assert.assertEquals(this.objectName, this.testClass.getName());
	}
}
