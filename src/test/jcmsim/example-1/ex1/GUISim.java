package ex1;

import javax.swing.*;
import java.awt.event.*;
import cartago.*;
import cartago.tools.*;

public class GUISim extends Artifact {

	public void init() {
		defineObsProperty("value",0);
	}

	int updateValue(){
		this.beginExtSession();
		ObsProperty prop = getObsProperty("value");
		int newValue = prop.intValue() + 1;
		prop.updateValue(newValue);
		this.endExtSession();
		return newValue;
	}

	class Worker extends Thread {
		public void run() {
			while (true) {
				
			}
		}
	}
	
}
