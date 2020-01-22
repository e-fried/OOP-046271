package homework4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.*;

public class TwoStepOrder implements PaintOrder {

	private Timer t;

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
  public void stopRun() {
		if (t != null)
			t.stop();
	}


}
