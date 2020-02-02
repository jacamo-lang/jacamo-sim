package jcmsim.activities;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.EvWspActDispatch;
import jcmsim.events.EvWspNewOpToExec;

public class ActWspActOpDispatch extends ECActivity {
    
    public ActWspActOpDispatch(EvWspActDispatch ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvWspNewOpToExec) {
            EvWspNewOpToExec ev2 = (EvWspNewOpToExec) ev;
            if (((EvWspActDispatch) this.getBeginEvent()).getActionId() == ev2.getActionId()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        EvWspNewOpToExec ev = (EvWspNewOpToExec) this.getEndEvent();
        if (ev.getArId() != null) {
        	return "[activity: wsp action op dispatch | act-id: " + ev.getActionId() + " | " + ev.getOp().getName() + " on " +ev.getArId()  + " by " + ev.getUserId().getAgentName() +"]";
        } else if (ev.getArName() != null){
        	return "[activity: wsp action op dispatch | act-id: " + ev.getActionId() + " | " + ev.getOp().getName() + " on " +ev.getArName()  + " by " + ev.getUserId().getAgentName() +"]";
        } else {
        	return "[activity: wsp action op dispatch | act-id: " + ev.getActionId() + " | " + ev.getOp().getName() + " by " + ev.getUserId().getAgentName() +"]";
        }
    }
}