package jcmsim.events;

import java.util.Optional;

import jcmsim.EvCtxActivity;
import jcmsim.EvCtxEvent;
import jcmsim.activities.ActAgBetweenRC;
import jcmsim.activities.ActAgRC;

public class EvAgRCEnd extends EvCtxEvent {

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

    public Optional<EvCtxActivity> getActivityToBegin() {
        return Optional.of(new ActAgBetweenRC(this));
    }
    
    public String toString() {
        return "[event: reasoning cycle end | num-cycle: " + numCycle + " ]";
    }
    
}
