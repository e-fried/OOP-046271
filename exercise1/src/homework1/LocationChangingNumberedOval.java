package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class LocationChangingNumberedOval extends LocationChangingOval {
	
	private static int counter = 0;
	private int myNumber;
	
	LocationChangingNumberedOval(Point location, Color color,Dimension dimension) {
    	super(location,color,dimension);
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
    	
    	Point middleOfBox = new Point ((int)(this.getBounds().getMinX() + this.getBounds().getMaxX())/2 ,
    			(int)(this.getBounds().getMinY() + this.getBounds().getMaxY())/2);
    	
        if(getColor() == Color.BLACK) {
        	((Graphics2D) g).setColor(Color.WHITE);	
        } 
        else {
        	((Graphics2D) g).setColor(Color.BLACK);
        }
        
        
    	((Graphics2D) g).setColor(getColor());
    	((Graphics2D) g).fillOval((int)getLocation().getX(),(int)getLocation().getY(),
    			(int)getBounds().getHeight(),(int)getBounds().getHeight());
    	
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
    }




}
