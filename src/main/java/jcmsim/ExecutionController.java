package jcmsim;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import jcmsim.ExecContext.ECType;
import jcmsim.Simulation.Mode;


public class ExecutionController  {
    
    private static ExecutionController contr;
    private Simulation sim;
    
	private HashMap<String,Object> properties;
    public enum Mode { Normal, Tracking, Simulation};
    private Mode mode;

    
    private ConcurrentHashMap<String, ExecContext> ectxs;
    private LinkedBlockingQueue<ExecContext> ectxsList;
    
    private ExecControllerLogger logger;
    private SimulationLoop simuLoop;
    private ExecControllerView view;
    
    private ReentrantLock ectxControlFlowsLock;
    private Condition ectxControlFlowsStateUpdated;
    
    private List<Thread> ectxControlFlows;
    private HashMap<String, Boolean> ectxControlFlowsState;
    
    public static ExecutionController getExecController() {
        synchronized (ExecutionController.class) {
            if (contr == null) {
                contr = new ExecutionController();
            }
        }
        return contr;
    }

    public Collection<ExecContext> getContexts(){
        return ectxs.values();
    }
    
    private ExecutionController() {
        
        ectxControlFlowsLock = new ReentrantLock();
        ectxControlFlowsStateUpdated = ectxControlFlowsLock.newCondition();
        
        ectxControlFlows = new ArrayList<Thread>();
        ectxControlFlowsState = new HashMap<String, Boolean>();
        
        ectxs = new ConcurrentHashMap<String, ExecContext> ();
        ectxsList = new LinkedBlockingQueue<ExecContext>();
        this.mode = Mode.Normal;
    }
        
    public void initTrackingMode() {
    	this.mode = mode.Tracking;
        view = new ExecControllerView();
        
        logger = new ExecControllerLogger(this, view);
        logger.start();
        this.mode = Mode.Tracking;
    }

    public boolean isTrackingMode() {
        return mode.equals(Mode.Tracking);  
    }

    public boolean isSimulationMode() {
        return mode.equals(Mode.Simulation);  
    }

    public boolean isNormalMode() {
        return mode.equals(Mode.Normal);  
    }
    
    public void initSimulationMode(Simulation sim) {
        this.sim = sim;
        this.mode = Mode.Simulation;

        view = new ExecControllerView();
        
        logger = new ExecControllerLogger(this, view);
        logger.start();
        
        simuLoop = new SimulationLoop(this);
        simuLoop.start();
    }
     
    
    public void waitFor(String ctxId, long dt) {
    	ExecContext ctx = ectxs.get(ctxId);
        if (ctx != null ) {
        	
        }
    }
    
    /* =============== */

    /* Called by JaCaMo Platform control flows */

    /* Execution contexts management */
    
    public void notifyNewExecContext(String ctxId, ECType type, long initialTime) {
        ExecContext ctx = new ExecContext(ctxId, type, 0);
        ectxs.put(ctxId, ctx);
        ectxsList.add(ctx);
    }

    public ECEvent getLastEvent(String ctxId) {
        ExecContext ctx = ectxs.get(ctxId);
        if (ctx != null ) {
            List<ECEvent> h = ctx.getEventHistory();
            if (!h.isEmpty()) {
                return h.get(h.size() - 1);
            }
        }
        return null;        
    }
    
    public List<ECEvent> getEventHistory(String ctxId) {
        ExecContext ctx = ectxs.get(ctxId);
        if (ctx != null ) {
            return ctx.getEventHistory();
        }
        return null;        
    }

    /* notification of event execution / scheduling */
       
