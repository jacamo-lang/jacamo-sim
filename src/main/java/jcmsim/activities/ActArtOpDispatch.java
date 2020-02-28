package jcmsim.activities;

import cartago.OpExecutionFrame;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.EvArtOpEnqueued;
import jcmsim.events.EvArtOpExecBegin;
import jcmsim.events.EvArtOpExecEnd;

public class ActArtOpDispatch extends ECActivity {
    
    public ActArtOpDispatch(EvArtOpEnqueued ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvArtOpExecBegin) {
            EvArtOpExecBegin ev2 = (EvArtOpExecBegin) ev;
            if (((EvArtOpEnqueued) this.getBeginEvent()).getInfo().getActionId() == ev2.getInfo().getActionId()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        OpExecutionFrame info = ((EvArtOpExecBegin) this.getEndEvent()).getInfo(); 
        return "[activity: op dispatched | act-id: " + info.getActionId() + " | " + info.getOperation().getName() + " on " + info.getTargetArtifactId() + " by " + info.getAgentId().getAgentName() +"]";
    }

}
