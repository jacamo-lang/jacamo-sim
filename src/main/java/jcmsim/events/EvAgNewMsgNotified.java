package jcmsim.events;

import jason.asSemantics.Message;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.ActAgMsgDelivery;

public class EvAgNewMsgNotified extends ECEvent {

	private Message m;
    private EvCommSendMsgDispatch causedEvent;
    
    public EvAgNewMsgNotified(Message m) {
        this.m = m;
    }
    
    public Message getMessage() {
    	return m;
    }
    
    public EvCommSendMsgDispatch getCausedEvent() {
        return causedEvent;
    }

    public void setCausedEvent(EvCommSendMsgDispatch ev) {
        causedEvent = ev;
    }
    
    
    public ECActivity[] getActivitiesToBegin() {
        return new ECActivity[] { new ActAgMsgDelivery(this) };
    }
        
    public String toString() {
        return "[event: new msg notified | " + m.getMsgId() + "from: " + m.getSender() + "]";  
    }
             
}
