package jcmsim.events;

import java.util.Optional;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.ActAgBetweenRC;
import jcmsim.activities.ActAgRC;

public class EvAgRCEnd extends ECEvent {

    private long numCycle;
    
    public EvAgRCEnd(long numCycle) {
        this.numCycle = numCycle;
    }
    
    public long getNumCycle() {
        return numCycle;
    }
    
    public boolean isActivityEnd() {
        return true;
    }

    public ECActivity[] getActivitiesToBegin() {
        return new ECActivity[] { new ActAgBetweenRC(this) };
    }
    
    public String toString() {
        return "[event: reasoning cycle end | num-cycle: " + numCycle + " ]";
    }
    
}
