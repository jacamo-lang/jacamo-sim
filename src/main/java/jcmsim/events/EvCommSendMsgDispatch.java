package jcmsim.events;

import java.util.Optional;

import cartago.Op;
import jason.asSemantics.Message;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.*;

public class EvCommSendMsgDispatch extends ECEvent {

    private Message m;
    
    public EvCommSendMsgDispatch(Message m) {
    	this.m = m;
    }

    public Message getMessage() {
    	return m;
    }
    
    public ECActivity[] getActivitiesToBegin() {
        return new ECActivity[] { new ActCommMsgDispatch(this) };
    }
    
    
    public String toString() {
    	return "[event: comm msg dispatch | msg-id: " + m.getMsgId() + " | " + m.getPropCont() + " from " + m.getSender() + " to " + m.getReceiver() + "]";
    }
         
}
