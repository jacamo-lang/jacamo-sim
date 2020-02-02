package jcmsim.events;

import cartago.events.CartagoActionEvent;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.ActWspActEventToPerceptDispatch;

public class EvWspActionEventDispatch extends ECEvent {
    
    private CartagoActionEvent ev;

    public EvWspActionEventDispatch(CartagoActionEvent ev) {
    	this.ev = ev;
    }
    
    public CartagoActionEvent getEvent() {
        return ev;
    }

    public String toString() {
    	if (ev.getActionId() != -1) {
    		return "[event: action event dispatch | act-id: " + ev.getActionId() + " | "+ ev.getOp().getName() +  " ]";
    	} else {
    		return "[event: action event dispatch | act-id: " + ev.getActionId() + " | internal ]";
    	}
    }
    
    public ECActivity[] getActivitiesToBegin() {
        return new ECActivity[] { new ActWspActEventToPerceptDispatch(this) };
    }


}
