package jcmsim;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import jcmsim.events.EvAgExtActRequest;

public class EvCtx {
    
    private long currentTime;
    private String id;
    private List<EvCtxEvent> eventHistory;
    private List<EvCtxActivity> activityHistory;
    private Simulation sim;
    public enum EvCtxType { AGENT, ARTIFACT, WORKSPACE };
    private EvCtxType type;
    
    public EvCtx(String id, EvCtxType type, long startTime, Simulation sim) {
    	this.id = id;
    	this.type = type;
        this.currentTime = startTime;
        this.sim = sim;
        eventHistory = new ArrayList<EvCtxEvent>();
        activityHistory = new ArrayList<EvCtxActivity>();
    }

    public String getId() {
    	return id;
    }

    public EvCtxType getType() {
    	return type;
    }
    
    public void addActivity(EvCtxActivity act) {
        activityHistory.add(act);
    }
    
    public long getCurrentTime() {
        return currentTime;
    }
 
    public synchronized List<EvCtxEvent> getEventHistory(){
    	ArrayList<EvCtxEvent> h = new ArrayList<EvCtxEvent>();
    	for (EvCtxEvent ev: eventHistory) {
    		h.add(ev);
    	}
    	return h;
    }

    public synchronized List<EvCtxActivity> getActivityHistory(){
    	ArrayList<EvCtxActivity> h = new ArrayList<EvCtxActivity>();
    	for (EvCtxActivity a: activityHistory) {
    		h.add(a);
    	}
    	return h;
    }
    
    public synchronized void notifyNewEvent(EvCtxEvent ev) {
        
        eventHistory.add(ev);

        Optional<EvCtxActivity> _act = ev.getActivityToBegin();
        if (_act.isPresent()) {
        	activityHistory.add(_act.get());
        }

        if (sim.isTrackingMode()) {
    		currentTime = System.currentTimeMillis();
    		ev.setTime(currentTime);
    		
        	if (ev.isActivityEnd()) {
    	        for (EvCtxActivity act: activityHistory) {
    	        	if (act.isPending()) {
    	        		if (act.checkAndAppyCompletion(ev)) {
    	        			act.setEndEvent(ev);
    	        			break;
    	        		}
    	        	}
    	        }
            }
    	} else {
    	
	    	/* 
	    	 * if the event is the end of an activity
	    	 * the duration maybe computed by a function
	    	 * that depends on the type of activity..
	    	 */
	    	if (ev.isActivityEnd()) {
	            for (EvCtxActivity act: activityHistory) {
		        	if (act.isPending()) {
		        		if (act.checkAndAppyCompletion(ev)) {
		        			act.setEndEvent(ev);	        			
		        			long dur = sim.getDuration(act);
		        			ev.setTime(act.getBeginEvent().getTime() + dur);
		        			break;
		        		}
		        	}
		        }
	        } else {
	        	/* sporadic, simu mode */
	        	ev.setTime(currentTime);        
	        } 
    	}

    }
        
}
