package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * A LocationChangingNumberedOval is a LocationChangingOval thus, its size is defined by it, 
 * and step is defined by LocationchangingShape.
 *  A LocationChangingNumberedOval has a unique number that is given to it by the static counter
 * Thus, a typical LocationChangingNumberedOval consists of the following set of
 * properties: {location, color, shape, dimension, velocity, myNumber}
 */


public class LocationChangingNumberedOval extends LocationChangingOval {
	// Abs. Function:
	// Represents the change in location of the NumberedOval that was extended by LocationChangingOval 
	// The Numbered Oval has a unique number that was 
	// Rep. Invariant:
	// ovalDimension.Width and ovalDimension.Height are positive values
	// myNumber is a number >=0
	
	private static int counter = 0;
	private int myNumber;
	
	/**
	 * @effects Initializes this with a a given location, color, dimension, and myNumber
	 * myNumber is initialized to a unique number based on the counter value that increases for every instance of this
	 * @requires dimension.Height and dimension.Width are a positive number       
	 */
	LocationChangingNumberedOval(Point location, Color color,Dimension dimension) {
    	super(location,color,dimension);
    	checkRep();
    	myNumber = counter;
    	counter+=1;	
    	checkRep();
    }
	
    
    
    /**
     * @modifies g
     * @effects Draws this onto g.
     */
	@Override
    public void draw(Graphics g) {
    	
    	checkRep();
    	
    	assert g != null;
    	super.draw(g);
    	
    	Point middleOfBox = new Point ((int)((this.getBounds().getMinX() + this.getBounds().getMaxX())/2) ,
    			(int)(this.getBounds().getMinY() + this.getBounds().getMaxY())/2);
    	
        if(getColor() == Color.BLACK) {
        	((Graphics2D) g).setColor(Color.WHITE);	
        } 
        else {
        	((Graphics2D) g).setColor(Color.BLACK);
        }
        
    	((Graphics2D) g).drawString( Integer.toString(myNumber), (int)middleOfBox.getX(), (int)middleOfBox.getY());
    	
    	checkRep();
    }
    
    /**
     * @effects Creates and returns a copy of this.
     */
	@Override
    public Object clone() {
    	checkRep();
    	
    	LocationChangingNumberedOval numberMovingOvalClone;
    	numberMovingOvalClone = (LocationChangingNumberedOval)super.clone();
    	numberMovingOvalClone.myNumber = this.myNumber;
    	
    	checkRep();
    	
    	return numberMovingOvalClone;

    }
    public static void reset(){
	counter=0;   
    }    
    
    
    /**
     * @effects Checks if values in shape are valid
     */
    private void checkRep() {
    	assert (myNumber >=0 ):
    		"Error: Oval number is not a positive number";
    	assert (counter >=0 ):
    		"Error: counter is not a positive number";
    }




}
