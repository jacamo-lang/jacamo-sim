package jcmsim.events;

import java.util.Optional;

import jcmsim.EvCtxActivity;
import jcmsim.EvCtxEvent;
import jcmsim.activities.ActAgRC;

public class EvAgRCBegin extends EvCtxEvent {

	private long numCycle;
	
	public EvAgRCBegin(long numCycle) {
		this.numCycle = numCycle;
	}
	
	public long getNumCycle() {
		return numCycle;
	}
	
    public Optional<EvCtxActivity> getActivityToBegin() {
    	return Optional.of(new ActAgRC(this));
    }

    public boolean isActivityEnd() {
    	return true;
    }

    public String toString() {
    	return "[event: reasoning cycle begin | num-cycle: " + numCycle + " ]";
    }
}
