package fr.rsommerard.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Represente un message transmit par les sites.
 */
public class Message implements Serializable {

	/**
     * Contenu du message, son corp.
     */
    private String content;

    /**
     * Site qui envoie le message.
     */
    private Site sender;

    /**
     * @param content
     * @param sender
     */
    public Message(String content, Site sender) {
        this.content = content;
        this.sender = sender;
    }

    /**
     * @return Le contenu du message.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * @param sender
     */
    public void setSender(Site sender) {
        this.sender = sender;
    }

    /**
     * @return Site qui envoie le message.
     */
    public Site getSender() {
        return this.sender;
    }
    
    @Override
    public int hashCode() {
    	return 1;
    }
    
    @Override
    public boolean equals(Object obj){
    	if(obj instanceof Message){
    		Message message = (Message) obj;
			if(this.getContent().equals(message.getContent())) {
		        return true;
		    }
    	}
    	
    	return false;
    }
}
