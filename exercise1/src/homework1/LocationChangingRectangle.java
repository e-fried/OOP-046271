package homework1;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class LocationChangingRectangle extends LocationChangingShape {
	
	private Dimension rectDimension;
	
	
	LocationChangingRectangle(Point location, Color color,Dimension dimension) {
    	super(location,color);
    	this.rectDimension=dimension;
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
    	this.rectDimension=dimension;
		checkRep();

    }
    
    /**
     * @return the bounding rectangle of this.
     */
    public Rectangle getBounds() {
		checkRep();
    	Rectangle boundingRect=new Rectangle(this.getLocation(), this.rectDimension);
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
    	((Graphics2D) g).fillRect((int)getLocation().getX(),(int)getLocation().getY(),
    			(int)getBounds().getHeight(),(int)getBounds().getHeight());
    	
    	checkRep();
    }
    
    /**
     * @effects Creates and returns a copy of this.
     */
	@Override
    public Object clone() {
    	checkRep();
    	LocationChangingRectangle movingRectangleClone;
    	movingRectangleClone=(LocationChangingRectangle)super.clone();
    	movingRectangleClone.rectDimension=(Dimension)rectDimension.clone();
    	checkRep();
    	return movingRectangleClone;

    }
    
    
    /**
     * @effects Checks if values in Rectangle are valid
     */
    private void checkRep() {
    	assert (rectDimension.getWidth() >=0 ):
        "Error: Width is not a positive number";
    	assert (rectDimension.getHeight() >=0 ):
        "Error: Height is not a positive number";
    }




}
