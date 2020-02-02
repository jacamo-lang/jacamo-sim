package jcmsim.activities;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.*;

public class ActAgRCFetchPercept extends ECActivity {
    
    public ActAgRCFetchPercept(EvAgRCBegin ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvAgFetchPercept) {
        	EvAgFetchPercept ev2 = (EvAgFetchPercept) ev;
            if (((EvAgRCBegin) this.getBeginEvent()).getNumCycle() == ev2.getNumCycle()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "[activity: fetch percept | num-cycle: " + ((EvAgRCBegin) this.getBeginEvent()).getNumCycle() + " ]";
    }
}
