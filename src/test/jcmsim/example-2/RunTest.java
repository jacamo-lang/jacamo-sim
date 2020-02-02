

import jcmsim.*;

public class RunTest {
    
    public static void main (String args[]) throws Exception {
        
        Simulation sim = new Simulation();
        sim.setProperty("mode", "real-time");
        // sim.setProperty("mode", "simulated-time");
        
        SimulationController contr = SimulationController.getSimulationController();
        contr.init(sim);
        jason.infra.centralised.RunCentralisedMAS.main(new String[]{ "src/test/jcmsim/example-1/main.mas2j" });
    }

}
