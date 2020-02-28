package ex1;

import javax.swing.*;
import java.awt.event.*;
import cartago.*;
import cartago.tools.*;

public class GUI extends Artifact {

	private MyFrame frame;
	
	public void init() {
		frame = new MyFrame();
		defineObsProperty("value",0);
		frame.setVisible(true);		
	}

	int updateValue(){
		this.beginExtSession();
		ObsProperty prop = getObsProperty("value");
		int newValue = prop.intValue() + 1;
		prop.updateValue(newValue);
		this.endExtSession();
		return newValue;
	}
	
	class MyFrame extends JFrame {		
		
		private JButton incButton;
		private JTextField text;
		
		public MyFrame(){
			setTitle("Simple GUI ");
			setSize(200,100);
			
			JPanel panel = new JPanel();
			setContentPane(panel);
			
			incButton = new JButton("inc");
			incButton.setSize(80,50);

			incButton.addActionListener((ActionEvent ev) -> {
				int val = updateValue();
				text.setText(""+val);
			});
			
			text = new JTextField(10);
			text.setText("0");
			text.setEditable(false);
						
			panel.add(text);
			panel.add(incButton);
		}

	}
}
