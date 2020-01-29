package jcmsim.activities;

import cartago.OpExecutionFrame;
import jcmsim.EvCtxActivity;
import jcmsim.EvCtxEvent;
import jcmsim.events.EvArtOpExecBegin;
import jcmsim.events.EvArtOpExecEnd;

public class ActArtOpExec extends EvCtxActivity {
	
	public ActArtOpExec(EvArtOpExecBegin ev){
		super(ev);
	}

	public boolean checkAndAppyCompletion(EvCtxEvent ev) {
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
