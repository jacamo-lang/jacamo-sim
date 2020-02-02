package jcmsim.events;

import cartago.AgentId;
import cartago.ArtifactId;
import cartago.Op;
import jcmsim.ECEvent;

public class EvWspNewOpToExec extends ECEvent {

    private long actionId;
    private AgentId userId;
    private ArtifactId arId;
    private String arName;
    private Op op;
    
    public EvWspNewOpToExec(long actionId, AgentId userId, ArtifactId arId, String arName, Op op) {
        this.actionId = actionId;
        this.op = op;
        this.userId = userId;
        this.arName = arName;
        this.arId = arId;
    }
        
    public long getActionId() {
        return actionId;
    }
    
    public boolean isActivityEnd() {
        return true;
    }
    
    public AgentId getUserId() {
        return userId;
    }

    public ArtifactId getArId() {
        return arId;
    }

    public String getArName() {
        return arName;
    }

    public Op getOp() {
        return op;
    }
    
    public String toString() {
        return "[event: wsp new op to exec | act-id: " + actionId + " | " + op.getName() + " on " + (arName != null ? arName : arId) + " by " + userId.getAgentName() + "]";
        
    }

    
         
}
