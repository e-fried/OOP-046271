package homework4;

import java.awt.Color;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Panel  {
	private JPanel myPanel;
	private Color col;

	
	public Panel() {
		myPanel = new JPanel();
		col=new Color (0,0,0);
	
	}
	public JPanel getMyJPanel (){
		return myPanel;
	}
	public void setColor(Color color){
		this.col =color;
	}
	public void paintPanel() {
		
		myPanel.paintImmediately(myPanel.getVisibleRect());
		myPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		myPanel.setBackground(col);

	}

}
