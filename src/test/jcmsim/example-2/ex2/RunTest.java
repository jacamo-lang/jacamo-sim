package ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jcmsim.*;


class CentralizedSimulation extends Simulation {
	
	private long time;
	private Random gen;
	
	public CentralizedSimulation() {
		time = 0;

		gen = new Random(1);

		try { 

			// reasoning cycle (RC) duration
			setActivityDuration("ActAgRC", (ECActivity act, ExecContext ctx) -> { 
				 return 1000; 
			});		
			
			setActivityDuration("ActCommMsgDispatch", (ECActivity act, ExecContext ctx) -> {
				return 20000; // + gen.nextInt(500000);
			});
	
			setActivityDuration("ActAgRCFetchPercept", (ECActivity act, ExecContext ctx) -> {
				return 0; // + gen.nextInt(500000);
			});
			// inter-RC duration
			
			/*
			setActivityDuration("ActAgBetweenRC", (ECActivity act, ExecContext ctx) -> { 
				 return 500000; 
			});		
			
			setActivityDuration("ActArtOpExec", (ECActivity act, ExecContext ctx) -> { 
				 return 5000000; 
			});
			*/		
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}
	
    public void assignTime(ECEvent ev, ExecContext ctx) {

    	long time = ctx.getCurrentTimeInMicroSec();

    	if (ev.getName().equals("EvAgExtActResult")) {

			/* find EvAgExtActRequest */
			
			ECEvent evActReq = ev.getEventInTheCausalChain("EvAgExtActRequest");

			/* find ActArtOpExec */
							
			ECActivity act = ev.getActivityInTheCausalChain("ActArtOpExec");

			if (evActReq != null && act != null) {
				
				long dur = act.getDurationInMicroSec();
				long startedTime = evActReq.getTimeInMicroSec();
				long evt = startedTime + dur;
				ev.setTime(evt/1000, evt); 
			}
		}

    	if (!ev.isTimeAssigned()) {
    		if (ev.hasCausingEvent()){
				long t = ev.getCausalLink().getCausingEvent().getTimeInMicroSec();			
				if (t > time) {
					ev.setTime(t/1000, t);
				} else {
					ev.setTime(time/1000, time);
				}
    		} else {
    			//  log("event without a time assigned: " + ev.getName() + " in " + ctx.getCurrentTime());
    			ev.setTime(time/1000, time);
    		}
		}
    }

    public void notifyTimeUpdated(ExecContext ctx) {
    	if (ctx.getCurrentTimeInMicroSec() > time) {
    		time = ctx.getCurrentTimeInMicroSec();
    	}
    }

    private void log(String msg) {
        System.out.println("[SIMULATION] " + msg);
    }
}

public class RunTest {

	public static void main(String args[]) throws Exception {

		Simulation sim = new CentralizedSimulation();

		ExecutionController contr = ExecutionController.getExecController();
		contr.initSimulationMode(sim);
		// contr.initTrackingMode();
		
		jason.infra.centralised.RunCentralisedMAS.main(new String[] { "src/test/jcmsim/example-2/main.mas2j" });
	}

}
