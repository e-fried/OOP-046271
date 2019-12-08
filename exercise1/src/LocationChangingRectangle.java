package hw1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class LocationChangingRectangle extends LocationChangingShape {
	
	private Dimension rectDimension;
	
	
	LocationChangingRectangle(Point location, Color color,Dimension dimension) {
    	super(location,color);
    	this.rectDimension=dimension;

    
    }
	
    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     * 			dimension.
     * 			If this cannot be resized to the specified dimension =>
     * 			this is not modified, throws ImpossibleSizeException
     * 			(the exception suggests an alternative dimension that is
     * 			 supported by this).
     */
    public void setSize(Dimension dimension) throws ImpossibleSizeException{
    	
    }
    
    /**
     * @return the bounding rectangle of this.
     */
    public Rectangle getBounds() {
    	
    	this.rect.getBounds();
    }
  
    
    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public void draw(Graphics g) {
    	
    }
    
    
    /**
     * @effects Checks if values in shape are valid
     */
    private void checkRep() {
        //are there any restriction on values?
    }




}
