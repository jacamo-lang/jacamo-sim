package jcmsim;

/* MAIN SIMULATION LOOP */

class SimulationLoop extends Thread {

	private ExecutionController cntr;
	
	public SimulationLoop(ExecutionController cntr) {
		this.cntr = cntr;
	}
    
	public void run() {     
        while (true) {
            try {

            	cntr.waitForAllControlFlowsBlocked();
                
                /* for each execution context select and exec an event from FES and update simulated time */
                
                for (ExecContext ec: cntr.getExecContexts()) {
                    
                    PendingECEvent pev = ec.selectEventAndAdvanceTime();
                
                    /* exec selected event */
                    
                    if (pev != null) {
                        pev.signalExecution();
                    }
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
