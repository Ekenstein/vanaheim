package tests.model.creatures;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Creature;
import edu.cth.tmnd.vanaheim.model.items.Axe;
import edu.cth.tmnd.vanaheim.model.items.impl.EquipableItem;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;

public class CreatureTest {
	
	private float x, y = 1.0f;
	private int velocity = 100;
	private int maxHP = 100;
	private String creatureName = "TestCreatureName";
	private boolean register = true;
	private Creature testClass;
	private ObjectMapper objMapper = ObjectMapper.getInstance();

	private class TestClassCreature extends Creature {
		public TestClassCreature(float x, float y, int velocity, int maxHP,
				String creatureName, boolean register) {
			super(x, y, velocity, maxHP, creatureName, register);
		}
	}
	
	@Before
	public void setUp() throws Exception {
		this.testClass = new TestClassCreature(x, y, velocity, maxHP, creatureName, register);
	}
	
	@After
	public void tearDown() {
		this.objMapper.clear();
	}

	@Test
	public void getVelocityTest() {
		Assert.assertEquals(this.velocity, this.testClass.getVelocity());
	}
	
	@Test
	public void setVelocityTest() {
		Assert.assertEquals(this.velocity, this.testClass.getVelocity());
		this.testClass.setVelocity(this.velocity - 10);
		Assert.assertEquals(this.velocity - 10, this.testClass.getVelocity());
	}
	
	@Test
	public void equipTest() {
		Assert.assertEquals(this.testClass.getEquippedItem(), null);
		Assert.assertEquals(this.testClass.getInventorySlotsLeft(), this.testClass.getInventorySlots());
		this.testClass.equip(null);
		Assert.assertEquals(null, this.testClass.getEquippedItem());
		
		EquipableItem item = new Axe();
		this.testClass.equip(item);
		Assert.assertNotSame(item, this.testClass.getEquippedItem());
		Assert.assertEquals(this.testClass.getInventorySlotsLeft(), this.testClass.getInventorySlots());
		
		this.testClass.addItem(item);
		Assert.assertEquals(this.testClass.getInventorySlotsLeft(), this.testClass.getInventorySlots()-1);
		this.testClass.equip(item);
		Assert.assertEquals(this.testClass.getInventorySlotsLeft(), this.testClass.getInventorySlots());
		Assert.assertEquals(item, this.testClass.getEquippedItem());
		
		EquipableItem item2 = new Axe();
		this.testClass.equip(item2);
		Assert.assertTrue(item == this.testClass.getEquippedItem());
		Assert.assertFalse(item2 == this.testClass.getEquippedItem());
	}
	
	@Test
	public void getEquippedItemTest() {
		Assert.assertEquals(this.testClass.getEquippedItem(), null);
		Axe a = new Axe();
		this.testClass.addItem(a);
		this.testClass.equip(a);
		Assert.assertEquals(this.testClass.getEquippedItem(), a);
	}

	@Test
	public void unequipTest() {
		Axe a = new Axe();
		this.testClass.addItem(a);
		this.testClass.equip(a);
		Assert.assertEquals(this.testClass.getInventorySlotsLeft(), this.testClass.getInventorySlots());
		
		this.testClass.unequip();
		Assert.assertEquals(this.testClass.getInventorySlotsLeft(), this.testClass.getInventorySlots()-1);
		this.testClass.retrieveItem(a);
		
		for(int i = 0; i < this.testClass.getInventorySlots(); i++) {
			this.testClass.addItem(new Axe());
			Assert.assertEquals(this.testClass.getInventorySlotsLeft(), this.testClass.getInventorySlots() - (i+1));
		}
		
		this.testClass.equip(a);
		this.testClass.addItem(new Axe());
		this.testClass.unequip();
		Assert.assertEquals(a, this.testClass.getEquippedItem());
	}
	
	@Test
	public void testMaxHP() {
		Assert.assertEquals(this.maxHP, this.testClass.getMaxHP());
		this.testClass.setMaxHP(20);
		Assert.assertEquals(20, this.testClass.getMaxHP());
	}
	
