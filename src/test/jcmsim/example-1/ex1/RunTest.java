package ex1;


import java.util.Random;

import jcmsim.*;
import jcmsim.Simulation.Mode;

public class RunTest {
    
    public static void main (String args[]) throws Exception {
        
    	Simulation sim = new Simulation();
        sim.setMode(Mode.SimulatedTime);

    	Random gen = new Random(1);

        /* reasoning cycle (RC) duration */
        
        sim.setActivityDuration("ActAgRC", (ExecContext ctx) -> {
        	return 1000000 + gen.nextInt(500000);
        });
        
        /* inter-RC duration */
        
        sim.setActivityDuration("ActAgBetweenRC", (ExecContext ctx) -> {
        	return 500000;
        });
        
        /* operation execution duration */
        
        sim.setActivityDuration("ActArtOpExec", (ExecContext ctx) -> {

        	if (ctx.getId().equals("my_counter")) {
        		return 2000000 + gen.nextInt(1000000);
        	} else {
        		return 1000000;
        	}
        });


        SimulationController contr = SimulationController.getSimulationController();
        contr.init(sim);
        jason.infra.centralised.RunCentralisedMAS.main(new String[]{ "src/test/jcmsim/example-1/main.mas2j" });
    }

}
