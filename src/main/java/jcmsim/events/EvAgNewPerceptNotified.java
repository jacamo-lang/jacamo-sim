package jcmsim.events;

import java.util.Optional;

import cartago.CartagoEvent;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.ActAgPercToBel;

public class EvAgNewPerceptNotified extends ECEvent {

    private CartagoEvent ev;
    
    public EvAgNewPerceptNotified(CartagoEvent ev) {
        this.ev = ev;
    }
    
    public CartagoEvent getPercept() {
        return ev;
    }
    
    public ECActivity[] getActivitiesToBegin() {
    	return new ECActivity[] { new ActAgPercToBel(this) };
    }
    
    public String toString() {
        return "[event: new percept notified | percept id: " + ev.getId() + " | " + ev.getClass().getName() + "]";
        
    }
}
