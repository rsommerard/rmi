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
     * Connexions du site.
     */
    private List<Site> connections;
    
    /**
     * Messages recus
     */
    private List<Message> receivedMessages;

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
        this.connections = new ArrayList<Site>();
        this.receivedMessages = new ArrayList<Message>();
    }

    public SiteImpl(String name, Monitor monitor) throws RemoteException {
        super();
        this.name = name;
        this.connections = new ArrayList<Site>();
        this.monitor = monitor;
        this.receivedMessages = new ArrayList<Message>();
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
    	this.notifyMonitor("Message received from site " + message.getSender().getName() + ": " + message.getContent());

    	synchronized(this.receivedMessages) {
    		if(this.receivedMessages.contains(message)) {
    			return;
        	}

    		this.receivedMessages.add(message);
    	}
    	
    	Site sender = message.getSender();
    	
        message.setSender(this);

        for(final Site site : this.connections) {
        	if(site.equals(sender)) {
        		continue;
        	}
        	
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

        for(final Site site : this.connections) {
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
     * @see Site#addConnection(Site)
     */
	@Override
	public void addConnection(Site site) throws RemoteException {
		if(this.connections.contains(site)) {
			return;
		}
		
		this.connections.add(site);
	}
    
    /*@Override
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
