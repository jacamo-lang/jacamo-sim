package jcmsim;


import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import jcmsim.EvCtx.EvCtxType;


public class SimulationController {
    
    private static SimulationController contr;
    private SimuFrame frame;
    private Simulation sim;
    
    private ConcurrentHashMap<String, EvCtx> tcontexts;
    
    private SimulationLogger logger;
    private SimulationView view;
    
    public static SimulationController getSimulationController() {
        synchronized (SimulationController.class) {
            if (contr == null) {
                contr = new SimulationController();
            }
        }
        return contr;
    }

    public Collection<EvCtx> getContexts(){
        return tcontexts.values();
    }
    
    private SimulationController() {
        tcontexts = new ConcurrentHashMap<String, EvCtx> ();
        sim = new Simulation();
        view = new SimulationView();
        
        if (sim.isTrackingMode()) {                 
            logger = new SimulationLogger(this, view);
            logger.start();
      
        } else {
            frame = new SimuFrame();
            frame.setVisible(true);
        }
    }
    
    public void createNewTContext(String ctxId, EvCtxType type, long initialTime) {
        EvCtx ctx = new EvCtx(ctxId, type, 0, sim);
        tcontexts.put(ctxId, ctx);
    }

    public EvCtxEvent getLastEvent(String ctxId) {
        EvCtx ctx = tcontexts.get(ctxId);
        if (ctx != null ) {
            List<EvCtxEvent> h = ctx.getEventHistory();
            if (!h.isEmpty()) {
                return h.get(h.size() - 1);
            }
        }
        return null;        
    }
    
    public List<EvCtxEvent> getEventHistory(String ctxId) {
        EvCtx ctx = tcontexts.get(ctxId);
        if (ctx != null ) {
            return ctx.getEventHistory();
        }
        return null;        
    }

    public void notifyNewEvent(String ctxId, EvCtxEvent ev) {
        EvCtx ctx = tcontexts.get(ctxId);
        if (ctx != null) {
            ctx.notifyNewEvent(ev);
        
            if (!sim.isTrackingMode()) {
                frame.next(ev.toString());  
            }
        }
    }
    

}
