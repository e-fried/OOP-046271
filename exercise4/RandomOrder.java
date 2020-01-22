package homework4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import javax.swing.*;

public class RandomOrder implements PaintOrder {

	private Timer t;

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
	
  public void stopRun() {
		if (t != null)
			t.stop();
	}


}
