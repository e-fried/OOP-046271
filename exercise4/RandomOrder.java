package homework4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import javax.swing.*;
/**
* This is an inherited class implementing PaintOrder,
* this class represents a random coloring order, in which every panel is randomly painted out of the remainding panels. 
* once all the panels are painted, the order in which they will be painted will be randomized again
*/

public class RandomOrder implements PaintOrder {
	// Abs. Function:
	// Represents the order in which the panels in the screen change color at every time unit. 
	// The order represented by this class is a randim order.
	// Rep. Invariant:
	// 

	private Timer t;

	/**
	 * @effects updates color of the various panels by calling paintPanel.
	 * The order in which these panels are updated is the order held in the array order after it is randomly shuffled.
	 * @requires panels !=null
	 *          
	 */
	@Override
	public void update(Observable arg0, Object panels) {
		int[] order = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23 };
		
		t = new javax.swing.Timer(40, new ActionListener() {
		int i = 0 ;
		Random rand = new Random();
		public void actionPerformed(ActionEvent e) {
        if(i==0){
        	for (int j = 0; j < order.length; j++) {
        		int randomPosition = rand.nextInt(order.length);
	        	int temp = order[j];
	        	order[j] = order[randomPosition];
	        	order[randomPosition] = temp;
        	}
        }

        Panel tmpPanel=((ArrayList <Panel>)panels).get(order[i % 25]);
        tmpPanel.paintPanel();
        i++;
        if(i==25){
        	i=0;
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
