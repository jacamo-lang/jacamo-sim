package jcmsim;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import jcmsim.events.EvAgExtActRequest;

public class ExecContext {
    
    private long currentTime;
    private long currentTimeMicro;
    
    private String id;
    private ArrayList<ECEvent> eventHistory;
    private ArrayList<ECActivity> activityHistory;
    
    private int oldestPendingActivityIndex;
    
    public enum ECType { AGENT, ARTIFACT, WORKSPACE };
    private ECType type;
    //public enum 
    
    private List<PendingECEvent> fes;
    
    public ExecContext(String id, ECType type, long startTime) {
        this.id = id;
        this.type = type;
        this.currentTime = startTime;
        eventHistory = new ArrayList<ECEvent>();
        fes = new ArrayList<PendingECEvent>();
        activityHistory = new ArrayList<ECActivity>();
        oldestPendingActivityIndex = -1;
    }

    public String getId() {
        return id;
    }

    public ECType getType() {
        return type;
    }
    
    public synchronized void addActivity(ECActivity act) {
        activityHistory.add(act);
        if (act.isPending()) {
        	if (oldestPendingActivityIndex == -1) {
        		oldestPendingActivityIndex = activityHistory.size() - 1;
        	}
        }
    }
    
    public synchronized long getCurrentTime() {
        return currentTime;
    }

    public synchronized long getCurrentTimeInMicroSec() {
        return currentTimeMicro;
    }
 
    public synchronized ArrayList<ECEvent> getEventHistory(){
        ArrayList<ECEvent> h = new ArrayList<ECEvent>();
        for (ECEvent ev: eventHistory) {
            h.add(ev);
        }
        return h;
    }

    public synchronized ArrayList<ECActivity> getActivityHistory(){
        ArrayList<ECActivity> h = new ArrayList<ECActivity>();
        for (ECActivity a: activityHistory) {
            h.add(a);
        }
        return h;
    }
    
    public synchronized ECActivity getActivityEndingWith(String actName, ECEvent ev){
    	for (int i = activityHistory.size() - 1; i >= 0; i--) {
    		ECActivity act = activityHistory.get(i);
    		if (act.getName().equals(actName) && (act.getEndEvent() == ev)) {
				return act;
			}
		}
    	return null;
	}

        
    /* this is about notifying event execution */
    

    public synchronized void notifyEventExecution(ECEvent ev) {
        
        bindEvent(ev);

        /* assign event time */
    	
        long currentTime = System.currentTimeMillis();
        long currentTimeMicro = System.nanoTime() / 1000;
        ev.setTime(currentTime, currentTimeMicro);  

        /* notify event execution */
        
        trackEventExecution(ev);
        
        /* advance  time to event one */
        
        this.currentTimeMicro = ev.getTimeInMicroSec();
        this.currentTime = ev.getTime();
        
    }
    
    public synchronized void notifyEventExecution(ECEvent ev, Simulation sim) {

        bindEvent(ev);

        /* in simulation mode, first the event/activity structures
    	 * are updated, then the time can be assigned
    	 */

        trackEventExecution(ev);

        if (!ev.isTimeAssigned()){
			sim.assignTime(ev, this);            	
		} 
        
        /* advance  time of the EC */
        
        this.currentTimeMicro = ev.getTimeInMicroSec();
        this.currentTime = ev.getTime();
    }
    
    public synchronized PendingECEvent scheduleEventExecution(ECEvent ev, Simulation sim) {
    	
        bindEvent(ev);
        
        if (!ev.isTimeAssigned()){
        	sim.assignTime(ev, this);            	
    	} else {
            currentTimeMicro = ev.getTimeInMicroSec();
            currentTime = ev.getTime();
    	}

        PendingECEvent pe = new PendingECEvent(ev);
        fes.add(pe);
        // log("scheduled " + ev);
        return pe;
    }
    
    /* binding a new event to existing activities and events */
    
    private void bindEvent(ECEvent ev) {    	
        if (ev.isActivityEnd()) {
        	int lastIndex = oldestPendingActivityIndex == -1 ? 0 : oldestPendingActivityIndex;
            int oldestResolvedActIndex = -1;
            
        	for (int i = activityHistory.size() - 1; i >= lastIndex; i--) {
            	ECActivity act = activityHistory.get(i);
                if (act.isPending()) {
                    if (act.checkAndAppyCompletion(ev)) {
                        act.setEndEvent(ev);
                        ev.addNewEndingActivity(act);
                    	if (!ev.hasCausingEvent()) {
                    		ev.setCausingEvent(new CausalLink(act.getBeginEvent(), this.id));
                    	}
                        oldestResolvedActIndex = i;
                    }
                }
            }
        	
            /* resolved the oldest => finding the new oldest  */
            
            if (oldestResolvedActIndex == oldestPendingActivityIndex) {
            	oldestPendingActivityIndex = -1;              
            	for (int i = oldestResolvedActIndex + 1; i < activityHistory.size(); i++) {
                	ECActivity act = activityHistory.get(i);
                	if (act.isPending()) {
                		oldestPendingActivityIndex = i;
                		break;
                	}
            	}
            }
        }
        
    }
    
    /* track event execution */
    
    private void trackEventExecution(ECEvent ev) {
        eventHistory.add(ev);

        for (ECActivity _act:  ev.getActivitiesToBegin()) {
            activityHistory.add(_act);
            ev.addNewBeginningActivity(_act);
        	if (oldestPendingActivityIndex == -1) {
        		oldestPendingActivityIndex = activityHistory.size() - 1;
        	}
        }
    
    }
    
    
    public synchronized PendingECEvent selectEventAndAdvanceTime() {

        /* select and remove next event */
        
        long min = Long.MAX_VALUE;
        PendingECEvent sel = null;
        for (PendingECEvent pe: fes) {
            if (pe.getEvent().getTime() < min) {
                min = pe.getEvent().getTimeInMicroSec();
                sel = pe;   
            }
        }
        
        if (sel != null) {
            fes.remove(sel);

            /* execute event */
            trackEventExecution(sel.getEvent());

            /* advance  time of the EC */
            
            this.currentTimeMicro = sel.getEvent().getTimeInMicroSec();
            this.currentTime = sel.getEvent().getTime();
                 
        }
        return sel;
    }
    
    private void log(String msg) {
        System.out.println("[EC " + this.getId() +"] " + msg);
    }
    
}
