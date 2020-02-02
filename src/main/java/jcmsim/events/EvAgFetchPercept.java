package jcmsim.events;

import java.util.Optional;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.ActAgRC;
import jcmsim.activities.ActAgRCFetchPercept;

public class EvAgFetchPercept extends ECEvent {

    private long numCycle;
    
    public EvAgFetchPercept(long numCycle) {
        this.numCycle = numCycle;
    }
    
    public long getNumCycle() {
        return numCycle;
    }
    
    public boolean isActivityEnd() {
        return true;
    }


    public String toString() {
        return "[event: fetch percept | num-cycle: " + numCycle + " ]";
    }
}
