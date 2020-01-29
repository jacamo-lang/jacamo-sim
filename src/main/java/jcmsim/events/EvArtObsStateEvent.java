package jcmsim.events;

import cartago.events.ArtifactObsEvent;
import jcmsim.EvCtxEvent;

public class EvArtObsStateEvent extends EvCtxEvent {

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
