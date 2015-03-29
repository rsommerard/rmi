package fr.rsommerard.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import org.junit.Test;

import fr.rsommerard.rmi.Monitor;
import fr.rsommerard.rmi.MonitorImpl;
import fr.rsommerard.rmi.Site;
import fr.rsommerard.rmi.SiteImpl;

public class SiteImplTest {

	@Test
	public void testSiteImpl() throws RemoteException {
		Site site = new SiteImpl("Site");
		assertTrue(site != null);
	}
	
	@Test
	public void testSiteImplWithMonitor() throws RemoteException {
		Monitor monitor = new MonitorImpl();
		Site site = new SiteImpl("Site", monitor);
		assertTrue(site != null);
	}
	
	@Test
	public void testGetName() throws RemoteException {
		Site site = new SiteImpl("Site");
		assertEquals("Site", site.getName());
	}
	
}
