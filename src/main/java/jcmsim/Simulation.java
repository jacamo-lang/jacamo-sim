package jcmsim;

import java.util.*;

public class Simulation {

	private HashMap<String,Object> properties;
    public enum Mode { RealTime, SimulatedTime};
    private Mode mode;

    private TimeAssignmentFunction timeHandler;
    
    public Simulation() {
        properties = new HashMap<String,Object> ();
        mode = Mode.RealTime;
    }

    public void registerTimeAssignmentHandler(TimeAssignmentFunction  fun) {
    	timeHandler = fun;
    }
    
    
    public void setActivityDuration(String propName, TimingFunction value) throws UnknownPropException, UnknownPropValueException  {
        properties.put(propName, value);
    }

    public void setActivityDuration(String propName, ActivityDurationFunction  value) throws UnknownPropException, UnknownPropValueException  {
        properties.put(propName, value);
    }     
    

    public void assignTime(ECEvent ev, ExecContext ctx) {
    	
    	boolean assigned = false;
    	List<ECActivity> acts = ev.getEndingActivities();
    	if (acts != null && acts.size() > 0) {
    		for (ECActivity act: acts) {
	        	Object fun = properties.get(act.getClass().getSimpleName());
	        	if (fun != null) {
	        		long dur = ((ActivityDurationFunction) fun).computeTime(act, ctx);
	        		long startTime = act.getBeginEvent().getTimeInMicroSec();
	        		long finalTime = startTime + dur;
	        		ev.setTime(finalTime/1000, finalTime);
	        		assigned = true;
	        		break;
	        	} 
    		}
    	} 
    	
    	if (!assigned) {
    		if (timeHandler != null) {
    			long t = timeHandler.computeTime(ev, ctx);
    			ev.setTime(t/1000, t);
    		} else {
    			ev.setTime(ctx.getCurrentTime(), ctx.getCurrentTimeInMicroSec());
    		}
    	}
    }

}
