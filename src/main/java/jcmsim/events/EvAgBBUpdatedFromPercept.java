package jcmsim.events;

import cartago.events.ArtifactObsEvent;
import jcmsim.EvCtxEvent;

public class EvAgBBUpdatedFromPercept extends EvCtxEvent {

    private ArtifactObsEvent ev;
    
    public EvAgBBUpdatedFromPercept(ArtifactObsEvent ev) {
        this.ev = ev;
    }
    
    public ArtifactObsEvent getArtifactObsEvent() {
        return ev;
    }
    
    public boolean isActivityEnd() {
    	return true;
    }
    
    public String toString() {
    	return "[event: BB updated with obs state event | percept " + ev.getId() + " from " + ev.getArtifactId() + "]";
        
    }
}
