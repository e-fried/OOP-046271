package homework1;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class LocationChangingRoundedRectangle extends LocationChangingRectangle {
	
	
	
	LocationChangingRoundedRectangle(Point location, Color color,Dimension dimension) {
    	super(location,color, dimension);
    }
	
    
    /**
     * @modifies g
     * @effects Draws this onto g.
     */
@Override
public void draw(Graphics g) {
    	
    	final int arcHeight=20;
    	final int arcWidth=20;
    	assert g != null;
    	
    	((Graphics2D) g).setColor(getColor());
    	((Graphics2D) g).fillRoundRect((int)getLocation().getX(),(int)getLocation().getY(),
    			(int)getBounds().getHeight(),(int)getBounds().getHeight(),arcWidth, arcHeight );
    	
    }
    



}
