package jcmsim.events;

import java.util.Optional;

import cartago.CartagoEvent;
import cartago.events.ArtifactObsEvent;
import cartago.events.CartagoActionEvent;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.ActAgPercToActionUpdate;
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
        if (ev instanceof ArtifactObsEvent) {
        	return new ECActivity[] { new ActAgPercToBel(this) };
        } else if (ev instanceof CartagoActionEvent){
        	return new ECActivity[] { new ActAgPercToActionUpdate(this) };
        } else return super.getActivitiesToBegin();
    }
    
    public String toString() {
        return "[event: new percept notified | percept id: " + ev.getId() + " | " + ev.getClass().getName() + "]";
        
    }
}
