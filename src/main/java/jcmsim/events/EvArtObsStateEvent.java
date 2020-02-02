package jcmsim.events;

import cartago.events.ArtifactObsEvent;
import jcmsim.ECEvent;

public class EvArtObsStateEvent extends ECEvent {

    private ArtifactObsEvent ev;
    
    public EvArtObsStateEvent(ArtifactObsEvent ev) {
        this.ev = ev;
    }
        
    public String toString() {
        return "[event: art new obs state event | " + ev.getArtifactId().getName() + "]";
    }
    
    public ArtifactObsEvent getArtObsEvent() {
        return ev;
    }

}
