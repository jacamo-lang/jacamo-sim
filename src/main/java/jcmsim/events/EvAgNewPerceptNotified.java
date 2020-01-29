package jcmsim.events;

import java.util.Optional;

import cartago.CartagoEvent;
import jcmsim.EvCtxActivity;
import jcmsim.EvCtxEvent;
import jcmsim.activities.ActAgPercToBel;

public class EvAgNewPerceptNotified extends EvCtxEvent {

    private CartagoEvent ev;
    
    public EvAgNewPerceptNotified(CartagoEvent ev) {
        this.ev = ev;
    }
    
    public CartagoEvent getPercept() {
        return ev;
    }
    
    public Optional<EvCtxActivity> getActivityToBegin() {
    	return Optional.of(new ActAgPercToBel(this));
    }

    public String toString() {
    	return "[event: new percept notified | percept id: " + ev.getId() + " | " + ev.getClass().getName() + "]";
        
    }
}
