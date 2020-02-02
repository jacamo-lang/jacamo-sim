package jcmsim.activities;

import cartago.OpExecutionFrame;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.EvArtOpExecBegin;
import jcmsim.events.EvArtOpExecEnd;

public class ActArtOpExec extends ECActivity {
    
    public ActArtOpExec(EvArtOpExecBegin ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvArtOpExecEnd) {
            EvArtOpExecEnd ev2 = (EvArtOpExecEnd) ev;
            if (((EvArtOpExecBegin) this.getBeginEvent()).getInfo().getActionId() == ev2.getActionId()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        OpExecutionFrame info = ((EvArtOpExecBegin) this.getBeginEvent()).getInfo(); 
        return "[activity: op execution | act-id: " + info.getActionId() + " | " + info.getOperation().getName() + " on " + info.getTargetArtifactId() + " by " + info.getAgentId().getAgentName() +"]";
    }

}
