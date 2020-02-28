package jcmsim;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import jcmsim.events.EvAgExtActRequest;
import jcmsim.events.EvAgFetchPercept;
import jcmsim.events.EvAgNewPerceptNotified;

public class ExecContext {
    
    private long currentTime;
    private long currentTimeMicro;
    
    private String id;
    private ArrayList<ECEvent> eventHistory;
    private ArrayList<ECActivity> activityHistory;
    
    private int oldestPendingActivityIndex;
    
    public enum ECType { AGENT, ARTIFACT, WORKSPACE, COMM };
    private ECType type;
    //public enum 
    
    private List<PendingECEvent> fes;
    private List<PendingECEvent> fees;
    private List<String> fesHistory;
    
    public ExecContext(String id, ECType type, long startTime) {
        this.id = id;
        this.type = type;
        this.currentTime = startTime;
        eventHistory = new ArrayList<ECEvent>();
        fes = new ArrayList<PendingECEvent>();
        fees = new ArrayList<PendingECEvent>();
        fesHistory = new ArrayList<String>();
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

    
    /* API */
    
    public void waitFor(long dt) {
    	
    }
    
    /* this is about notifying event execution */
    

    public synchronized void notifyEventExecution(ECEvent ev) {
        
        bindEvent(ev);

        /* assign event time */
    	
        long currentTime = System.currentTimeMillis();
        long currentTimeMicro = System.nanoTime() / 1000;
        ev.setTime(currentTime, currentTimeMicro);  
        
        /* advance  time to event one */

        this.currentTimeMicro = ev.getTimeInMicroSec();
        this.currentTime = ev.getTime();
        
        /* notify event execution */
        
        trackEventExecution(ev);
    }
    
   
    /* for internal events */
    
    public synchronized PendingECEvent scheduleEventExecution(ECEvent ev, Simulation sim) {
    	
        bindEvent(ev);
        
        if (!ev.isTimeAssigned()){
        	sim.assignTimeToScheduledEvent(ev, this);            	
    	}

        PendingECEvent pe = new PendingECEvent(ev);
        fes.add(pe);
        fesHistory.add("added " + ev.getName() + " at " + this.currentTimeMicro + " by " + Thread.currentThread().getName());
        // log("scheduled " + ev);
        return pe;
    }
    
    /* for external events */
    public synchronized PendingECEvent scheduleExtEventExecution(ECEvent ev, Simulation sim) {
    	
        bindEvent(ev);
        
        if (!ev.isTimeAssigned()){
        	sim.assignTimeToScheduledEvent(ev, this);            	
    	}

        PendingECEvent pe = new PendingECEvent(ev);
        fees.add(pe);
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
            if (pe.getEvent().getTimeInMicroSec() < min) {
                min = pe.getEvent().getTimeInMicroSec();
                sel = pe;   
            }
        }
        
        /* check with external future events */
        PendingECEvent extsel = getNearestExtECEvent();
        if (extsel != null) {
        	if (sel != null) {
        		if (extsel.getEvent().getTimeInMicroSec() < sel.getEvent().getTimeInMicroSec()) {
        			fees.remove(extsel);
                    ECEvent ev = extsel.getEvent();

                    if (currentTimeMicro > ev.getTimeInMicroSec()) {
                    	ev.setTime(this.currentTime, this.currentTimeMicro);
                    } else {
        	            this.currentTimeMicro = ev.getTimeInMicroSec();
        	            this.currentTime = ev.getTime();
                    }

                    /* execute event */
                    trackEventExecution(ev);
                    return extsel;
        		}
        	} else {
        		if (extsel.getEvent().getTimeInMicroSec() <= this.currentTimeMicro) {
        			fees.remove(extsel);
                    ECEvent ev = extsel.getEvent();
                    ev.setTime(this.currentTime, this.currentTimeMicro);

                    /* execute event */
                    trackEventExecution(ev);
                    return extsel;
        			
        		}
        	}
        }
        
        if (sel != null) {
            fes.remove(sel);
            fesHistory.add("removed " + sel.getEvent() + " at " + this.currentTimeMicro);

            ECEvent ev = sel.getEvent();

            if (currentTimeMicro > ev.getTimeInMicroSec()) {
            	// log("BUG: ROLLING BACK TIME - EVENT IN THE PAST "  + ev.getName());
            	ev.setTime(this.currentTime, this.currentTimeMicro);
            } else {
	        	/* advance  time of the EC */
            	
	            this.currentTimeMicro = ev.getTimeInMicroSec();
	            this.currentTime = ev.getTime();
	            
            }
            
            /* execute event */
            trackEventExecution(ev);
        }
        return sel;
    }
    
    private PendingECEvent getNearestExtECEvent() {
        long min = Long.MAX_VALUE;
        PendingECEvent sel = null;
        for (PendingECEvent pe: fees) {
            if (pe.getEvent().getTimeInMicroSec() < min) {
                min = pe.getEvent().getTimeInMicroSec();
                sel = pe;   
            }
        }
        return sel;
    }

    private void log(String msg) {
        System.out.println("[EC " + this.getId() +"] " + msg);
    }
    
}
