package jcmsim.activities;

import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.EvArtActionEventDispatch;
import jcmsim.events.EvArtOpExecEnd;

public class ActArtOpResultDispatch extends ECActivity {
    
    public ActArtOpResultDispatch(EvArtOpExecEnd ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvArtActionEventDispatch) {
        	EvArtActionEventDispatch ev2 = (EvArtActionEventDispatch) ev;
            if (((EvArtOpExecEnd) this.getBeginEvent()).getActionId() == ev2.getActionEvent().getActionId()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
    	EvArtActionEventDispatch ev = (EvArtActionEventDispatch) this.getEndEvent();
        return "[activity: op result dispatch | act-id: " + ev.getActionEvent().getActionId() + " | " + ev.getActionEvent().getOp().getName() + "]";
    }

}
