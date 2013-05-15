package tests.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cth.tmnd.vanaheim.model.MessageBuffer;
import edu.cth.tmnd.vanaheim.model.ObjectMapper;

public class MessageBufferTest {
	
	private String message = "Test";
	private TestClassPropertyChange pcl;
	
	private class TestClassPropertyChange implements PropertyChangeListener {
		private boolean msgSent = false;
		
		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			this.msgSent = true;
		}
		
	}
	
	@Before
	public void setUp() {
		ObjectMapper.getInstance().clear();
		MessageBuffer.getInstance().clear();
		this.pcl = new TestClassPropertyChange();
	}
	
	@After
	public void tearDown() {
		ObjectMapper.getInstance().clear();
		MessageBuffer.getInstance().clear();
	}
	
	@Test
	public void appendTest() {
		MessageBuffer msgBuffer = MessageBuffer.getInstance();
		final String message = "Test";
		
		msgBuffer.addListener(this.pcl);
		
		Assert.assertFalse(this.pcl.msgSent);
		msgBuffer.append(message);
		Assert.assertTrue(this.pcl.msgSent);
		Assert.assertEquals(1, msgBuffer.size());
		
		this.pcl.msgSent = false;
		
		msgBuffer.append(null);
		Assert.assertEquals(1, msgBuffer.size());
		
		Assert.assertFalse(this.pcl.msgSent);
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
