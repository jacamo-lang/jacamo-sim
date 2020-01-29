package jcmsim.activities;

import cartago.events.ActionSucceededEvent;
import cartago.events.CartagoActionEvent;
import jcmsim.EvCtxActivity;
import jcmsim.EvCtxEvent;
import jcmsim.events.EvAgExtActRequest;
import jcmsim.events.EvAgExtActResult;

public class ActAgActionExec extends EvCtxActivity {
	
	public ActAgActionExec(EvAgExtActRequest ev){
		super(ev);
	}

	public boolean checkAndAppyCompletion(EvCtxEvent ev) {
		if (ev instanceof EvAgExtActResult) {
			EvAgExtActResult ev2 = (EvAgExtActResult) ev;
			if (((EvAgExtActRequest) this.getBeginEvent()).getCausedEvent().getActionId() == ev2.getActionEvent().getActionId()) {
				this.setEndEvent(ev2);
				return true;
			}
		}
		return false;
	}

	public String toString() {
		CartagoActionEvent ev = ((EvAgExtActResult) this.getEndEvent()).getActionEvent();
		return "[activity: action exec | act-id: " + ev.getActionId() + " | " + ev.getOp().getName() + " | " 
				+ (ev instanceof ActionSucceededEvent ? "succeeded" : "failed") + " ]";
	}

}
