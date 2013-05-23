package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.model.BattleTest;
import tests.model.ContainerTest;
import tests.model.GameObjectTest;
import tests.model.InventoryTest;
import tests.model.MessageBufferTest;
import tests.model.ObjectMapperTest;
import tests.model.creatures.CreatureTest;
import tests.model.creatures.HumanTest;
import tests.model.creatures.player.TestPlayer;
import tests.model.quests.QuestTest;
import tests.model.quests.impl.QuestbookTest;

@RunWith(Suite.class)
@SuiteClasses({BattleTest.class,
	   		   MessageBufferTest.class,
			   ContainerTest.class, 
			   GameObjectTest.class, 
			   InventoryTest.class,
			   HumanTest.class,
			   QuestTest.class,
			   QuestbookTest.class,
			   ObjectMapperTest.class,
			   CreatureTest.class,
			   TestPlayer.class})
public class TestSuite {

}
