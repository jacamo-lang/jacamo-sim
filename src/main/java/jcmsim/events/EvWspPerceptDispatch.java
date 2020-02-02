package jcmsim.events;

import java.util.Date;

import cartago.AgentId;
import cartago.CartagoEvent;
import jcmsim.ECEvent;

public class EvWspPerceptDispatch extends ECEvent {

    private CartagoEvent ev;
    private String agentId;
    
    public EvWspPerceptDispatch(CartagoEvent ev, String agentId) {
        this.ev = ev;
        this.agentId = agentId;
    }
    
    public CartagoEvent getPercept() {
        return ev;
    }

    public String getAgentId() {
        return agentId;
    }
    
    public boolean isActivityEnd() {
        return true;
    }

    public String toString() {
        return "[event: wsp percept dispatch | ev-id: " + ev.getId() + " | " + ev.getClass().getName() +" |  dispatched to " + agentId +" ]";
        
    }
}
