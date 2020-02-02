package jcmsim.activities;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.EvAgBBUpdatedFromPercept;
import jcmsim.events.EvAgNewPerceptNotified;
import jcmsim.events.EvWspActDispatch;
import jcmsim.events.EvWspNewOpToExec;

public class ActAgPercToBel extends ECActivity {
    
    public ActAgPercToBel(EvAgNewPerceptNotified ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvAgBBUpdatedFromPercept) {
            EvAgBBUpdatedFromPercept ev2 = (EvAgBBUpdatedFromPercept) ev;
            if (((EvAgNewPerceptNotified) this.getBeginEvent()).getPercept().getId() == ev2.getArtifactObsEvent().getId()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        EvAgBBUpdatedFromPercept ev = (EvAgBBUpdatedFromPercept) this.getEndEvent();
        return "[activity: percept to bel update | ev-id: " + ev.getArtifactObsEvent().getId() + " | about " + ev.getArtifactObsEvent().getArtifactId().getName() + " artifact ]";
    }

}
