package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class LocationChangingOval extends LocationChangingShape {
	
	private Dimension ovalDimension;
	
	
	LocationChangingOval(Point location, Color color,Dimension dimension) {
    	super(location,color);
    	this.ovalDimension=dimension;
		checkRep();
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
    	if(dimension.height<=0 || dimension.width <=0) {
    		throw new ImpossibleSizeException();
    	}
		checkRep();
    	this.ovalDimension=dimension;
		checkRep();

    }
    
    /**
     * @return the bounding rectangle of this.
     */
    public Rectangle getBounds() {
		checkRep();
    	Rectangle boundingRect=new Rectangle(this.getLocation(), this.ovalDimension);
		checkRep();
    	return boundingRect.getBounds();
    }
  
    
    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public void draw(Graphics g) {
    	
    	checkRep();
    	
    	assert g != null;
    	
    	((Graphics2D) g).setColor(getColor());
    	((Graphics2D) g).fillOval((int)getLocation().getX(),(int)getLocation().getY(),
    			(int)getBounds().getHeight(),(int)getBounds().getHeight());
    	
    	checkRep();
    }
    
    /**
     * @effects Creates and returns a copy of this.
     */
    
    @Override
    public Object clone() {
    	checkRep();
    	LocationChangingOval movingOvalClone;
    	movingOvalClone=(LocationChangingOval)super.clone();
    	movingOvalClone.ovalDimension=(Dimension)ovalDimension.clone();
    	checkRep();
    	return movingOvalClone;

    }
    
    
    /**
     * @effects Checks if values in shape are valid
     */
    private void checkRep() {
    	assert (ovalDimension.getWidth() >=0 ):
        "Error: Width is not a positive number";
    	assert (ovalDimension.getHeight() >=0 ):
        "Error: Height is not a positive number";
    }




}