package tests.model.parser;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.constants.Constants;
import edu.cth.tmnd.vanaheim.model.MessageBuffer;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;
import edu.cth.tmnd.vanaheim.model.creatures.player.Player;
import edu.cth.tmnd.vanaheim.model.parser.Parser;

public class ParserTest {

	private Parser p;
	private ObjectMapper objMapper = ObjectMapper.getInstance();
	private Player player;
	private float px, py = 1.0f;
	private int pvel = 100;
	private int pmaxHP = 100;
	
	@Before
	public void setUp() throws Exception {
		this.objMapper.clear();
		MessageBuffer.getInstance().clear();
		this.player = new Player(px, py, pvel, pmaxHP, "Harald");
		this.objMapper.registerObject(Constants.PLAYER_OBJECT_NAME, this.player);
		this.p = Parser.getInstance(Constants.COMMAND_FILE);
	}

	@After
	public void tearDown() throws Exception {
		this.objMapper.clear();
		MessageBuffer.getInstance().clear();
	}

	@Test
	public void equipParseTest() {
		Assert.assertEquals(null, this.player.getEquippedItem());
		Assert.assertEquals("crude axe", this.player.getItem("crude axe").getItemName().toLowerCase());
		this.p.parse("equip crude axe");
		
		Assert.assertEquals("crude axe", this.player.getEquippedItem().getItemName().toLowerCase());
	}

}
