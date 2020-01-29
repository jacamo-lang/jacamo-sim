package jcmsim;

import java.util.Optional;

public abstract class EvCtxEvent {

    private long time;
    
    public EvCtxEvent() {
    }

    public void setTime(long time) {
        this.time = time;
    }
    
    public long getTime() {
        return time;
    }
    
    public Optional<EvCtxActivity> getActivityToBegin() {
    	return Optional.empty();
    }

    public boolean isActivityEnd() {
    	return false;
    }

}
