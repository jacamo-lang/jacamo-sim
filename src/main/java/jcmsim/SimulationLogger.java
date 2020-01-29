package jcmsim;

import java.util.List;

import jcmsim.EvCtx.EvCtxType;

public class SimulationLogger extends Thread {

    private SimulationController contr;
    private SimulationView view;
    
    public SimulationLogger(SimulationController contr, SimulationView view) {
        this.contr = contr;
        this.view = view;
    }
    
    private String getTypeName(EvCtx.EvCtxType type) {
        if (type.equals(EvCtxType.AGENT)) {
            return "AGENT: ";
        } else if (type.equals(EvCtxType.ARTIFACT)) {
            return "ARTIFACT: ";
        } else {
            return "WORKSPACE: ";
        }
    }
    
    public void run() {
        try {
            BasicLoggerOnFile logger = new BasicLoggerOnFile("simu-log.txt");           
            while (true) {              
                long t0 = System.currentTimeMillis();
                try {
                    logger.reset();
                    StringBuffer sb = new StringBuffer();
                    
                    for (EvCtx ev: contr.getContexts()) {
                        sb.append("------------------------------------\n");
                        sb.append( ev.getType() + ": " + ev.getId() + "\n\n");
                        sb.append("Activities: \n");
                        List<EvCtxActivity> h1 = ev.getActivityHistory();
                        for (EvCtxActivity act: h1) {
                            if (!act.isPending()) {
                                String msg = "[" + act.getBeginEvent().getTime()+"] " + act + " duration: " + act.getDuration() + " ms";
                                sb.append(msg + "\n");
                            }
                        }
                        sb.append("\nEvents: \n");
                        List<EvCtxEvent> h2 = ev.getEventHistory();
                        for (EvCtxEvent e: h2) {
                            String msg = "[" + e.getTime()+"] " + e;
                            sb.append(msg + "\n");
                        }
                        sb.append("------------------------------------\n");
                    }
                    
                    String st = sb.toString();
                    logger.log(st);
                    view.update(st);
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }               
                long t1 = System.currentTimeMillis();
                Thread.sleep(2000 - (t1 - t0));             
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
