package jcmsim.events;

import cartago.events.ActionSucceededEvent;
import cartago.events.CartagoActionEvent;
import jcmsim.EvCtxEvent;

public class EvAgExtActResult extends EvCtxEvent {
    
    private CartagoActionEvent ev;
    
    public EvAgExtActResult(CartagoActionEvent ev) {
        this.ev = ev;
    }
    
    public CartagoActionEvent getActionEvent() {
        return ev;
    }
    
    public boolean isActivityEnd() {
        return true;
    }
    
    public String toString() {
        return "[event: ext action result | act-id: " + ev.getActionId() + " | " + (ev instanceof ActionSucceededEvent ? "success" : "failure") + " ]";  
    }
             
}
