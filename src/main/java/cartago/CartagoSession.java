package cartago;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.*;
import cartago.events.*;
import cartago.util.agent.ArtifactObsProperty;
import jason.asSemantics.ActionExec;
import jcmsim.CausalLink;
import jcmsim.ECEvent;
import jcmsim.PendingECEvent;
import jcmsim.ExecutionController;
import jcmsim.events.EvAgExtActRequest;
import jcmsim.events.EvWspActDispatch;
import jcmsim.events.EvArtObsStateEvent;

/**
 * Class to manage a working session of an agent inside a workspace
 * 
 * @author aricci
 *
 */
public class CartagoSession implements ICartagoSession, ICartagoCallback {

    // one context for workspace, the agent can work in multiple workspaces
    private ConcurrentHashMap<WorkspaceId, ICartagoContext> contexts;
    private LinkedList<WorkspaceId> contextOrderedList;
    
    // queue where percepts are notified by the environment
    private java.util.concurrent.ConcurrentLinkedQueue<CartagoEvent> perceptQueue;

    private ICartagoListener agentArchListener;
    private AtomicLong actionId;

    private AgentCredential credential;
    private String agentRole;
    private ArtifactId agentContextId;  
        
    CartagoSession(AgentCredential credential, String agentRole, ICartagoListener listener) throws CartagoException {
        contexts = new ConcurrentHashMap<WorkspaceId, ICartagoContext>();
        contextOrderedList = new java.util.LinkedList<WorkspaceId>();
        perceptQueue = new java.util.concurrent.ConcurrentLinkedQueue<CartagoEvent>();
        agentArchListener = listener;
        this.agentRole = agentRole;
        this.credential = credential;
        actionId = new AtomicLong(0);
    }

    void init(ArtifactId agentContextId, WorkspaceId initialWspId, ICartagoContext startContext) {
        this.agentContextId = agentContextId;
        contexts.put(initialWspId, startContext);
        synchronized (contextOrderedList) {
            contextOrderedList.addFirst(initialWspId);
        }
    }

    public ArtifactId getAgentContextArtifactId() {
        return agentContextId;
    }

    public String getEnvName() {
        return CartagoEnvironment.getInstance().getName();
    }
    
    public long doAction(ArtifactId aid, Op op, IAlignmentTest test, long timeout) throws CartagoException  {
        long actId = actionId.incrementAndGet();
        ICartagoContext ctx = null;
        synchronized (this){
            ctx = contexts.get(aid.getWorkspaceId());
            if (ctx != null) {
                
                /* @SIMU */
                ExecutionController contr = ExecutionController.getExecController();
                EvAgExtActRequest ev = (EvAgExtActRequest) contr.getLastEvent(this.credential.getId());
                EvWspActDispatch evc = new EvWspActDispatch(aid.getWorkspaceId().getName(), actId, aid.getName(), op);
                ev.setCausedEvent(evc);
                evc.setCausingEvent(new CausalLink(ev,this.credential.getId()));
                contr.notifyEventExec(aid.getWorkspaceId().getName(), evc);              

                ctx.doAction(actId, aid.getName(), op, test, timeout);
                return actId;
            } else {
                throw new CartagoException("Wrong workspace.");
            }
        }
    }

    public long doAction(WorkspaceId wspId, String artName, Op op, IAlignmentTest test, long timeout)
            throws CartagoException {
        long actId = actionId.incrementAndGet();
        ICartagoContext ctx = null;
        synchronized (this){
            ctx = contexts.get(wspId);
            if (ctx != null) {

                /* @SIMU */
                ExecutionController contr = ExecutionController.getExecController();
                EvAgExtActRequest ev = (EvAgExtActRequest) contr.getLastEvent(this.credential.getId());
                EvWspActDispatch evc = new EvWspActDispatch(wspId.getName(), actId, artName, op);
                evc.setCausingEvent(new CausalLink(ev,this.credential.getId()));
                
                ev.setCausedEvent(evc);
                contr.notifyEventExec(wspId.getName(), evc);             
                
                ctx.doAction(actId, artName, op, test, timeout);
                return actId;
            } else {
                throw new CartagoException("Wrong workspace.");
            }
        }
    }
    
