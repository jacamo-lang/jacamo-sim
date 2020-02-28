package jcmsim.events;

import jason.asSemantics.Message;
import jcmsim.ECEvent;

public class EvAgIntActSendMsg extends ECEvent {

	private Message m;
    private EvCommSendMsgDispatch causedEvent;
    
    public EvAgIntActSendMsg(Message m) {
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
    
    /*
    public ECActivity[] getActivitiesToBegin() {
        return new ECActivity[] { new ActAgActionExec(this) };
    }*/
        
    public String toString() {
        return "[event: int action send msg | " + m.getMsgId() + "rec: " + m.getReceiver() + "]";  
    }
             
}
