package homework1;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;


/**
 * A LocationChangingRoundedRectangle is a LocationChangingRectangle thus, can change its location using its step()
 * method. and is defined by the LocationChaningRectangle properties.
 * this rectangle is rounded by arcHeight and arcWidth which are defined in the draw property
 * Thus, a typical LocationChangingRoundedRectangle consists of the following set of
 * properties: {location, color, shape, dimension, velocity, arcHeight, arcWidth}
 */

public class LocationChangingRoundedRectangle extends LocationChangingRectangle {
	
	// Abs. Function:
	// Represents the change in location of the roundedRectangle that was extended by LocationChangingShape 
	// units at every step 
	// the RoundedRectangle is represented by LocationChangingRectangle properties and 
	// draw method with (arcWidth,arcHeight) properties
	// Rep. Invariant:
	// rectDimension.Width and rectDimension.Height are positive values
	
	
	/**
	 * @effects Initializes this with a a given location, color and dimension.
	 * @requires dimension.Height and dimension.Width are a positive number       
	 */
	
	LocationChangingRoundedRectangle(Point location, Color color,Dimension dimension) {
    	super(location,color, dimension);
    }
	
    
    /**
     * @modifies g
     * @effects Draws this onto g.
     * defines rounded rectangle draw by [arcWidth, arcHeight] which defines how much the rectangle is rounded
     */
	@Override
	public void draw(Graphics g) {
    	
    	final int arcHeight=20;
    	final int arcWidth=20;
    	assert g != null;
    	
    	((Graphics2D) g).setColor(getColor());
    	((Graphics2D) g).fillRoundRect((int)getLocation().getX(),(int)getLocation().getY(),
    			(int)getBounds().getWidth(),(int)getBounds().getHeight(),arcWidth, arcHeight );
    	
    }
    



}
