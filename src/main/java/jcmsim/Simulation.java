package jcmsim;

import java.util.HashMap;

public class Simulation {

	private HashMap<String,Object> properties;
    public enum Mode { RealTime, SimulatedTime};
    private Mode mode;
    
    public Simulation() {
        properties = new HashMap<String,Object> ();
        mode = Mode.RealTime;
    }
    
    public void setActivityDuration(String propName, DurationFunction value) throws UnknownPropException, UnknownPropValueException  {
        properties.put(propName, value);
    }
    
    public void setMode(Mode mode) {
    	this.mode = mode;
    }

    public Mode getMode() {
    	return mode;
    }
    
    public boolean isRealTimeMode() {
        return mode.equals(Mode.RealTime);  
    }
    
    public long getDurationInMicroSec(ECActivity act, ExecContext ctx) {
    	String actName = act.getClass().getSimpleName();
    	Object value = properties.get(actName);
    	if (value != null) {
    		if (value instanceof DurationFunction) {
    			return ((DurationFunction) value).computeDuration(ctx);
    		} 
    	} 
    	return 0;
    }
}