    public void notifyEventExec(String ctxId, ECEvent ev) {  
    	if (!isNormalMode()) {    		
            ExecContext ctx = ectxs.get(ctxId);
	        if (ctx != null) {
	        	if (isTrackingMode()) {
	        		ctx.notifyEventExecution(ev);
	        	} else {	        		
	        		PendingECEvent evScheduled = ctx.scheduleEventExecution(ev, sim);
	            	/* before releasing the control, wait for the event to be scheduled */
	                try {
	                    this.notifyECControlFlowWaiting(Thread.currentThread());
	                    // log(Thread.currentThread().getName() + " - wait for sched: " + evScheduled.getEvent().getName() + " in " + ctx.getId());
	                    evScheduled.waitForSched();             
	                    // log(Thread.currentThread().getName() + " - signaled for exec of " + pev.getEvent());
	                    this.notifyECControlFlowRunning(Thread.currentThread());
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	        	
	        	}		
	        }
    	}   		
    }

    public void notifyEventFromExternalCtx(String ctxId, ECEvent ev) {  
    	if (!isNormalMode()) {    		
            ExecContext ctx = ectxs.get(ctxId);
	        if (ctx != null) {
	        	if (isTrackingMode()) {
	        		ctx.notifyEventExecution(ev);
	        	} else {	        		
	        		PendingECEvent evScheduled = ctx.scheduleExtEventExecution(ev, sim);
	            	/* before releasing the control, wait for the event to be scheduled */
	                try {
	                    this.notifyECControlFlowWaiting(Thread.currentThread());
	                    // log(Thread.currentThread().getName() + " - wait for sched: " + evScheduled.getEvent().getName() + " in " + ctx.getId());
	                    evScheduled.waitForSched();             
	                    // log(Thread.currentThread().getName() + " - signaled for exec of " + pev.getEvent());
	                    this.notifyECControlFlowRunning(Thread.currentThread());
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	        	
	        	}		
	        }
    	}   		
    }
    
    
    public void notifyECControlFlowStarted(Thread t) {
        ectxControlFlowsLock.lock();
        // log("active worker " + t.getName() + " started working");
        ectxControlFlows.add(t);
        ectxControlFlowsState.put(t.getName(), true);
        this.ectxControlFlowsStateUpdated.signalAll();
        ectxControlFlowsLock.unlock();
    }

    public void notifyECControlFlowNotWorking(Thread t) {
        ectxControlFlowsLock.lock();
        // log("active worker " + t.getName() + " no  working");
        ectxControlFlowsState.put(t.getName(), false);
        ectxControlFlowsLock.unlock();
    }

    public void notifyECControlFlowResumedWorking(Thread t) {
        ectxControlFlowsLock.lock();
        // log("active worker " + t.getName() + " resumed working");
        ectxControlFlowsState.put(t.getName(), true);
        ectxControlFlowsLock.unlock();
    }
    
    public void notifyECControlFlowFinished(Thread t) {
        ectxControlFlowsLock.lock();
        // log("active worker " + t.getName() + " stopped working");
        ectxControlFlows.remove(t);
        ectxControlFlowsState.remove(t.getName());      
        this.ectxControlFlowsStateUpdated.signalAll();
        ectxControlFlowsLock.unlock();
    }

    public void notifyECControlFlowWaiting(Thread t) throws InterruptedException {
        ectxControlFlowsLock.lock();
        // log("active worker " + t.getName() + " going to block");
        ectxControlFlowsState.put(t.getName(), false);
        this.ectxControlFlowsStateUpdated.signalAll();
        ectxControlFlowsLock.unlock();
    }

    public void notifyECControlFlowRunning(Thread t) throws InterruptedException {
        ectxControlFlowsLock.lock();
        // log("active worker " + t.getName() + " unblocked");
        ectxControlFlowsState.put(t.getName(), true);
        this.ectxControlFlowsStateUpdated.signalAll();
        ectxControlFlowsLock.unlock();
    }    
     
    /* called by the simulator loop */
    
    public void waitForAllControlFlowsBlocked() throws InterruptedException  {
        /* wait current EC control flows to complete current event execution */
        
        ectxControlFlowsLock.lock();
    
        boolean allBlocked = false;
        while (!allBlocked) {
            allBlocked = true;
            // log("checking for active workers - " + ectxControlFlowsState.size());
            for (Entry<String, Boolean> t: ectxControlFlowsState.entrySet()) {
                if (t.getValue()) {
                    // log("worker " + t.getKey() + " not blocked ");
                    allBlocked = false;
                    break;
                }
            }
            if (!allBlocked) {
                ectxControlFlowsStateUpdated.await();
                // log("signaled.");
            } 
        }
        ectxControlFlowsLock.unlock();
    }
    
    public Iterator getContextInterator() {
    	return ectxsList.iterator();
    }
   
    public void notifyTimeUpdated(ExecContext ctx) {
    	if (sim != null) {
    		sim.notifyTimeUpdated(ctx);
    	}
    }
    
    /* called by timing functions */
    
    public Collection<ExecContext> getExecContexts(){
    	return ectxs.values();
    }

    
    public ExecContext getExecContext(String id){
    	return ectxs.get(id);
    }
    
    
    private void log(String msg) {
        System.out.println("[EXECUTION CONTROLLER] " + msg);
    }

   
 }
