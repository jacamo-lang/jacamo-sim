package jcmsim;

import java.util.Iterator;

/* MAIN SIMULATION LOOP */

class SimulationLoop extends Thread {

	private ExecutionController cntr;
	
	public SimulationLoop(ExecutionController cntr) {
		this.cntr = cntr;
	}
    
	public void run() {  
		
        log("started.");
		Iterator it = cntr.getContextInterator();
		boolean block = true;
        while (true) {
            try {

            	if (block) {
            		// log("waiting all blocked...");
            		cntr.waitForAllControlFlowsBlocked();
            	}
            	
                // log("all blocked => going to select events to exec");
                /* for each execution context select and exec an event from FES and update simulated time */
                
            	if (!it.hasNext()) {
            		it = cntr.getContextInterator();
            	}
            	
            	if (it.hasNext()) {
            		ExecContext ec = (ExecContext) it.next();
            		
                	PendingECEvent pev = ec.selectEventAndAdvanceTime();
                    if (pev != null) {
                        /* exec selected event */
                    	cntr.notifyTimeUpdated(ec);
                    	
                        // log("for " + ec.getId() + " => " + pev.getEvent().getName() + " - time " + ec.getCurrentTimeInMicroSec());
                        pev.signalExecution();
                        block = true;
                    } else {
                    	block = false;
                    }
                } else {
                	log("waiting exec contexts...");
                	sleep(100);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
 
    private void log(String msg) {
        System.out.println("[SIMULATION LOOP] " + msg);
    }
	
}
