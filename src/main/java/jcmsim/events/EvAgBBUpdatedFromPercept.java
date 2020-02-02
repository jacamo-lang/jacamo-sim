package jcmsim.events;

import cartago.events.ArtifactObsEvent;
import jcmsim.ECEvent;

public class EvAgBBUpdatedFromPercept extends ECEvent {

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
