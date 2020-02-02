package jcmsim.events;

import java.util.Optional;

import cartago.*;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.ActArtOpExec;

public class EvArtOpExecBegin extends ECEvent{

    private ArtifactId aid;
    private OpExecutionFrame info;
    
    public EvArtOpExecBegin(ArtifactId aid, OpExecutionFrame info) {
        this.aid = aid;
        this.info = info; 
    }
    

    public ECActivity[] getActivitiesToBegin() {
    	return new ECActivity[] { new ActArtOpExec(this) };
    }


    public ArtifactId getAid() {
        return aid;
    }


    public OpExecutionFrame getInfo() {
        return info;
    }
    
    
    public String toString() {
        return "[event: op exec begin | act-id: " + info.getActionId() + " | " + info.getOperation().getName() + " on " + info.getTargetArtifactId().getName()+ " by " + info.getAgentId().getAgentName() + " ]";
    }

}
