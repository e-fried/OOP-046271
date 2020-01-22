package homework4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import javax.swing.*;

/**
* This is an inherited class implementing PaintOrder,
* this class represents a continous coloring order in which the top left panel is painted than the adjacent panel to the right
* until the completzion of the first row, than on to the second row and so forth.
*/

public class StepOrder implements PaintOrder {

	private Timer t;
	
	// Abs. Function:
	// Represents the order in which the panels in the screen change color at every time unit. 
	// Rep. Invariant:
	// 
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
	/**
	 * @effects stops update of the panel colors.
	 *          
	 */
  public void stopRun() {
		if (t != null)
			t.stop();
	}


}
