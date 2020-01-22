package homework4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import javax.swing.*;

public class StepOrder implements PaintOrder {

	private Timer t;

	@Override
	public void update(Observable arg0, Object panels) {
		t = new javax.swing.Timer(40, new ActionListener() {
			Iterator it= ((ArrayList <Panel>)panels).iterator();
			public void actionPerformed(ActionEvent e) {
	        if(it.hasNext()){
	        	((Panel)it.next()).paintPanel();
	        	
	        }
	        else{
	          it=((ArrayList <Panel>)panels).iterator();
	        }
	      }

		});
		t.start();
	}
  public void stopRun() {
		if (t != null)
			t.stop();
	}


}
