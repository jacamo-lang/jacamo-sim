package jcmsim.events;

import java.util.Optional;
import jason.asSemantics.ActionExec;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.ActAgActionExec;
import jcmsim.activities.ActAgRC;
import jcmsim.activities.ActWspActOpDispatch;

public class EvAgExtActRequest extends ECEvent {

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
    
    public ECActivity[] getActivitiesToBegin() {
        return new ECActivity[] { new ActAgActionExec(this) };
    }
        
    public String toString() {
        return "[event: ext action req | " + actionExec.getActionTerm() + "]";  
    }
             
}
