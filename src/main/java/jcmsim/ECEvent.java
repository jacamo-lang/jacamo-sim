package jcmsim;

public abstract class ECEvent {

    private long time;
    private long timeInMicroseconds;
    
    private static final ECActivity[] emptyList = new ECActivity[0];
    
    public ECEvent() {
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
