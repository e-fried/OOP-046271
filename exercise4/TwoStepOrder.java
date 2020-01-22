package homework4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.*;

/**
* This is an inherited class implementing PaintOrder,
* this class represents a continous coloring order by skips, in which the top left panel is painted first, 
* than its adjacent panel to the right is skipped and the following one is painted, and so on.
*/
public class TwoStepOrder implements PaintOrder {
	// Abs. Function:
	// Represents the order in which the panels in the screen change color at every time unit. 
	// Rep. Invariant:
	// 
	private Timer t;
	
	/**
	 * @effects updates color of the various panels by calling paintPanel.
	 * the order in which these panels are updated is the order held in the array order.
	 * @requires panels !=null
	 *          
	 */

	@Override
	public void update(Observable arg0, Object panels ) {
		int[] order = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23 };
		t = new javax.swing.Timer(40, new ActionListener() {
		int i = 0 ; 

		public void actionPerformed(ActionEvent e) {
			
          Panel tmpPanel=((ArrayList <Panel>)panels).get(order[i % 25]);
          tmpPanel.paintPanel();
          i++;

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
