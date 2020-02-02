package jcmsim.events;

import java.util.Optional;

import cartago.Op;
import jcmsim.ECActivity;
import jcmsim.ECEvent;
import jcmsim.activities.*;

public class EvWspActDispatch extends ECEvent {

    private Op op;
    private long actionId;
    private String aid;
    private EvAgExtActRequest causedByEvent;
    private String wspName;
    
    public EvWspActDispatch(String wspName, long actionId, String aid, Op op, EvAgExtActRequest causedByEvent) {
        this.actionId = actionId;
        this.op = op;
        this.aid = aid;
        this.causedByEvent = causedByEvent;
        this.wspName = wspName;
    }
    
    public String getWspName() {
        return wspName;
    }
    
    public Op getOp() {
        return op;
    }

    public String getArtifactId() {
        return aid;
    }
    
    public long getActionId() {
        return actionId;
    }
    
    public ECActivity[] getActivitiesToBegin() {
    	return new ECActivity[] { new ActWspActOpDispatch(this) };
    }
    
    public EvAgExtActRequest getCausedByEvent() {
        return causedByEvent;
    }

    
    public String toString() {
        if (aid != null) {
            return "[event: wsp ext action dispatch | act-id: " + actionId + " | " + op.getName() + " on " + aid + "]";
        } else {
            return "[event: wsp ext action dispatch | act-id: " + actionId + " | " + op.getName() +  "]";
        }
        
    }
         
}
