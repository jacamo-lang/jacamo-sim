package jcmsim;

import jcmsim.events.*;

public abstract class EvCtxActivity {

    private EvCtxEvent beginEvent;
    private EvCtxEvent endEvent;

    public EvCtxActivity(EvCtxEvent beginEvent) {
        this.beginEvent = beginEvent;
    }
    
    public void setEndEvent(EvCtxEvent endEvent) {
        this.endEvent = endEvent;
    }
    
    public EvCtxEvent getBeginEvent() {
        return beginEvent;
    }

    public EvCtxEvent getEndEvent() {
        return endEvent;
    }
    
    public boolean checkAndAppyCompletion(EvCtxEvent ev) {
        return false;
    }
    
    public boolean isPending() {
        return endEvent == null;
    }
    
    public long getDuration() {
        return endEvent.getTime() - beginEvent.getTime();
    }

}
