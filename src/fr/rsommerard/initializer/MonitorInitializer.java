package fr.rsommerard.initializer;

import fr.rsommerard.rmi.Monitor;
import fr.rsommerard.rmi.MonitorImpl;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Initialise le moniteur.
 */
public class MonitorInitializer {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();

        Monitor monitor = new MonitorImpl();

        registry.rebind("Monitor", monitor);

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
