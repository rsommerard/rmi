package fr.rsommerard.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface RMI pour le moniteur.
 */
public interface Monitor extends Remote {

    /**
     * Methode de notification du moniteur.
     * Affiche le message dans la console.
     *
     * @param message
     * @throws RemoteException
     */
    public void notify(Message message) throws RemoteException;

}
