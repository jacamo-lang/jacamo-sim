package jcmsim.activities;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.*;

public class ActAgRC extends ECActivity {
    
    public ActAgRC(EvAgRCBegin ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvAgRCEnd) {
            EvAgRCEnd ev2 = (EvAgRCEnd) ev;
            if (((EvAgRCBegin) this.getBeginEvent()).getNumCycle() == ev2.getNumCycle()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "[activity: reasoning cycle | num-cycle: " + ((EvAgRCBegin) this.getBeginEvent()).getNumCycle() + " ]";
    }
}
