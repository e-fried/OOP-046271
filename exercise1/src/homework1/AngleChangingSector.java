package homework1;

import java.awt.*;
import java.util.Random;


/**
 * A LocationChaningShape is a Shape that can change its location using its step()
 * method. A LocationChaningShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public class AngleChangingSector extends Shape implements Animatable {

	// Abs. Function:
	// Represents the change in location by (vx, vy) units at every step time unit 
	// vx is the velocity on the X axis, and vy represents the velocity on the Y axis
	// Rep. Invariant:
	// vx and vy are integer numbers
	private int arcAngle ;
	private int startAngle ;
	private static final int MAX_ARC_ANGLE =360;
	private static final int MIN_ARC_ANGLE =-360;
	private static final int STEP_ANGLE =1;
	private Dimension ovalDimension;

	
	/**
	 * @effects Initializes this with a a given location and color. Each
	 *          of the horizontal and vertical velocities of the new
	 *          object is set to a random integral value i such that
	 *          -5 <= i <= 5 and i != 0
	 */
	AngleChangingSector(Point location, Color color, int startAngle, int arcAngle, Dimension dimension) {
		super(location,color);
		assert (arcAngle <MAX_ARC_ANGLE) && (arcAngle >MIN_ARC_ANGLE) :
            "Error: Illegal arcAngle Value";
    	assert (dimension.getWidth() >=0 ):
            "Error: Width is not a positive number";
        assert (dimension.getHeight() >=0 ):
            "Error: Height is not a positive number";
		checkRep();
		this.startAngle=startAngle;
		this.arcAngle=arcAngle;
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
     * @modifies this// TBD
     * @effects 
     */
    public void step(Rectangle bound) {
    	assert bound != null ;
    	checkRep();
    	startAngle=(startAngle+ STEP_ANGLE); 
    	checkRep();
    }
    
    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public void draw(Graphics g) {
    	
    	checkRep();
    	
    	assert g != null;
    	
    	((Graphics2D) g).setColor(getColor());
    	((Graphics2D) g).fillArc((int)getLocation().getX(),(int)getLocation().getY(),
    			(int)getBounds().getHeight(),(int)getBounds().getHeight(), this.startAngle, this.arcAngle);
    	
    	checkRep();
    }
    

    /**
     * @effects Creates and returns a copy of this.
     */
	@Override
    public Object clone() {
    	checkRep();
    	AngleChangingSector angleChangeClone;
    	angleChangeClone=(AngleChangingSector)super.clone();
    	angleChangeClone.arcAngle=this.arcAngle;
    	angleChangeClone.startAngle=this.startAngle;
    	angleChangeClone.ovalDimension=(Dimension)this.ovalDimension.clone();
    	checkRep();
    	return angleChangeClone;

    }
    /**
     * @effects Checks if values in shape are valid
     */
    private void checkRep() {
		assert (arcAngle <MAX_ARC_ANGLE) && (arcAngle >MIN_ARC_ANGLE) :
            "Error: Illegal arcAngle Value";
    	assert (ovalDimension.getWidth() >=0 ):
            "Error: Width is not a positive number";
        assert (ovalDimension.getHeight() >=0 ):
            "Error: Height is not a positive number";

    }
}

