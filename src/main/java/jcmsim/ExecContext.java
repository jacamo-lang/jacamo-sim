package jcmsim;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import jcmsim.events.EvAgExtActRequest;

public class ExecContext {
    
    private long currentTime;
    private long currentTimeMicro;
    
    private String id;
    private List<ECEvent> eventHistory;
    private List<ECActivity> activityHistory;
    private Simulation sim;
    public enum ECType { AGENT, ARTIFACT, WORKSPACE };
    private ECType type;
    //public enum 
    
    private List<PendingECEvent> fes;
    
    public ExecContext(String id, ECType type, long startTime, Simulation sim, SimulationController contr) {
        this.id = id;
        this.type = type;
        this.currentTime = startTime;
        this.sim = sim;
        eventHistory = new ArrayList<ECEvent>();
        fes = new ArrayList<PendingECEvent>();
        activityHistory = new ArrayList<ECActivity>();
    }

    public String getId() {
        return id;
    }

    public ECType getType() {
        return type;
    }
    
    public void addActivity(ECActivity act) {
        activityHistory.add(act);
    }
    
    public long getCurrentTime() {
        return currentTime;
    }
 
    public synchronized List<ECEvent> getEventHistory(){
        ArrayList<ECEvent> h = new ArrayList<ECEvent>();
        for (ECEvent ev: eventHistory) {
            h.add(ev);
        }
        return h;
    }

    public synchronized List<ECActivity> getActivityHistory(){
        ArrayList<ECActivity> h = new ArrayList<ECActivity>();
        for (ECActivity a: activityHistory) {
            h.add(a);
        }
        return h;
    }
    
    /* this is about notifying event execution */
    
    public synchronized void notifyEventExecution(ECEvent ev) {
    	eventHistory.add(ev);

    	for (ECActivity _act:  ev.getActivitiesToBegin()) {
        	activityHistory.add(_act);
        }

        if (sim.isRealTimeMode()) {    
            currentTime = System.currentTimeMillis();
            currentTimeMicro = System.nanoTime() / 1000;
            
            ev.setTime(currentTime, currentTimeMicro);            
            if (ev.isActivityEnd()) {
                for (ECActivity act: activityHistory) {
                    if (act.isPending()) {
                        if (act.checkAndAppyCompletion(ev)) {
                            act.setEndEvent(ev);
                            break;
                        }
                    }
                }
            }
        } else {
            if (ev.isActivityEnd()) {
                for (ECActivity act: activityHistory) {
                    if (act.isPending()) {
                        if (act.checkAndAppyCompletion(ev)) {
                            act.setEndEvent(ev);                        
        	                currentTime = act.getBeginEvent().getTime() + sim.getDuration(act);
                            ev.setTime(currentTime);
                            break;
                        }
                    }
                }
            } else {
                /* if the event is sporadic.. */           	
            	ev.setTime(currentTime);        
            } 
        }
	}

    public synchronized PendingECEvent scheduleEvent(ECEvent ev) {
    	if (sim.isRealTimeMode()) {    
            this.notifyEventExecution(ev);
            return null;
        } else {
            if (ev.isActivityEnd()) {
                for (ECActivity act: activityHistory) {
                    if (act.isPending()) {
                        if (act.checkAndAppyCompletion(ev)) {
                            act.setEndEvent(ev);                        
                            ev.setTime(act.getBeginEvent().getTime() + sim.getDuration(act));
                            break;
                        }
                    }
                }
            } else {
                /* if the event is sporadic.. */           	
            	ev.setTime(currentTime);        
            } 	
            PendingECEvent pe = new PendingECEvent(ev);
            fes.add(pe);
            // log("scheduled " + ev);
            return pe;
        }
	}
    
    public PendingECEvent selectEventAndAdvanceTime() {

    	/* select and remove next event */
    	
    	long min = Long.MAX_VALUE;
    	PendingECEvent sel = null;
    	for (PendingECEvent pe: fes) {
    		if (pe.getEvent().getTime() < min) {
    			min = pe.getEvent().getTime();
    			sel = pe;   
    		}
    	}
    	
    	if (sel != null) {
    		fes.remove(sel);
    	
    		/* advance simulated time to desired exection time */
    		this.currentTime = min;
    	}
    	return sel;
    }
    
    private void log(String msg) {
    	System.out.println("[EC " + this.getId() +"] " + msg);
    }
    
}
