package fr.rsommerard.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation de l'interface Site.
 */
public class SiteImpl extends UnicastRemoteObject implements Site {

	/**
     * Nom du site.
     */
    private String name;

    /**
     * Fils du site.
     */
    private List<Site> sons;
    
    /**
     * Pere du site.
     */
    private Site father;

    /**
     * Moniteur du site.
     */
    private Monitor monitor;

    /**
     * @param name
     * @throws RemoteException
     */
    public SiteImpl(String name) throws RemoteException {
        super();
        this.name = name;
        this.sons = new ArrayList<Site>();
    }

    public SiteImpl(String name, Monitor monitor) throws RemoteException {
        super();
        this.name = name;
        this.sons = new ArrayList<Site>();
        this.monitor = monitor;
    }

    /**
     * @see Site#getName()
     */
    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    /**
     * @see Site#transferMessage(Message)
     */
    @Override
    public void transferMessage(final Message message) throws RemoteException {
    	this.notifyMonitor("Message receved from site " + message.getSender().getName() + ": " + message.getContent());
    	
        message.setSender(this);

        for(final Site site : this.sons) {
            new Thread(new Runnable() {
            	@Override
                public void run() {
            		try {
            			site.transferMessage(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
         }
    }

    /**
     * @see Site#notifyMonitor(String)
     */
    @Override
    public void notifyMonitor(String content) throws RemoteException {
        Message message = new Message(content, this);

        if(this.monitor != null) {
            this.monitor.notify(message);
        }
    }

    /**
     * @see Site#addMonitor(Monitor)
     */
    @Override
    public void addMonitor(Monitor monitor) throws RemoteException {
        this.monitor = monitor;
    }

    /**
     * @see Site#initializeMessage(String)
     */
    @Override
    public void initializeMessage(String content) throws RemoteException {
        this.notifyMonitor("Message initialized on site " + this.name + ": " + content);

        final Message message = new Message(content, this);

        for(final Site site : this.sons) {
            new Thread(new Runnable() {
            	@Override
                public void run() {
            		try {
            			site.transferMessage(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
         }
    }

    /**
     * @see Site#addSon(Site)
     */
	@Override
	public void addSon(Site site) throws RemoteException {
		if(this.sons.contains(site)) {
			return;
		}
		
		this.sons.add(site);
	}

	/**
     * @see Site#addFather(Site)
     */
	@Override
	public void addFather(Site site) throws RemoteException {
		this.father = site;
	}
	
    /*@Override
    public int hashCode() {
        return 1;
    }
    
    @Override
    public boolean equals(Object obj){
    	if(obj instanceof Site){
    		Site site = (SiteImpl) obj;

			try {
				if(this.name.equals(site.getName())) {
	        		return true;
	        	}
			} catch (RemoteException e) {
				return false;
			}
    	}
    	
    	return false;
    }*/
}
