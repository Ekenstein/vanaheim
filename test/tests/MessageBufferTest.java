package tests;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.MessageBuffer;

public class MessageBufferTest {
	
	private String message = "Test";
	
	@After
	public void tearDown() {
		MessageBuffer.getInstance().clear();
	}
	
	@Test
	public void appendTest() {
		MessageBuffer msgBuffer = MessageBuffer.getInstance();
		final String message = "Test";
		class TestPropertyChange implements PropertyChangeListener {
			private boolean msgSent = false;
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				Assert.assertEquals(message, e.getNewValue());
				this.msgSent = true;
			}
		}
		
		TestPropertyChange c = new TestPropertyChange();
		
		msgBuffer.addListener(c);
		
		Assert.assertFalse(c.msgSent);
		msgBuffer.append(message);
		Assert.assertTrue(c.msgSent);
		Assert.assertEquals(1, msgBuffer.size());
		
		c.msgSent = false;
		
		msgBuffer.append(null);
		Assert.assertEquals(1, msgBuffer.size());
		
		Assert.assertFalse(c.msgSent);
	}
	
	@Test
	public void clearTest() {
		MessageBuffer msgBuffer = MessageBuffer.getInstance();
		Assert.assertEquals(0, msgBuffer.size());
		msgBuffer.append(message);
		Assert.assertEquals(1, msgBuffer.size());
		msgBuffer.clear();
		Assert.assertEquals(0, msgBuffer.size());
	}

}