    public long doAction(String wspName, String artName, Op op, IAlignmentTest test, long timeout)
            throws CartagoException {
        long actId = actionId.incrementAndGet();
        ICartagoContext ctx = null;
        synchronized (this){
            for (Map.Entry<WorkspaceId, ICartagoContext> e: contexts.entrySet()){
                if (e.getKey().getName().equals(wspName)) {
                    ctx = e.getValue();
                    break;
                }
            }
            if (ctx != null) {
                
                /* @SIMU */
                ExecutionController contr = ExecutionController.getExecController();
                EvAgExtActRequest ev = (EvAgExtActRequest) contr.getLastEvent(this.credential.getId());
                EvWspActDispatch evc = new EvWspActDispatch(wspName, actId, artName, op);
                evc.setCausingEvent(new CausalLink(ev,this.credential.getId()));
                
                ev.setCausedEvent(evc);
                contr.notifyEventExec(wspName, evc);             
                
                ctx.doAction(actId, artName, op, test, timeout);
                return actId;
            } else {
                throw new CartagoException("Wrong workspace.");
            }
        }
    }

    public long doAction(Op op, WorkspaceId wspId, IAlignmentTest test, long timeout) throws CartagoException {
        long actId = actionId.incrementAndGet();
        ICartagoContext ctx = null;
        synchronized (this){
            ctx = contexts.get(wspId);
            if (ctx != null) {
                
                /* @SIMU */
                ExecutionController contr = ExecutionController.getExecController();
                EvAgExtActRequest ev = (EvAgExtActRequest) contr.getLastEvent(this.credential.getId());
                EvWspActDispatch evc = new EvWspActDispatch(wspId.getName(), actId, null, op);
                evc.setCausingEvent(new CausalLink(ev,this.credential.getId()));
                
                ev.setCausedEvent(evc);
                contr.notifyEventExec(wspId.getName(), evc);             
                
                ctx.doAction(actId, op, test, timeout);
                return actId;
            } else {
                throw new CartagoException("Workspace not found.");
            }
        }
    }

    public long doAction(Op op, String wspName, IAlignmentTest test, long timeout) throws CartagoException {
        long actId = actionId.incrementAndGet();
                
        ICartagoContext ctx = null;
        synchronized (this){
            for (Map.Entry<WorkspaceId, ICartagoContext> e: contexts.entrySet()){
                if (e.getKey().getName().equals(wspName)) {
                    ctx = e.getValue();
                    break;
                }
            }
            if (ctx != null) {

                /* @SIMU */
                ExecutionController contr = ExecutionController.getExecController();
                EvAgExtActRequest ev = (EvAgExtActRequest) contr.getLastEvent(this.credential.getId());
                EvWspActDispatch evc = new EvWspActDispatch(wspName, actId, null, op);
                evc.setCausingEvent(new CausalLink(ev,this.credential.getId()));
                
                ev.setCausedEvent(evc);
                contr.notifyEventExec(wspName, evc);             

                
                ctx.doAction(actId, op, test, timeout);
                return actId;
            } else {
                throw new CartagoException("Workspace not found.");
            }
        }
    }

    public List<WorkspaceId> getJoinedWorkspaces() throws CartagoException {
        List<WorkspaceId> wsps = new LinkedList<WorkspaceId>();
        for (java.util.Map.Entry<WorkspaceId, ICartagoContext> c : contexts.entrySet()) {
            wsps.add(c.getKey());
        }
        return wsps;
    }

    // Utility methods

    public WorkspaceId getJoinedWspId(String wspName) throws CartagoException {
        for (java.util.Map.Entry<WorkspaceId, ICartagoContext> c : contexts.entrySet()) {
            if (c.getKey().getName().equals(wspName)) {
                return c.getKey();
            }
        }
        throw new CartagoException("Workspace not joined.");
    }
    
    public WorkspaceId getCurrentWorkspace() {
        synchronized (contextOrderedList) {
            return this.contextOrderedList.getFirst();
        }
    }
    
