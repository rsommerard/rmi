package fr.rsommerard.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface RMI pour les sites.
 */
public interface Site extends Remote {
    /**
     * @return Nom du site.
     * @throws RemoteException
     */
    public String getName() throws RemoteException;

    /**
     * Ajoute une connexion au site.
     *
     * @param site
     * @throws RemoteException
     */
    public void addConnection(Site site) throws RemoteException;

    /**
     * Transfert le message aux sites fils.
     *
     * @param message
     * @throws RemoteException
     */
    public void transferMessage(Message message) throws RemoteException;

    /**
     * Notifie le moniteur.
     *
     * @param content
     * @throws RemoteException
     */
    public void notifyMonitor(String content) throws RemoteException;

    /**
     * Ajoute le moniteur au site.
     *
     * @param monitor
     * @throws RemoteException
     */
    public void addMonitor(Monitor monitor) throws RemoteException;

    /**
     * Permet d'initializer un nouveau message a propager.
     *
     * @param content
     * @throws RemoteException
     */
    public void initializeMessage(String content) throws RemoteException;

}
