package jcmsim;

import java.util.HashMap;

public class Simulation {

    private boolean isRealTimeMode;
    private HashMap<String,Object> properties;
    
    public Simulation() {
        this.isRealTimeMode = true;
        properties = new HashMap<String,Object> ();
    }
    
    public void setProperty(String propName, Object value) throws UnknownPropException, UnknownPropValueException {
    	properties.put(propName, value);
    	
    	if (propName.equals("mode")) {
    		if (value.equals("real-time")) {
    			isRealTimeMode = true;
    		} else if (value.equals("simulated-time")) {
    			isRealTimeMode = false;
    		} else throw new UnknownPropValueException();
    	}  
    }
    
    public boolean isRealTimeMode() {
        return isRealTimeMode;  
    }
    
    public long getDuration(ECActivity act) {
        return 1;
    }
}
