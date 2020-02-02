package jcmsim.activities;

import cartago.events.CartagoActionEvent;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.events.EvAgExtActResult;
import jcmsim.events.EvAgNewPerceptNotified;

public class ActAgPercToActionUpdate extends ECActivity {
    
    public ActAgPercToActionUpdate(EvAgNewPerceptNotified ev){
        super(ev);
    }

    public boolean checkAndAppyCompletion(ECEvent ev) {
        if (ev instanceof EvAgExtActResult) {
        	EvAgExtActResult ev2 = (EvAgExtActResult) ev;
        	CartagoActionEvent ev1 = (CartagoActionEvent) (((EvAgNewPerceptNotified) this.getBeginEvent()).getPercept());
            if (ev1.getActionId() == ev2.getActionEvent().getActionId()){
                this.setEndEvent(ev2);
                return true;
            }
        }
        return false;
    }

    public String toString() {
    	CartagoActionEvent ev = (CartagoActionEvent) (((EvAgNewPerceptNotified) this.getBeginEvent()).getPercept());
        return "[activity: percept to action update | act-id: " + ev.getActionId() + " | about " + ev.getOp() + " ]";
    }

}
