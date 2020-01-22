package homework4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.*;
/**
* This is an inherited class implementing PaintOrder,
* this class represents a coloring order by columns, in which first column of panles is first painted than second ans so forth. interface for strategy design pattern of different orders by which to paint the panels
*/
public class ColumnOrder implements PaintOrder {

	private Timer t;

	@Override
	public void update(Observable arg0, Object panels ) {
		int[] order = { 0, 5, 10, 15, 20, 1, 6, 11, 16, 21, 2, 7, 12, 17, 22, 3, 8, 13, 18, 23, 4, 9, 14, 19, 24 };
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
