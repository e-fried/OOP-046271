package homework4;

import java.awt.Color;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
* This is a Panel class that uses JPanel to hold the charactereistics of this module, 
* and uses color in order to hold the current color we want the panels to be colored.
*/

public class Panel  {
	// Abs. Function:
	// Represents a panel that will be displayed on screen. the characteristics of the panel will be held by myPanel. 
	// the current color of the panel will be held by col
	// Rep. Invariant:
	// col !=null && myPanel !=null 

	private JPanel myPanel;
	private Color col;

	/**
	 * @effects Initializes this with Jpanel, initalizes color to black
	 *          
	 */
	
	public Panel() {
		myPanel = new JPanel();
		col=new Color (0,0,0);
		checkRep();
	}
	
	/**
	 * @return myPanel
	 *          
	 */
	public JPanel getMyJPanel (){
		checkRep();
		return myPanel;
	}
	/**
	 * @effects sets this.col to color
	 *          
	 */
	public void setColor(Color color){
		checkRep();
		this.col =color;
		checkRep();
	}
	/**
	 * @effects paints myPanel with color currently in this.col 
	 *          
	 */
	public void paintPanel() {
		checkRep();
		myPanel.paintImmediately(myPanel.getVisibleRect());
		myPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		myPanel.setBackground(col);
		checkRep();

	}
    /**
     * @effects Checks if values in Panel are valid
     */
	private void checkRep(){
		assert (myPanel != null):
            "Error: myPanel is null pointer";
		assert (col != null):
	    "Error: color is null pointer";

	}

}
