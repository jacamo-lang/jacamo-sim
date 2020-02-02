package jcmsim.events;

import java.util.Optional;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.ActAgRC;
import jcmsim.activities.ActAgRCFetchPercept;

public class EvAgRCBegin extends ECEvent {

    private long numCycle;
    
    public EvAgRCBegin(long numCycle) {
        this.numCycle = numCycle;
    }
    
    public long getNumCycle() {
        return numCycle;
    }
    
    public ECActivity[] getActivitiesToBegin() {
        return new ECActivity[] { new ActAgRC(this), new ActAgRCFetchPercept(this) };
    }

    public boolean isActivityEnd() {
        return true;
    }

    public String toString() {
        return "[event: reasoning cycle begin | num-cycle: " + numCycle + " ]";
    }
}