	@Test
	public void testHeal(){
		Assert.assertEquals(100,this.testClass.getCurrentHP());
		this.testClass.damage(20);
		Assert.assertEquals(80,this.testClass.getCurrentHP());
		this.testClass.heal(20);
		Assert.assertEquals(100,this.testClass.getCurrentHP());
		
		this.testClass.damage(20);
		Assert.assertEquals(80,this.testClass.getCurrentHP());
		this.testClass.heal(30);
		Assert.assertEquals(100,this.testClass.getCurrentHP());
		
		this.testClass.damage(50);
		Assert.assertEquals(50,this.testClass.getCurrentHP());
		this.testClass.heal(20);
		Assert.assertEquals(70,this.testClass.getCurrentHP());
	}
	
	@Test
	public void testDamage(){
		Assert.assertEquals(100,this.testClass.getCurrentHP());
		this.testClass.damage(20);
		Assert.assertEquals(80,this.testClass.getCurrentHP());
		
		this.testClass.damage(80);
		Assert.assertEquals(0,this.testClass.getCurrentHP());
		
		this.testClass.damage(20);
		Assert.assertEquals(0, this.testClass.getCurrentHP());
		
	}
	
	@Test
	public void isDeadTest(){
		Assert.assertEquals(100, this.testClass.getCurrentHP());
		this.testClass.damage(100);
		Assert.assertTrue(this.testClass.isDead());
		
		this.testClass.heal(100);
		this.testClass.damage(20);
		Assert.assertFalse(this.testClass.isDead());
		
		this.testClass.damage(100);
		Assert.assertTrue(this.testClass.isDead());
	}
	
	@Test
	public void getItemTest(){
		Item item = new Axe();
		Assert.assertEquals(this.testClass.getInventorySlots(),this.testClass.getInventorySlotsLeft());
		
		Assert.assertEquals(null,this.testClass.getItem(item));
		Assert.assertEquals(this.testClass.getInventorySlots(),this.testClass.getInventorySlotsLeft());
		
		this.testClass.addItem(item);
		
		Assert.assertEquals(item,this.testClass.getItem(item));
		Assert.assertEquals(this.testClass.getInventorySlots() - 1, this.testClass.getInventorySlotsLeft());
		
	}
	
	@Test
	public void getItemFromStringTest(){
		Item item = new Axe();
		Assert.assertEquals(this.testClass.getInventorySlots(),this.testClass.getInventorySlotsLeft());
		
		Assert.assertEquals(null,this.testClass.getItem(item.getItemName()));
		Assert.assertEquals(this.testClass.getInventorySlots(),this.testClass.getInventorySlotsLeft());
		
		this.testClass.addItem(item);
		
		Assert.assertEquals(item,this.testClass.getItem(item.getItemName()));
		Assert.assertEquals(this.testClass.getInventorySlots() - 1, this.testClass.getInventorySlotsLeft());
	}
	
	@Test
	public void setXTest(){
		this.testClass.setX(5.0f);
		Assert.assertEquals(Creature.Direction.RIGHT, this.testClass.getDirection());
		Assert.assertEquals(5.0f, this.testClass.getX(),0.01);
		Assert.assertEquals(1.0f,this.testClass.getY(),0.01);
		
		this.testClass.setX(4.0f);
		Assert.assertEquals(Creature.Direction.LEFT, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getX(),0.01);
		Assert.assertEquals(1.0f,this.testClass.getY(),0.01);
		
		this.testClass.setX(4.0f);
		Assert.assertEquals(Creature.Direction.LEFT, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getX(),0.01);
		Assert.assertEquals(1.0f,this.testClass.getY(),0.01);
		
	}
	
	@Test
	public void setYTest(){
		this.testClass.setX(1.0f);
		this.testClass.setY(5.0f);
		Assert.assertEquals(Creature.Direction.DOWN, this.testClass.getDirection());
		Assert.assertEquals(5.0f, this.testClass.getY(), 0.01);
		Assert.assertEquals(1.0f, this.testClass.getX(), 0.01);
		
		this.testClass.setY(4.0f);
		Assert.assertEquals(Creature.Direction.UP, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getY(), 0.01);
		Assert.assertEquals(1.0f, this.testClass.getX(), 0.01);
		
		this.testClass.setY(4.0f);
		Assert.assertEquals(Creature.Direction.UP, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getY(), 0.01);
		Assert.assertEquals(1.0f, this.testClass.getX(), 0.01);
	}
	
