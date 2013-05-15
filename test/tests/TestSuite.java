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

@RunWith(Suite.class)
@SuiteClasses({BattleTest.class,
	   		   MessageBufferTest.class,
			   ContainerTest.class, 
			   GameObjectTest.class, 
			   InventoryTest.class,  
			   ObjectMapperTest.class,
			   CreatureTest.class})
public class TestSuite {

}
