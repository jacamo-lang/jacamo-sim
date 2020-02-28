package jcmsim.activities;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.EvAgNewMsgNotified;
import jcmsim.events.EvAgNewMsgReceived;

public class ActAgMsgDelivery extends ECActivity {
    
    public ActAgMsgDelivery(EvAgNewMsgNotified ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvAgNewMsgReceived) {
        	EvAgNewMsgReceived ev2 = (EvAgNewMsgReceived) ev;
            if (((EvAgNewMsgNotified) this.getBeginEvent()).getMessage().getMsgId().equals(ev2.getMessage().getMsgId())){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
    	EvAgNewMsgNotified ev = (EvAgNewMsgNotified) this.getBeginEvent();
        return "[activity: msg notified dispatch | msg-id: " + ev.getMessage() + " ]";
    }

}
