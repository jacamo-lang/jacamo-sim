package jcmsim;

import java.awt.event.*;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class SimulationView extends JFrame  {      
    
    private SimuViewerFrame frame;
    
    public SimulationView(){
        frame = new SimuViewerFrame();
        frame.setVisible(true);
    }
    
    public void update(String s){
        frame.update(s);
    }
    
    class SimuViewerFrame extends JFrame {

        private JTextArea text;
        private boolean updateBlocked;

        public SimuViewerFrame() {
            setTitle("Simulation Viewer");
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
}

