package jcmsim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class SimuFrame extends JFrame implements ActionListener {      
    
    private JButton okButton;
    private JTextArea text;
    private Semaphore eventSem;
    
    public SimuFrame(){
        setTitle("Simulation GUI ");
        setSize(1000,600);
        
        JPanel panel = new JPanel();
        setContentPane(panel);
        
        okButton = new JButton("next");
        okButton.setSize(120,50);
        okButton.setEnabled(false);
        okButton.addActionListener(this);
        
        text = new JTextArea(30,100);
        JScrollPane scrollPane = new JScrollPane(text); 

        text.setEditable(false);
                    
        eventSem = new Semaphore(0);
        panel.add(scrollPane);
        panel.add(okButton);
        
    }
    
    public String getText(){
        return text.getText();
    }

    public void setText(String s){
        text.setText(s);
    }
    
    public void next(String msg) {
    
        SwingUtilities.invokeLater(() -> {
            // text.setText(msg);
            // okButton.setEnabled(true);
            text.append("\n----------------------------------------------------\n" + msg);
        });
        
        try {
            Thread.sleep(10);
            // eventSem.acquire();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        okButton.setEnabled(false);
        eventSem.release();
    }
}

