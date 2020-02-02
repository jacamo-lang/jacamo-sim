package jcmsim.activities;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.*;

public class ActWspActEventToPerceptDispatch extends ECActivity {
    
    public ActWspActEventToPerceptDispatch(EvWspActionEventDispatch ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvWspPerceptDispatch) {
            
            EvWspPerceptDispatch ev2 = (EvWspPerceptDispatch) ev;
            if (((EvWspActionEventDispatch) this.getBeginEvent()).getEvent().getId() == ev2.getPercept().getId()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
    	EvWspActionEventDispatch ev1 = (EvWspActionEventDispatch) this.getBeginEvent();
    	EvWspPerceptDispatch ev = (EvWspPerceptDispatch) this.getEndEvent();
        return "[activity: wsp action event to percept dispatch | act-id: " + ev1.getEvent().getActionId() + " | action " + ev1.getEvent().getOp().getName()  + " to " + ev.getAgentId()  +"]";
    }

}
