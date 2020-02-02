package jcmsim;

import jcmsim.events.*;

public abstract class ECActivity {

    private ECEvent beginEvent;
    private ECEvent endEvent;

    public ECActivity(ECEvent beginEvent) {
        this.beginEvent = beginEvent;
    }
    
    public void setEndEvent(ECEvent endEvent) {
        this.endEvent = endEvent;
    }
    
    public ECEvent getBeginEvent() {
        return beginEvent;
    }

    public ECEvent getEndEvent() {
        return endEvent;
    }
    
    public boolean checkAndAppyCompletion(ECEvent ev) {
        return false;
    }
    
    public boolean isPending() {
        return endEvent == null;
    }
    
    public long getDuration() {
        return endEvent.getTime() - beginEvent.getTime();
    }

    public long getDurationInMicroSec() {
        return endEvent.getTimeInMicroSec() - beginEvent.getTimeInMicroSec();
    }

}
