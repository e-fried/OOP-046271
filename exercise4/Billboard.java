package homework4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.*;
/**
 * Main application class for exercise #1.
 * This application allows the user to add shapes to a graphical window and
 * to animate them.
 */
@SuppressWarnings("serial")

public class Billboard extends JFrame implements ActionListener  {
	// preferred frame width and height.
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 400;
	private static final int ROWS_NUM = 5;
	private static final int COLLUMNS_NUM = 5;
	// graphical components
	private JMenuBar menuBar;
	private JMenu fileMenu, insertMenu, helpMenu;
	private JMenuItem columnItem, exitItem,stepItem, twoSItem, randItem, aboutItem;
	private JPanel mainPanel;
	//
	private ColorGenerator myGen;

	// shapes that have been added to this
	
	
	private ArrayList<Panel> boards = new ArrayList<Panel>();

	/**
	 * @modifies this
	 * @effects Initializes the GUI and enables a timer that steps animation
	 * 			of all shapes in this 25 times per second while animation
	 * 			checkbox is selected.
	 */
	public Billboard() {
		super("Billboard");
		
		// create main panel
		menuBar = (JMenuBar)createMenuBar();
        setJMenuBar(menuBar);
        super.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        //creating single generator
        myGen = ColorGenerator.getInstance();
        //create boards
        
		for (int i = 0; i < ROWS_NUM*COLLUMNS_NUM; i++) {
				boards.add(new Panel());
				super.add(boards.get(i).getMyJPanel());
		}
		
	
		super.setBackground(Color.WHITE);
		super.setLayout(new GridLayout(ROWS_NUM, COLLUMNS_NUM));
		super.setVisible(true);
	
	}


	/**
	 * @return main GUI menubar.
	 */
	private JMenuBar createMenuBar() {
    	JMenuBar menuBar = new JMenuBar();


    	insertMenu = new JMenu("Change order");
    	stepItem = new JMenuItem("Step Order");
    	stepItem.addActionListener(this);
    	insertMenu.add(stepItem);
    	columnItem = new JMenuItem("Column order");
    	columnItem.addActionListener(this);
    	insertMenu.add(columnItem);
    	twoSItem = new JMenuItem("Two step order");
    	twoSItem.addActionListener(this);
    	insertMenu.add(twoSItem);
    	randItem = new JMenuItem("Random order");
    	randItem.addActionListener(this);
    	insertMenu.add(randItem);

    	menuBar.add(insertMenu);

    	helpMenu = new JMenu("Help");
    	aboutItem = new JMenuItem("About");
    	aboutItem.addActionListener(this);
    	helpMenu.add(aboutItem);
    	menuBar.add(helpMenu);

    	return menuBar;
	}



		


	/**
	 * @modifies this
	 * @effects Invoked when the user selects an action from the menubar
	 * 			and performs the appropriate operation.
	 */
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)(e.getSource());
		myGen.stopRun();
		if (source.equals(stepItem)){
			myGen.UpdateStrategy(new StepOrder(),boards);
            }
        else if (source.equals(columnItem)) {
            myGen.UpdateStrategy(new ColumnOrder(),boards);
         	}
        else if (source.equals(twoSItem)) {
            myGen.UpdateStrategy(new TwoStepOrder(),boards);
            }
        else if (source.equals(randItem)) {
            myGen.UpdateStrategy(new RandomOrder(),boards);
            }
         // File->Exit: close application
        else if(source.equals(exitItem)) {         
          
                dispose();
         }
       
		// Help->About : show about message dialog
		else if (source.equals(aboutItem)){
			JOptionPane.showMessageDialog(
					this,
					"Billboard - 4st" +
					" homework assignment",
					"About",
					JOptionPane.INFORMATION_MESSAGE);
		}
    }


	/**
	 * @effects Billboard application.
	 * @param - args for main not in use
	 */
	public static void main(String[] args) {
        Billboard application = new Billboard();
        application.myGen.initialStra(new TwoStepOrder());
        application.myGen.UpdateStrategy(application.myGen.getStra(), application.boards);
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
	}

}
