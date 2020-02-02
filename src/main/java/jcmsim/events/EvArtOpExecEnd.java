package jcmsim.events;

import cartago.ArtifactId;
import cartago.Op;
import cartago.Tuple;
import jcmsim.ECEvent;

public class EvArtOpExecEnd extends ECEvent {
    
    private long actionId;
    private ArtifactId aid;
    private Op op;
    private String failureMsg;
    private Tuple failureReason;
    
    public EvArtOpExecEnd(long actionId, ArtifactId aid, Op op) {
        this.actionId = actionId;
        this.aid = aid;
        this.op = op;
        
    }
    
    public EvArtOpExecEnd(long actionId, ArtifactId aid, Op op, String failureMsg, Tuple failureReason) {
        this.actionId = actionId;
        this.aid = aid;
        this.op = op;
        this.failureMsg = failureMsg;
        this.failureReason = failureReason;
    }
    
    public String getFailureMsg() {
        return failureMsg;
    }


    public Tuple getFailureReason() {
        return failureReason;
    }
        
    public boolean isActivityEnd() {
        return true;
    }

    public long getActionId() {
        return actionId;
    }

    public ArtifactId getAid() {
        return aid;
    }

    public Op getOp() {
        return op;
    }
    

    public String toString() {
        if (failureMsg == null && failureReason == null) {
            return "[event: op exec end | act-id: " + actionId + " | "+ op.getName() + " on " + aid.getName() + " ]";
        } else {
            return "[event: op exec end (failure) | "  + actionId + " | "+ op.getName() + " on " + aid.getName() + " ]";
        }
    }

}
