package fr.rsommerard.test;

import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import org.junit.Test;

import fr.rsommerard.rmi.Monitor;
import fr.rsommerard.rmi.MonitorImpl;

public class MonitorImplTest {
	
	@Test
	public void testMonitorImpl() throws RemoteException {
		Monitor monitor = new MonitorImpl();
		assertTrue(monitor != null);
	}

}
