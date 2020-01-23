package homework4;

import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

import javax.swing.Timer;

/**
 * A ColorGenerator is constructed of a singleton design pattern that is being observed by PaintOrder. 
 * whenever the colorGenerator changes its color it notifies observers which calls the update functions of its observers. 
 * As a result the observers update the panels color based on the current strategy.
 */

public class ColorGenerator extends Observable{
	// Abs. Function:
	// Represents the generation of new random colors every 2000 time units and notifyng the paintOrder in order
	// to paint panels.
	// Rep. Invariant:
	
	private static ColorGenerator singleColGen = new ColorGenerator();
	
	Timer t;
	private PaintOrder myStrategy;
	
	/**
	 * @return instance of ColorGenerator this.singleColGen
	 *          
	 */
	public static ColorGenerator getInstance() {
		return singleColGen;
	}
	
	/**
	 * @effects initalizes myStrategy with newStrategy recieved
	 *          
	 */
	public void initialStra(PaintOrder newStrategy){
		myStrategy=newStrategy;
	}
	
	/**
	 * @return current strategy used
	 *          
	 */
	public PaintOrder getStra(){
		return myStrategy;
	}
	
	/**
	 * @param  newStrategy- update myStrategy to work according to newStrategy
	 *         panelObservers- panels list in order to update their color         
	 * @effects updates coloring order(strategy) by which the panels should update their colors,
	 * rands new color by which to color the panels, and notifies observers after change is made, to call coloring order 
	 * update methods 
	 *          
	 */
	public void UpdateStrategy (PaintOrder newStrategy,ArrayList<Panel> panelObservers){

		if(t!=null) {
			t.stop();
		}
        t = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
	            Color newCol =randColor();
	            myStrategy.stopRun();
	            myStrategy=newStrategy;
	            Iterator<Panel> it = panelObservers.iterator();
	            while(it.hasNext()){
	            	((Panel) it.next()).setColor(newCol);       
	            }
	            setChanged();
	            
	            //newStrategy.stopRun();
	            singleColGen.addObserver(newStrategy);
	            notifyObservers(panelObservers);
	            singleColGen.deleteObserver(newStrategy);
            

            }
        });
        t.start();
	}
	
	/**     
	 * @effects randomizes new color 
	 * @return new randomized color
	 *          
	 */
	public Color randColor(){
		Random rand = new Random();
		// Java 'Color' class takes 3 floats, from 0 to 1.
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		return (new Color(r, g, b));
	
	}
	
		/**     
	 * @effects stops updating panels by myStrategy logic 
	 *          
	 */
	public void stopRun(){
		myStrategy.stopRun();
	}

	

}
