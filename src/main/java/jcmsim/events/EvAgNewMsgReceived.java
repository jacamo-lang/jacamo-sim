package jcmsim.events;

import jason.asSemantics.Message;
import jcmsim.ECEvent;

public class EvAgNewMsgReceived extends ECEvent {

	private Message m;
    
    public EvAgNewMsgReceived(Message m) {
        this.m = m;
    }
    
    public Message getMessage() {
    	return m;
    }
    
    public boolean isActivityEnd() {
        return true;
    }
        
    public String toString() {
        return "[event: new msg received | " + m.getMsgId() + "from: " + m.getSender() + "]";  
    }
             
}
