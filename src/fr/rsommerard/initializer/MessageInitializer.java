package fr.rsommerard.initializer;

import fr.rsommerard.rmi.Site;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Initialise un message sur un noeud.
 */
public class MessageInitializer {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();

        System.out.println("Sending message ...");

        Site site = (Site) registry.lookup(args[0]);
        site.initializeMessage(args[1]);
    }
}
