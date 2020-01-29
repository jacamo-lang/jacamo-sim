package jcmsim.activities;

import jcmsim.EvCtxActivity;
import jcmsim.EvCtxEvent;
import jcmsim.events.*;

public class ActAgRC extends EvCtxActivity {
    
    public ActAgRC(EvAgRCBegin ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(EvCtxEvent ev) {
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
