package jcmsim;

public class Simulation {

	private boolean isTracking;
	
	public Simulation() {
		isTracking = true;
	}
	
	public boolean isTrackingMode() {
		return isTracking;	
	}
	
	public long getDuration(EvCtxActivity act) {
		return 1;
	}
}
