package cartago;

import jcmsim.ECEvent;

public class EvEnvNewOpToExec extends ECEvent {

    private WorkspaceId wid;
    private long actionId;
    private AgentId userId;
    private ArtifactId arId;
    private String arName;
    private Op op;
    
    public EvEnvNewOpToExec(WorkspaceId wid, long actionId, AgentId userId, ArtifactId arId, String arName, Op op) {
        this.wid = wid;
        this.actionId = actionId;
        this.userId = userId;
        this.arId = arId;
        this.arName = arName;
        this.op = op;
    }

    public WorkspaceId getWid() {
        return wid;
    }

    public long getActionId() {
        return actionId;
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
    
}
