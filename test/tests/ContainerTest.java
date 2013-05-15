package tests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.Container;

public class ContainerTest {
	private Container testClass;

	private class TestClassContainer extends Container {}
	
	@Before
	public void setUp() throws Exception {
		this.testClass = new TestClassContainer();
	}

	@Test
	public void toggleTest() {
		Assert.assertFalse(this.testClass.isToggled());
		this.testClass.toggle();
		Assert.assertTrue(this.testClass.isToggled());
		this.testClass.toggle();
		Assert.assertFalse(this.testClass.isToggled());
	}

}
