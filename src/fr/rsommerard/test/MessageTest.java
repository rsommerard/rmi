package fr.rsommerard.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import org.junit.Test;

import fr.rsommerard.rmi.Message;
import fr.rsommerard.rmi.Site;
import fr.rsommerard.rmi.SiteImpl;

public class MessageTest {
	
	@Test
	public void testMessage() throws RemoteException {
		Site site = new SiteImpl("Test");
		Message message = new Message("This is a message !", site);
		assertTrue(message != null);
	}

	@Test
	public void testGetContent() throws RemoteException {
		Site site = new SiteImpl("Test");
		Message message = new Message("This is a message !", site);
		assertEquals("This is a message !", message.getContent());
	}
	
	@Test
	public void testGetSender() throws RemoteException {
		Site site = new SiteImpl("Test");
		Message message = new Message("This is a message !", site);
		assertEquals(site, message.getSender());
	}
	
	@Test
	public void testSetSender() throws RemoteException {
		Site site = new SiteImpl("Test");
		Message message = new Message("This is a message !", site);
		assertEquals(site, message.getSender());
		
		Site otherSite = new SiteImpl("OtherTest");
		message.setSender(otherSite);
		assertEquals(otherSite, message.getSender());
	}
}
