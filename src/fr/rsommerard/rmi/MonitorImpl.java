package fr.rsommerard.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementation de l'interface Monitor.
 */
public class MonitorImpl extends UnicastRemoteObject implements Monitor {

	/**
     * @param name
     * @throws RemoteException
     */
    public MonitorImpl() throws RemoteException {
        super();
        System.out.println("[Monitor] Monitor created.");
    }

    /**
     * @see Monitor#notify(Message)
     */
    @Override
    public void notify(Message message) throws RemoteException {
        System.out.println("[Site " + message.getSender().getName() + "] " + message.getContent());
    }
}
