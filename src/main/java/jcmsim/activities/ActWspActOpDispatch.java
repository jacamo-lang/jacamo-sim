package jcmsim.activities;

import jcmsim.EvCtxActivity;
import jcmsim.EvCtxEvent;
import jcmsim.events.EvWspActDispatch;
import jcmsim.events.EvWspNewOpToExec;

public class ActWspActOpDispatch extends EvCtxActivity {
    
    public ActWspActOpDispatch(EvWspActDispatch ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(EvCtxEvent ev) {
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
        return "[activity: wsp action op dispatch | act-id: " + ev.getActionId() + " | " + ev.getOp().getName() + " on " +ev.getArId()  + " by " + ev.getUserId().getAgentName() +"]";
    }

}
