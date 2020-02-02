package jcmsim.activities;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.*;

public class ActAgBetweenRC extends ECActivity {
    
    public ActAgBetweenRC(EvAgRCEnd ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvAgRCBegin) {
            EvAgRCBegin ev2 = (EvAgRCBegin) ev;
            if (((EvAgRCEnd) this.getBeginEvent()).getNumCycle() == ev2.getNumCycle() - 1){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        long nc1 = ((EvAgRCEnd) this.getBeginEvent()).getNumCycle();
        long nc2 = ((EvAgRCBegin) this.getEndEvent()).getNumCycle();
        return "[activity: between reasoning cycles | num-cycles: " + nc1 + " - " + nc2 + " ]";
    }
}
