package jcmsim;

import java.util.ArrayList;

public abstract class ECEvent {

    private long time;
    private long timeInMicroseconds;
    private CausalLink causalLink;
    private ArrayList<ECActivity> endActivities; 
    private ArrayList<ECActivity> beginActivities; 
    		
    
    private static final ECActivity[] emptyList = new ECActivity[0];
    
    public ECEvent() {
    	time = -1;
    	endActivities = null;
    	beginActivities = null;
    }

    public void setCausingEvent(CausalLink causingEvent) {
    	this.causalLink = causingEvent;
    }

    public ECEvent getEventInTheCausalChain(String name) {
    	if (this.hasCausingEvent()) {
    		ECEvent ev = causalLink.getCausingEvent();
    		if (ev.getName().equals(name)){
    			return ev;
    		} else {
    			return ev.getEventInTheCausalChain(name);
    		}
    	} else {
    		return null;
    	}
    }
    
    public ECActivity getActivityInTheCausalChain(String name) {
    	if (this.hasCausingEvent()) {
    		ECEvent ev = causalLink.getCausingEvent();
    		ECActivity act = ev.getEndingActivity(name);
    		if (act != null) {
    			return act;
    		} else {
    			return ev.getActivityInTheCausalChain(name);
    		}
    	} else {
    		return null;
    	}
    }
    
    public String getName() {
    	return this.getClass().getSimpleName();
    }
    
    public boolean isTimeAssigned() {
    	return time != -1;
    }

    public void addNewBeginningActivity(ECActivity act) {
    	if (beginActivities == null) {
    		beginActivities = new ArrayList<ECActivity>();
    	}
    	beginActivities.add(act);
    }
    
    public ECActivity getBeginningActivity(String name) {
    	for (ECActivity act: beginActivities) {
    		if (act.getName().equals(name)) {
    			return act;
    		}
    	}
    	return null;
    }

    public ArrayList<ECActivity> getEndingActivities(){
    	return endActivities;
    }
    
    public ECActivity getEndingActivity(String name) {
		if (endActivities != null) {
			for (ECActivity act: endActivities) {
				if (act.getName().equals(name)){
					return act;
    			}
			}
			return null;
		} else {
			return null;
		}
    }
    
    public void addNewEndingActivity(ECActivity act) {
    	if (endActivities == null) {
    		endActivities = new ArrayList<ECActivity>();
    	}
    	endActivities.add(act);
    }
    
    public boolean hasCausingEvent() {
    	return causalLink != null;
    }
    
    public CausalLink getCausalLink() {
    	return causalLink;
    }
    
    public void setTime(long time, long timeInMicroSecs) {
        this.time = time;
        this.timeInMicroseconds = timeInMicroSecs;
    }

    public void setTime(long time) {
        this.time = time;
        this.timeInMicroseconds = time*1000;
    }
    
    public long getTime() {
        return time;
    }

    public long getTimeInMicroSec() {
        return timeInMicroseconds;
    }
    
    public ECActivity[] getActivitiesToBegin() {
        return emptyList;
    }

    public boolean isActivityEnd() {
        return false;
    }
    
    
}
