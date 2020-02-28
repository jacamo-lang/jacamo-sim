package jcmsim.activities;

import jason.asSemantics.Message;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.EvCommReceiveMsgDispatch;
import jcmsim.events.EvCommSendMsgDispatch;

public class ActCommMsgDispatch extends ECActivity {

    public ActCommMsgDispatch(EvCommSendMsgDispatch ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvCommReceiveMsgDispatch) {
        	EvCommReceiveMsgDispatch ev2 = (EvCommReceiveMsgDispatch) ev;
            if (((EvCommSendMsgDispatch) this.getBeginEvent()).getMessage().getMsgId().equals(ev2.getMessage().getMsgId())){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
    	EvCommSendMsgDispatch ev = (EvCommSendMsgDispatch) this.getBeginEvent();
    	Message m = ev.getMessage();
    	return "[event: comm msg dispatch | msg-id: " + m.getMsgId() + " | " + m.getPropCont() + " from " + m.getSender() + " to " + m.getReceiver() + "]";
    }

}
