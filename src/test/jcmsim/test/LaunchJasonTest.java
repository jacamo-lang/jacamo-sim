package test;

import jcmsim.*;

public class LaunchJasonTest {
    
    public static void main (String args[]) throws Exception {
    	
    	Simulation sim = new Simulation();
    	sim.setProperty("mode", "real-time");
    	
    	SimulationController contr = SimulationController.getSimulationController();
    	contr.init(sim)
    	;
        jason.infra.centralised.RunCentralisedMAS.main(new String[]{ "src/test/jcmsim/test-mix.mas2j" });
    }

}
