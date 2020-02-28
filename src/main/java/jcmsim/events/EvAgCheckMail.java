package jcmsim.events;

import jcmsim.ECEvent;

public class EvAgCheckMail extends ECEvent {

    private long numCycle;
    
    public EvAgCheckMail(long numCycle) {
        this.numCycle = numCycle;
    }
    
    public long getNumCycle() {
        return numCycle;
    }
    

    public String toString() {
        return "[event: check mail | num-cycle: " + numCycle + " ]";
    }
}
