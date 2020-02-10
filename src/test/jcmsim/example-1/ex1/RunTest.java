package ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jcmsim.*;

public class RunTest {

	public static void main(String args[]) throws Exception {

		Simulation sim = new Simulation();

		Random gen = new Random(1);

		// reasoning cycle (RC) duration

		sim.setActivityDuration("ActAgRC", (ECActivity act, ExecContext ctx) -> {
			return 1000000; // + gen.nextInt(500000);
		});

		// inter-RC duration
		
		sim.setActivityDuration("ActAgBetweenRC", (ECActivity act, ExecContext ctx) -> { 
			 return 500000; 
		});
		
		// operation execution duration

		sim.setActivityDuration("ActArtOpExec", (ECActivity act, ExecContext ctx) -> {
			if (ctx.getId().equals("my_counter")) {
				return 2000000 + gen.nextInt(1000000);
			} else {
				return 1000000;
			}
		});

		
		sim.registerTimeAssignmentHandler((ECEvent ev, ExecContext ctx) -> {
			long currentTime = ctx.getCurrentTimeInMicroSec();

			/* 
			 * we want to assign time to EvAgExtActResult as:
			 * 
			 * EvAgExtActResult = EvAgExtActRequest + ActArtOpExec
			 *  
			 */			
			if (ev.getName().equals("EvAgExtActResult")) {

				/* find EvAgExtActRequest */
				
				ECEvent evActReq = ev.getEventInTheCausalChain("EvAgExtActRequest");

				/* find ActArtOpExec */
								
				ECActivity act = ev.getActivityInTheCausalChain("ActArtOpExec");

				if (evActReq != null && act != null) {
					
					long dur = act.getDurationInMicroSec();
					long startedTime = evActReq.getTimeInMicroSec();
					return startedTime + dur;
					
				} else {
					
					/* not found */
					return currentTime;
				}
			} else {

				/* not found */
				return currentTime;
			}

		});


		ExecutionController contr = ExecutionController.getExecController();
		contr.initSimulationMode(sim);

		jason.infra.centralised.RunCentralisedMAS.main(new String[] { "src/test/jcmsim/example-1/main.mas2j" });
	}

}
