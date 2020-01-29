package jcmsim.activities;

import jcmsim.EvCtxActivity;
import jcmsim.EvCtxEvent;
import jcmsim.events.*;

public class ActWspObsStatePerceptDispatch extends EvCtxActivity {
    
    public ActWspObsStatePerceptDispatch(EvWspObsStateDispatch ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(EvCtxEvent ev) {
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
