package homework1;

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
public class Animator extends JFrame implements ActionListener {

	// preferred frame width and height.
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 400;

	// graphical components
	private JMenuBar menuBar;
	private JMenu fileMenu, insertMenu, helpMenu;
	private JMenuItem newItem, exitItem,
						rectangleItem, roundedRectangleItem, ovalItem,
						numberedOvalItem, sectorItem, aboutItem;
	private JCheckBoxMenuItem animationCheckItem;
	private JPanel mainPanel;

	// shapes that have been added to this
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	

	/**
	 * @modifies this
	 * @effects Initializes the GUI and enables a timer that steps animation
	 * 			of all shapes in this 25 times per second while animation
	 * 			checkbox is selected.
	 */
	public Animator() {
		super("Animator");

		// create main panel and menubar
		mainPanel = (JPanel)createMainPanel();
		getContentPane().add(mainPanel);
		menuBar = (JMenuBar)createMenuBar();
        setJMenuBar(menuBar);

        // enable animation timer (ticks 25 times per second)
        Timer timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (animationCheckItem.isSelected()) {
                	Iterator<Shape> myShapesIt = shapes.iterator();
                	while(myShapesIt.hasNext()){
                		Animatable tmpAnim = (Animatable) myShapesIt.next();
                		tmpAnim.step(mainPanel.getBounds());
                	}
                	
                	

            		repaint();	// make sure that the shapes are redrawn
                }
            }
        });
        timer.start();
	}


	/**
	 * @return main GUI panel.
	 */
	private JComponent createMainPanel() {
    	JPanel mainPanel = new JPanel();
    	mainPanel.setPreferredSize(
    			new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    	mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
    	mainPanel.setBackground(Color.WHITE);

    	return mainPanel;
	}


	/**
	 * @return main GUI menubar.
	 */
	private JMenuBar createMenuBar() {
    	JMenuBar menuBar = new JMenuBar();

    	fileMenu = new JMenu("File");
    	newItem = new JMenuItem("New");
    	newItem.addActionListener(this);
    	fileMenu.add(newItem);
    	animationCheckItem = new JCheckBoxMenuItem("Animation");
    	fileMenu.add(animationCheckItem);
    	exitItem = new JMenuItem("Exit");
    	exitItem.addActionListener(this);
    	fileMenu.add(exitItem);
    	menuBar.add(fileMenu);

    	insertMenu = new JMenu("Insert");
    	rectangleItem = new JMenuItem("Rectangle");
    	rectangleItem.addActionListener(this);
    	insertMenu.add(rectangleItem);
    	roundedRectangleItem = new JMenuItem("Rounded Rectangle");
    	roundedRectangleItem.addActionListener(this);
    	insertMenu.add(roundedRectangleItem);
    	ovalItem = new JMenuItem("Oval");
    	ovalItem.addActionListener(this);
    	insertMenu.add(ovalItem);
    	numberedOvalItem = new JMenuItem("Numbered Oval");
    	numberedOvalItem.addActionListener(this);
    	insertMenu.add(numberedOvalItem);
    	sectorItem = new JMenuItem("Sector");
    	sectorItem.addActionListener(this);
    	insertMenu.add(sectorItem);
    	menuBar.add(insertMenu);

    	helpMenu = new JMenu("Help");
    	aboutItem = new JMenuItem("About");
    	aboutItem.addActionListener(this);
    	helpMenu.add(aboutItem);
    	menuBar.add(helpMenu);

    	return menuBar;
	}


	/**
	 * @modifies g
	 * @effects Paint this including all its shapes to g. This method is
	 * 			invoked by Swing to draw components. It should not be invoked
	 * 			directly, but the repaint method should be used instead in
	 * 			order to schedule the component for redrawing.
	 */
	public void paint(Graphics g) {
		super.paint(g);

    	Iterator<Shape> myShapesIt = shapes.iterator();
    	while(myShapesIt.hasNext()){
    		Shape tmpShape = (Shape) myShapesIt.next();
    		tmpShape.draw(getContentPane().getGraphics());
    	}

		
	}


	/**
	 * @modifies this
	 * @effects Invoked when the user selects an action from the menubar
	 * 			and performs the appropriate operation.
	 */
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)(e.getSource());

		// File->New : clear all shapes
		if (source.equals(newItem)) {
			shapes.clear();
			repaint();
			
			LocationChangingNumberedOval.reset();
		}

		// File->Exit: close application
		else if (source.equals(exitItem)) {
        	dispose();
        }

		// Insert a shape
		else if ((source.equals(rectangleItem)) ||
      		 	 (source.equals(roundedRectangleItem)) ||
      		 	 (source.equals(ovalItem)) ||
      		 	 (source.equals(numberedOvalItem)) ||
      		 	 (source.equals(sectorItem))) {


			Random rand = new Random() ; 
			int randWidth = (int) (rand.nextInt((int)(0.2*WINDOW_WIDTH))+0.1*WINDOW_WIDTH);
			int xLocation = (int)(rand.nextInt(WINDOW_WIDTH - randWidth));
			int randHeight = (int) (rand.nextInt((int)(0.2*WINDOW_HEIGHT))+0.1*WINDOW_HEIGHT);
			int yLocation = (int)(rand.nextInt(WINDOW_HEIGHT - randHeight));
			int startAngle = rand.nextInt(359);
			int arcAngle= rand.nextInt(358)+1;
			Dimension  dimension = new Dimension(randWidth,randHeight);
			Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
			Point location = new Point(xLocation,yLocation);
			
			if(source.equals(roundedRectangleItem)){
				shapes.add(new LocationChangingRoundedRectangle(location, color, dimension));
			}
			else if(source.equals(rectangleItem)){
				shapes.add(new LocationChangingRectangle(location, color, dimension)); 
			}
			else if(source.equals(numberedOvalItem)){
				shapes.add(new LocationChangingNumberedOval(location, color, dimension)); 
			}
			else if(source.equals(ovalItem)){
				shapes.add(new LocationChangingOval(location, color, dimension)); 
			}
			else {
				shapes.add(new AngleChangingSector( location, color, startAngle, arcAngle , dimension)); 
			}
			
			repaint();
		}

		// Help->About : show about message dialog
		else if (source.equals(aboutItem)){
			JOptionPane.showMessageDialog(
					this,
					"Animator - 1st" +
					" homework assignment",
					"About",
					JOptionPane.INFORMATION_MESSAGE);
		}
    }


	/**
	 * @effects Animator application.
	 */
	public static void main(String[] args) {
        Animator application = new Animator();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.pack();
        application.setVisible(true);
	}
}
