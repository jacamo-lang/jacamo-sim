package jcmsim.events;

import java.util.Optional;

import cartago.AgentId;
import cartago.ArtifactId;
import cartago.Op;
import cartago.OpExecutionFrame;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.ActAgRC;
import jcmsim.activities.ActAgRCFetchPercept;
import jcmsim.activities.ActArtOpDispatch;
import jcmsim.activities.ActArtOpExec;

public class EvArtOpEnqueued extends ECEvent {

    private OpExecutionFrame info;
    
    public EvArtOpEnqueued(OpExecutionFrame info) {
        this.info = info;
    }
        
    public OpExecutionFrame getInfo() {
        return info;
    }
    
    public ECActivity[] getActivitiesToBegin() {
        return new ECActivity[] { new ActArtOpDispatch(this) };
    }

    public String toString() {
        return "[event: art new op to dispatch | act-id: " + info.getActionId() + " | " + info.getOperation().getName() + " on " + (info.getTargetArtifactId().getName()) + " by " + info.getAgentId().getAgentName() + "]";        
    }

    
         
}
