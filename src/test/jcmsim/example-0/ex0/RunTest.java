package ex0;


import jcmsim.*;
import jcmsim.Simulation.Mode;
import jason.architecture.AgArch;

public class RunTest {
    
    public static void main (String args[]) throws Exception {
        
        Simulation sim = new Simulation();
        sim.setMode(Mode.RealTime);

        SimulationController contr = SimulationController.getSimulationController();
        contr.init(sim);
        jason.infra.centralised.RunCentralisedMAS.main(new String[]{ "src/test/jcmsim/example-0/main.mas2j" });
    }

}
