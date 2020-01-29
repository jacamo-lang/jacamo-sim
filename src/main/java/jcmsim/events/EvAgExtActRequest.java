package jcmsim.events;

import java.util.Optional;
import jason.asSemantics.ActionExec;
import jcmsim.EvCtxActivity;
import jcmsim.EvCtxEvent;
import jcmsim.activities.ActAgActionExec;
import jcmsim.activities.ActAgRC;
import jcmsim.activities.ActWspActOpDispatch;

public class EvAgExtActRequest extends EvCtxEvent {

    private ActionExec actionExec;
    private EvWspActDispatch causedEvent;
    
    public EvAgExtActRequest(ActionExec actionExec) {
        this.actionExec = actionExec;
    }
    
    public ActionExec getActionExec() {
        return actionExec;
    }
    
    public EvWspActDispatch getCausedEvent() {
        return causedEvent;
    }

    public void setCausedEvent(EvWspActDispatch ev) {
        causedEvent = ev;
    }
    
    public Optional<EvCtxActivity> getActivityToBegin() {
        return Optional.of(new ActAgActionExec(this));
    }
    
    
    public String toString() {
        return "[event: ext action req | " + actionExec.getActionTerm() + "]";  
    }
             
}
