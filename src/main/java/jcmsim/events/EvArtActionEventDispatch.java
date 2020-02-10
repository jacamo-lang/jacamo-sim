package jcmsim.events;

import cartago.events.CartagoActionEvent;
import jcmsim.ECEvent;

public class EvArtActionEventDispatch extends ECEvent {
    
    private CartagoActionEvent ev;
    
    public EvArtActionEventDispatch(CartagoActionEvent ev) {
    	this.ev = ev;
    }
    
    public CartagoActionEvent getActionEvent() {
    	return ev;
    }
    
    public boolean isActivityEnd() {
        return true;
    }

    public String toString() {
    	return "[event: action event dispatch | act-id: " + ev.getActionId() + " | "+ ev.getOp().getName() +" ]";
    }

}
