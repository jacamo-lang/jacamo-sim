package jcmsim.events;

import java.util.Optional;

import cartago.CartagoEvent;
import cartago.events.ActionSucceededEvent;
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
        if (ev instanceof ArtifactObsEvent) {
        	ArtifactObsEvent aev = (ArtifactObsEvent) ev;
        	return "[event: new obs state notified | percept id: " + aev.getId() + " | " + aev.getArtifactId().getName() + " ]";
        } else if (ev instanceof CartagoActionEvent){
        	CartagoActionEvent caev = (CartagoActionEvent) ev;
        	return "[event: new action event notified | action id: " + caev.getActionId() + " | " + caev.getOp().getName() + (caev instanceof ActionSucceededEvent ? " succeeded" : " failed") + " ]";
        } else {
        	return "[event: new percept notified | percept id: " + ev.getId() + " | " + ev.getClass().getName() + "]";
        }
    }
}
