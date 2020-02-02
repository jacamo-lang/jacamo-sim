package jcmsim;

import java.util.Optional;

public class PendingECEvent {

    private ECEvent ev;
    private boolean scheduled;
    
    public PendingECEvent(ECEvent ev) {
        this.ev = ev;
        scheduled = false;
    }

    public ECEvent getEvent() {
        return ev;
    }
    
    public synchronized void waitForSched() throws InterruptedException {
        while (!scheduled) {
            wait();
        }
    }

    public synchronized void signalExecution() {
        scheduled = true;
        notifyAll();
    }
    
}
