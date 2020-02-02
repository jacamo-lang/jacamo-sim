package jcmsim.activities;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.*;

public class ActWspObsStatePerceptDispatch extends ECActivity {
    
    public ActWspObsStatePerceptDispatch(EvWspObsStateDispatch ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvWspObsStateDispatch) {
            
            EvWspPerceptDispatch ev2 = (EvWspPerceptDispatch) ev;
            if (((EvWspObsStateDispatch) this.getBeginEvent()).getArtObsEvent().getId() == ev2.getPercept().getId()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        EvWspObsStateDispatch ev = (EvWspObsStateDispatch) this.getEndEvent();
        return "[activity: wsp obs state percept dispatch | ev-id: " + ev.getAgentId() + " | gen by " + ev.getArtObsEvent().getArtifactId().getName()  + " observed by " + ev.getAgentId()  +"]";
    }

}