	@Test
	public void setXIgnoreDirectionTest(){
		this.testClass.setX(5.0f,false);
		Assert.assertEquals(Creature.Direction.RIGHT, this.testClass.getDirection());
		Assert.assertEquals(5.0f, this.testClass.getX(),0.01);
		Assert.assertEquals(1.0f,this.testClass.getY(),0.01);
		
		this.testClass.setX(4.0f,false);
		Assert.assertEquals(Creature.Direction.LEFT, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getX(),0.01);
		Assert.assertEquals(1.0f,this.testClass.getY(),0.01);
		
		this.testClass.setX(4.0f,false);
		Assert.assertEquals(Creature.Direction.LEFT, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getX(),0.01);
		Assert.assertEquals(1.0f,this.testClass.getY(),0.01);
		
		this.testClass.setX(5.0f,true);
		Assert.assertEquals(Creature.Direction.LEFT, this.testClass.getDirection());
		Assert.assertEquals(5.0f, this.testClass.getX(),0.01);
		Assert.assertEquals(1.0f,this.testClass.getY(),0.01);
		
		this.testClass.setX(4.0f,false);
		Assert.assertEquals(Creature.Direction.LEFT, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getX(),0.01);
		Assert.assertEquals(1.0f,this.testClass.getY(),0.01);
		
		this.testClass.setX(4.0f,false);
		Assert.assertEquals(Creature.Direction.LEFT, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getX(),0.01);
		Assert.assertEquals(1.0f,this.testClass.getY(),0.01);
	}
	
	@Test
	public void setYIgnoreDirectionTest(){
		this.testClass.setX(1.0f);
		this.testClass.setY(5.0f,false);
		Assert.assertEquals(Creature.Direction.DOWN, this.testClass.getDirection());
		Assert.assertEquals(5.0f, this.testClass.getY(), 0.01);
		Assert.assertEquals(1.0f, this.testClass.getX(), 0.01);
		
		this.testClass.setY(4.0f,false);
		Assert.assertEquals(Creature.Direction.UP, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getY(), 0.01);
		Assert.assertEquals(1.0f, this.testClass.getX(), 0.01);
		
		this.testClass.setY(4.0f,false);
		Assert.assertEquals(Creature.Direction.UP, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getY(), 0.01);
		Assert.assertEquals(1.0f, this.testClass.getX(), 0.01);
		
		this.testClass.setY(5.0f,true);
		Assert.assertEquals(Creature.Direction.UP, this.testClass.getDirection());
		Assert.assertEquals(5.0f, this.testClass.getY(), 0.01);
		Assert.assertEquals(1.0f, this.testClass.getX(), 0.01);
		
		this.testClass.setY(4.0f,true);
		Assert.assertEquals(Creature.Direction.UP, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getY(), 0.01);
		Assert.assertEquals(1.0f, this.testClass.getX(), 0.01);
		
		this.testClass.setY(4.0f,true);
		Assert.assertEquals(Creature.Direction.UP, this.testClass.getDirection());
		Assert.assertEquals(4.0f, this.testClass.getY(), 0.01);
		Assert.assertEquals(1.0f, this.testClass.getX(), 0.01);
	}
	
	@Test
	public void setDirectionTest(){
		Assert.assertEquals(Creature.Direction.UP, this.testClass.getDirection());
		
		this.testClass.setDirection(Creature.Direction.UP);
		Assert.assertEquals(Creature.Direction.UP, this.testClass.getDirection());
		
		this.testClass.setDirection(Creature.Direction.DOWN);
		Assert.assertEquals(Creature.Direction.DOWN, this.testClass.getDirection());
		
		this.testClass.setDirection(Creature.Direction.LEFT);
		Assert.assertEquals(Creature.Direction.LEFT, this.testClass.getDirection());
		
		this.testClass.setDirection(Creature.Direction.RIGHT);
		Assert.assertEquals(Creature.Direction.RIGHT, this.testClass.getDirection());
	}
	
	@Test
	public void isEquippedTest() {
		EquipableItem item = new Axe();
		this.testClass.addItem(item);
		Assert.assertFalse(this.testClass.isEquipped(null));
		
		this.testClass.equip(null);
		Assert.assertFalse(this.testClass.isEquipped());
		
		this.testClass.equip(item);
		Assert.assertTrue(this.testClass.isEquipped(item));
		Assert.assertTrue(this.testClass.isEquipped());

	}
}