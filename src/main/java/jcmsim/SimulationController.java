package jcmsim;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import jcmsim.ExecContext.ECType;


public class SimulationController extends Thread  {
    
    private static SimulationController contr;
    private Simulation sim;
    
    private ConcurrentHashMap<String, ExecContext> ectxs;
    
    private SimulationLogger logger;
    private SimulationLoop loop;
    private SimulationView view;
    
    private ReentrantLock ectxControlFlowsLock;
    private Condition ectxControlFlowsStateUpdated;
    
    private List<Thread> ectxControlFlows;
    private HashMap<String, Boolean> ectxControlFlowsState;
    
    public static SimulationController getSimulationController() {
        synchronized (SimulationController.class) {
            if (contr == null) {
                contr = new SimulationController();
            }
        }
        return contr;
    }

    public Collection<ExecContext> getContexts(){
        return ectxs.values();
    }
    
    private SimulationController() {
        
        ectxControlFlowsLock = new ReentrantLock();
        ectxControlFlowsStateUpdated = ectxControlFlowsLock.newCondition();
        
        ectxControlFlows = new ArrayList<Thread>();
        ectxControlFlowsState = new HashMap<String, Boolean>();
        
        ectxs = new ConcurrentHashMap<String, ExecContext> ();
    }
        
    public void init(Simulation sim) {
        this.sim = sim;
        view = new SimulationView();
        
        logger = new SimulationLogger(this, view);
        logger.start();

        if (!sim.isRealTimeMode()) {                 
            loop = new SimulationLoop();
            loop.start();
        }        
    }
          
    
    
    public void createNewExecContext(String ctxId, ECType type, long initialTime) {
        ExecContext ctx = new ExecContext(ctxId, type, 0, sim, this);
        ectxs.put(ctxId, ctx);
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

    
    /* CALLED BY EC CONTROL FLOWS */
     
    /* called by an EC control flow to schedule an event on a specific EC */
    
    public PendingECEvent scheduleEvent(String ctxId, ECEvent ev) {
        ExecContext ctx = ectxs.get(ctxId);
        if (ctx != null) {
            return ctx.scheduleEvent(ev);
        } else {
            return null;
        }
    }

   
    /* called by EC control flows to notify event execution - for tracking */
    
    public void notifyEventExecution(String ctxId, ECEvent ev) {
        ExecContext ctx = ectxs.get(ctxId);
        if (ctx != null) {
            ctx.notifyEventExecution(ev);
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
    
    public void waitToExecEvent(PendingECEvent pev) {
        if (!sim.isRealTimeMode()) {
            
            try {
                this.notifyECControlFlowWaiting(Thread.currentThread());
                // log(Thread.currentThread().getName() + " - wait for exec of " + pev.getEvent());
                pev.waitForSched();             
                // log(Thread.currentThread().getName() + " - signaled for exec of " + pev.getEvent());
                this.notifyECControlFlowRunning(Thread.currentThread());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
   
    
    private void log(String msg) {
        System.out.println("[SIMULATION CONTROLLER] " + msg);
    }

    
    /* MAIN SIMULATION LOOP */
    
    class SimulationLoop extends Thread {

        public void run() {     
            while (true) {
                try {
                    /* wait current EC control flows to complete current event execution */
                    
                    ectxControlFlowsLock.lock();
                
                    boolean allBlocked = false;
                    while (!allBlocked) {
                        allBlocked = true;
                        // log("checking for active workers - " + activeWorkerState.size());
                        for (Entry<String, Boolean> t: ectxControlFlowsState.entrySet()) {
                            if (t.getValue()) {
                                // log("worker " + t.getKey() + " not blocked ");
                                allBlocked = false;
                                break;
                            }
                        }
                        if (!allBlocked) {
                            ectxControlFlowsStateUpdated.await();
                        } 
                    }
                    ectxControlFlowsLock.unlock();

                    
                    /* for each execution context select and exec an event from FES and update simulated time */
                    
                    for (ExecContext ec: ectxs.values()) {
                        
                        PendingECEvent pev = ec.selectEventAndAdvanceTime();
                    
                        /* exec selected event */
                        
                        if (pev != null) {
                            pev.signalExecution();
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
                
    }
}
