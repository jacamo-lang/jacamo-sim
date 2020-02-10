package jcmsim;

import java.awt.event.*;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ExecControllerView extends JFrame  {      
    
    private EventFrame eventsFrame;
    private ReportFrame reportFrame;
    
    public ExecControllerView(){
        eventsFrame = new EventFrame();
        eventsFrame.setVisible(true);
        
        reportFrame = new ReportFrame();
        reportFrame.setVisible(true);
    }
    
    public void updateEvents(String s){
        eventsFrame.update(s);
    }

    public void updateReport(String s){
        reportFrame.update(s);
    }
    
    class EventFrame extends JFrame {

        private JTextArea text;
        private boolean updateBlocked;

        public EventFrame() {
            setTitle("Simulation Viewer | Events and Activities");
            setSize(1000,600);
            
            updateBlocked = false;
            
            JPanel panel = new JPanel();
            setContentPane(panel);
            
            text = new JTextArea(30,80);
            JScrollPane scrollPane = new JScrollPane(text); 

            JCheckBox cb = new JCheckBox("Don't refresh");  
            cb.addItemListener((ItemEvent e) -> {
                if (e.getStateChange() ==  java.awt.event.ItemEvent.SELECTED) {
                    updateBlocked = true;
                } else {
                    updateBlocked = false;
                }
            });
            panel.add(cb);
            
            text.setEditable(false);
            panel.add(scrollPane);        
        }
        
        public void update(String s){
            if (!updateBlocked) {
                SwingUtilities.invokeLater(() -> {
                    text.setText(s);
                });
            }
        }
        
    }
    
    class ReportFrame extends JFrame {

        private JTextArea text;
        private boolean updateBlocked;

        public ReportFrame() {
            setTitle("Simulation Viewer | Report");
            setSize(600, 600);
            
            JPanel panel = new JPanel();
            setContentPane(panel);
            
            text = new JTextArea(30,40);
            JScrollPane scrollPane = new JScrollPane(text); 

            text.setEditable(false);
            panel.add(scrollPane);        
        }
        
        public void update(String s){
            if (!updateBlocked) {
                SwingUtilities.invokeLater(() -> {
                    text.setText(s);
                });
            }
        }
        
    }    
}

