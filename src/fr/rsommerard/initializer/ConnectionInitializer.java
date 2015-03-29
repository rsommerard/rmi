package fr.rsommerard.initializer;

import fr.rsommerard.rmi.Site;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Initialise les connexions entre sites.
 */
public class ConnectionInitializer {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();

        Site site1 = (Site) registry.lookup(args[0]);
        Site site2 = (Site) registry.lookup(args[1]);

        site1.addSon(site2);
        site2.addFather(site1);

        System.out.println("Connection established with site " + args[0] + " and site " + args[1] + ".");
    }
}
