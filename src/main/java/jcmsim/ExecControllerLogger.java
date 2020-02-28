package jcmsim;

import java.util.List;

import jcmsim.ExecContext.ECType;

public class ExecControllerLogger extends Thread {

    private ExecutionController contr;
    private ExecControllerView view;
    
    public ExecControllerLogger(ExecutionController contr, ExecControllerView view) {
        this.contr = contr;
        this.view = view;
    }
    
    private String getTypeName(ExecContext.ECType type) {
        if (type.equals(ECType.AGENT)) {
            return "AGENT: ";
        } else if (type.equals(ECType.ARTIFACT)) {
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
                    StringBuffer sbEvents = new StringBuffer();
                    StringBuffer sbReport = new StringBuffer();
                    
                    for (ExecContext ec: contr.getContexts()) {
                    	long te0 = 0;
                    	sbEvents.append("------------------------------------\n");
                        List<ECEvent> h2 = ec.getEventHistory();
                        if (h2.size() > 0) {
                        	te0 = h2.get(0).getTime();
                        }
                        sbEvents.append( ec.getType() + ": " + ec.getId() + " - start time: " + te0 + "\n\n");
                        sbEvents.append("\nEvents: \n");
                        for (ECEvent e: h2) {
                            String msg = "[" + (e.getTime() - te0)+"] " + e;
                            sbEvents.append(msg + "\n");
                        }
                        sbEvents.append("Activities: \n");
                        List<ECActivity> h1 = ec.getActivityHistory();
                        for (ECActivity act: h1) {
                            if (!act.isPending()) {
                                String msg = "[" + (act.getBeginEvent().getTime() - te0) +"] " + act + " duration: " + act.getDuration() + " ms ( " + act.getDurationInMicroSec() + " us )";
                                sbEvents.append(msg + "\n");
                            }
                        }
                        sbEvents.append("------------------------------------\n");
                        
                        sbReport.append( ec.getType() + ": " + ec.getId()  + " | time: " + ec.getCurrentTime() + "\n");
                    }
                    
                    String st = sbEvents.toString();
                    logger.log(st);
                    view.updateEvents(st);
                    view.updateReport(sbReport.toString());
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }               
                long sleepTime = 2000 - (System.currentTimeMillis() - t0);
                if (sleepTime > 0) {
                	Thread.sleep(sleepTime);             
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
