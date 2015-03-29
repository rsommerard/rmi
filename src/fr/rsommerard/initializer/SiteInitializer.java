package fr.rsommerard.initializer;

import fr.rsommerard.rmi.Monitor;
import fr.rsommerard.rmi.Site;
import fr.rsommerard.rmi.SiteImpl;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Initialise les sites.
 */
public class SiteInitializer {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();

        Monitor monitor = (Monitor) registry.lookup("Monitor");

        Site site = new SiteImpl(args[0], monitor);

        registry.rebind(args[0], site);

        System.out.println("Site " + args[0] + " created and running ...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread();
					Thread.sleep(999999999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
