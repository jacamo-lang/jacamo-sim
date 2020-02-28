package jcmsim.activities;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.*;

public class ActWspObsStateToPerceptDispatch extends ECActivity {
    
    public ActWspObsStateToPerceptDispatch(EvWspObsStateDispatch ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvWspPerceptDispatch) {
        	
            EvWspPerceptDispatch ev2 = (EvWspPerceptDispatch) ev;
            if (((EvWspObsStateDispatch) this.getBeginEvent()).getArtObsEvent().getId() == ev2.getPercept().getId()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        EvWspObsStateDispatch ev = (EvWspObsStateDispatch) this.getBeginEvent();
        return "[activity: wsp obs state percept dispatch | ev-id: " + ev.getAgentId() + " | gen by " + ev.getArtObsEvent().getArtifactId().getName()  + " observed by " + ev.getAgentId()  +"]";
    }

}
