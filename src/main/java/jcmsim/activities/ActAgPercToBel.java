package jcmsim.activities;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.EvAgBelUpdatedFromPercept;
import jcmsim.events.EvAgNewPerceptNotified;
import jcmsim.events.EvWspActDispatch;
import jcmsim.events.EvWspNewOpToExec;

public class ActAgPercToBel extends ECActivity {
    
    public ActAgPercToBel(EvAgNewPerceptNotified ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvAgBelUpdatedFromPercept) {
            EvAgBelUpdatedFromPercept ev2 = (EvAgBelUpdatedFromPercept) ev;
            if (((EvAgNewPerceptNotified) this.getBeginEvent()).getPercept().getId() == ev2.getArtifactObsEvent().getId()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        EvAgBelUpdatedFromPercept ev = (EvAgBelUpdatedFromPercept) this.getEndEvent();
        return "[activity: percept to bel update | ev-id: " + ev.getArtifactObsEvent().getId() + " | about " + ev.getArtifactObsEvent().getArtifactId().getName() + " artifact ]";
    }

}
