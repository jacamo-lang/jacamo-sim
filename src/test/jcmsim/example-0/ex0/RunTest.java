package ex0;

import jcmsim.*;

public class RunTest {
    
    public static void main (String args[]) throws Exception {
        
		ExecutionController contr = ExecutionController.getExecController();
		contr.initTrackingMode();
		jason.infra.centralised.RunCentralisedMAS.main(new String[]{ "src/test/jcmsim/example-0/main.mas2j" });
    }

}