    /**
     * Make a new artifact instance
     * 
     * @param artifactName logic name
     * @param templateName type
     * @return
     * @throws CartagoException
     */
    public ArtifactId makeArtifact(WorkspaceId wid, String artifactName, String templateName, Object[] params) throws CartagoException {
        OpFeedbackParam<ArtifactId> res = new OpFeedbackParam<ArtifactId>();
        try{
            doAction(new Op("makeArtifact", artifactName, templateName, params, res), wid, null, -1);
        } catch (Exception ex){
            throw new CartagoException();
        }
        return res.get();
    }

    
    //

    /**
     * Fetch a new percept.
     * 
     * To be called in the sense stage of the agent execution cycle.
     * 
     */
    public CartagoEvent fetchNextPercept() {
        return perceptQueue.poll();
    }

    
    private void checkWSPEvents(CartagoEvent ev) {
        if (ev instanceof JoinWSPSucceededEvent) {
            JoinWSPSucceededEvent wspev = (JoinWSPSucceededEvent) ev;               
            WorkspaceId wid = wspev.getWorkspaceId();       
            contexts.put(wspev.getWorkspaceId(), wspev.getContext());
            synchronized (contextOrderedList) {
                contextOrderedList.addFirst(wspev.getWorkspaceId());
            }
        } else if (ev instanceof QuitWSPSucceededEvent) {
            QuitWSPSucceededEvent wspev = (QuitWSPSucceededEvent) ev;
            contexts.remove(wspev.getWorkspaceId());
            synchronized (contextOrderedList) {
                contextOrderedList.remove(wspev.getWorkspaceId());
            }
        }
    }

    public void notifyCartagoEvent(CartagoEvent ev) {
        // System.out.println("NOTIFIED "+ev.getId()+"
        // "+ev.getClass().getCanonicalName());
        
        /* 
         * @SIMU 
         */
        ExecutionController contr = ExecutionController.getExecController();
        
        // PendingECEvent evScheduled = null;
        jcmsim.events.EvAgNewPerceptNotified eventToExec = null;
        
        if (ev instanceof CartagoActionEvent) {
            /* find the wsp from the artifact id */
            CartagoActionEvent ae = (CartagoActionEvent) ev;
            long actId = ae.getActionId();
            
            String agentName = this.credential.getId();
            List<ECEvent> h = contr.getEventHistory(agentName);

            if (h != null) {
                for (int i = h.size() - 1; i >= 0; i--) {
                    ECEvent e = h.get(i);
                    if (e instanceof EvAgExtActRequest) {
                        EvAgExtActRequest req = (EvAgExtActRequest) e;
                        if (req.getCausedEvent().getActionId() == actId) {
                            EvWspActDispatch disp = req.getCausedEvent();
                            
                            jcmsim.events.EvWspPerceptDispatch causingEv = new jcmsim.events.EvWspPerceptDispatch(ev, this.credential.getId());
                            contr.notifyEventExec(disp.getWspName(), causingEv);      
                            eventToExec = new jcmsim.events.EvAgNewPerceptNotified(ev);
                            eventToExec.setCausingEvent(new CausalLink(causingEv, disp.getWspName()));
                            break;
                        }
                    }
                }
            }
            
        } else if (ev instanceof ArtifactObsEvent) {
            /* find the wsp from the artifact id */
            ArtifactObsEvent oe = (ArtifactObsEvent) ev;

            jcmsim.events.EvWspPerceptDispatch causingEv = new jcmsim.events.EvWspPerceptDispatch(ev, this.credential.getId());
            contr.notifyEventExec(oe.getArtifactId().getWorkspaceId().getName(), causingEv);      
            eventToExec = new jcmsim.events.EvAgNewPerceptNotified(ev);
            eventToExec.setCausingEvent(new CausalLink(causingEv, oe.getArtifactId().getWorkspaceId().getName()));
        }
        
        checkWSPEvents(ev);
        boolean keepEvent = true;
        if (agentArchListener != null) {
            keepEvent = agentArchListener.notifyCartagoEvent(ev);
        }
        if (keepEvent) {
 
        	contr.readyToExecEvent(this.credential.getId(), eventToExec);
            
            perceptQueue.add(ev);
        }
    }



}
