package tests;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.model.Battle;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.impl.Monster;
import edu.cth.tmnd.vanaheim.model.creatures.monsters.Spider;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.items.impl.Item;
import edu.cth.tmnd.vanaheim.model.world.tiles.GrassTile;
import edu.cth.tmnd.vanaheim.model.world.tiles.impl.Tile;

public class BattleTest {

	private Battle battle;
	private Monster monster;
	private Tile tile;
	private ObjectMapper objMapper;
	private Player p;
	private int monsterHP = 100;
	private int playerDamage = 10;
	private int playerHP = 100;
	private int monsterDamage = 10;
	
	@Before
	public void setUp() throws Exception {
		this.objMapper = ObjectMapper.getInstance();
		this.p = new Player(1.0f, 1.0f, 100, this.playerHP, "Harald");
		this.objMapper.registerObject(Constants.PLAYER_OBJECT_NAME, this.p);
		this.monster = new Spider(1.0f, 1.0f, 100, this.monsterHP, this.monsterDamage, false);
		this.tile = new GrassTile();
		this.battle = new Battle(this.monster, this.tile);
	}
	
	@After
	public void tearDown() {
		this.objMapper.clear();
	}

	@Test
	public void registeredMonsterTest() {
		Assert.assertFalse(this.objMapper.isEmpty());
		Assert.assertTrue(this.objMapper.isRegistered(this.monster.getName()));
		Assert.assertEquals(this.monster, (Monster)this.objMapper.getObject(this.monster.getName()));
	}
	
	@Test
	public void getMonsterCurrentHPTest() {
		Assert.assertEquals(this.monsterHP, this.battle.getMonsterCurrentHP());
		this.monster.damage(this.playerDamage);
		Assert.assertEquals(this.monsterHP-this.playerDamage, this.battle.getMonsterCurrentHP());
	}
	
	@Test
	public void getMonsterMaxHPTest() {
		Assert.assertEquals(this.monsterHP, this.battle.getMonsterCurrentHP());
		this.monster.damage(this.playerDamage);
		Assert.assertEquals(this.monsterHP, this.battle.getMonsterMaxHP());
	}
	
	@Test
	public void getMonsterHealthPercentageTest() {
		Assert.assertEquals((this.monsterHP/this.monsterHP)*100, this.battle.getMonsterHealthPercentage());
		this.monster.damage(this.playerDamage);
		Assert.assertEquals(((this.monsterHP-this.playerDamage) / this.monsterHP)*100, this.battle.getMonsterHealthPercentage());
	}
	
	@Test
	public void getPlayerCurrentHPTest() {
		Assert.assertEquals(this.playerHP, this.battle.getPlayerCurrentHP());
		this.p.damage(this.monsterDamage);
		Assert.assertEquals(this.playerHP - this.monsterDamage, this.battle.getPlayerCurrentHP());
	}
	
	@Test
	public void destructTest() {
		Assert.assertTrue(this.objMapper.isRegistered(this.monster.getName()));
		Assert.assertEquals(this.monster, (Monster)this.objMapper.getObject(this.monster.getName()));
		this.battle.destruct();
		Assert.assertFalse(this.objMapper.isRegistered(this.monster.getName()));
		Assert.assertNotSame(this.monster, (Monster)this.objMapper.getObject(this.monster.getName()));
		
		List<Item> monsterItems = this.monster.dropItems();
		
		List<Item> tileItems = this.tile.getItems();
		
		Assert.assertEquals(monsterItems.size(), tileItems.size());
		
		for(int i = 0; i < monsterItems.size(); i++) {
			Assert.assertEquals(monsterItems.get(i), tileItems.get(i));
		}
	}
	
	@Test
	public void getPlayerMaxHPTest() {
		Assert.assertEquals(this.playerHP, this.battle.getPlayerMaxHP());
		this.p.damage(this.monsterDamage);
		Assert.assertEquals(this.playerHP, this.battle.getPlayerMaxHP());
	}
	
	@Test
	public void getPlayerHealthPercentageTest() {
		Assert.assertEquals((this.playerHP/this.playerHP)*100, this.battle.getPlayerHealthPercentage());
		this.p.damage(this.monsterDamage);
		Assert.assertEquals(((this.playerHP-this.monsterDamage) / this.playerHP)*100, this.battle.getPlayerHealthPercentage());
	}
	
	@Test
	public void hitPlayerTest() {
		Assert.assertEquals(this.playerHP, this.battle.getPlayerCurrentHP());
		this.battle.hitPlayer();
		Assert.assertEquals(this.playerHP-this.monsterDamage, this.battle.getPlayerCurrentHP());
	}
	
	@Test
	public void hasEndedTest() {
		this.monster.damage(this.battle.getMonsterMaxHP());
		Assert.assertTrue(this.battle.hasEnded());
		this.monster.heal(this.battle.getMonsterMaxHP());
		this.p.damage(this.battle.getPlayerMaxHP());
		Assert.assertTrue(this.battle.hasEnded());
		this.monster.damage(this.battle.getMonsterMaxHP());
		Assert.assertTrue(this.battle.hasEnded());
		
		this.monster.heal(this.battle.getMonsterMaxHP());
		this.p.heal(this.battle.getPlayerMaxHP());
		
		Assert.assertFalse(this.battle.hasEnded());
	}
	
	@Test
	public void monsterWinTest() {
		Assert.assertFalse(this.battle.monsterWin());
		this.p.damage(this.battle.getPlayerMaxHP());
		Assert.assertTrue(this.battle.monsterWin());
	}
	
	@Test
	public void playerWinTest() {
		Assert.assertFalse(this.battle.playerWin());
		this.monster.damage(this.battle.getMonsterMaxHP());
		Assert.assertTrue(this.battle.playerWin());
	}
	
	@Test
	public void getMonsterNameTest() {
		Assert.assertEquals(this.monster.getName(), this.battle.getMonsterName());
	}
}
