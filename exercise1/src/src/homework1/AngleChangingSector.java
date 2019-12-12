package homework1;

import java.awt.*;
import java.util.Random;


/**
 * An Angle changing sector is a sector of an oval that can change its startAngle by STEP_ANGLE using its step()
 * method at every time unit. A LocationChaningShape has a arcAngle that defines how big the sector will be in degree units with the 
 * parameter arcAngle, so when step is active, the angle will pivot on its axis 
 * Thus, a typical AngleChangingSector consists of the following set of
 * properties: {location, color, shape, size, startAngle, arcAngle}
 */
public class AngleChangingSector extends Shape implements Animatable {

	// Abs. Function:
	// Represents the change in angle of the sector in degree units at every step time unit 
	// startAngle is the degree in which the sector begins and arcAngle is the size in degrees of the sector 
	// Rep. Invariant:
	// arc angle is a number between [-360,360]
	// startAngle is any int value
	// dimension has positive values of height and width only

	private int arcAngle ;
	private int startAngle ;
	private static final int MAX_ARC_ANGLE =360;
	private static final int MIN_ARC_ANGLE =-360;
	private static final int STEP_ANGLE =1;
	private Dimension ovalDimension;

	
	/**
	 * @effects Initializes this with a a given location, color, dimension, arcAngle and startAngle. 
	 * @requires dimension.Height and dimension.Width are a positive number
	 *           arcAngle is a value between [MIN_ARC_ANGLE, MAX_ARC_ANGLE]
	 *           dimension in not a null pointer
	 *          
	 */
	AngleChangingSector(Point location, Color color, int startAngle, int arcAngle, Dimension dimension) {
		super(location,color);
        assert (dimension != null):
            "Error: dimnsion is null pointer";
		assert (arcAngle <MAX_ARC_ANGLE) && (arcAngle >MIN_ARC_ANGLE) :
            "Error: Illegal arcAngle Value";
    	assert (dimension.getWidth() >0 ):
            "Error: Width is not a positive number";
        assert (dimension.getHeight() >0 ):
            "Error: Height is not a positive number";
		checkRep();
		this.startAngle=startAngle;
		this.arcAngle=arcAngle;
		this.ovalDimension=(Dimension) dimension.clone();
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
     * 			If this cannot be resized to the specified dimension than
     * 			this is not modified, throws ImpossibleSizeException
     * 			(the exception suggests an alternative dimension that is
     * 			 supported by this).
     */
    public void setSize(Dimension dimension) throws ImpossibleSizeException{
    	if(dimension.height<=0 || dimension.width <=0) {
    		throw new ImpossibleSizeException();
    	}
		checkRep();
    	this.ovalDimension=(Dimension)dimension.clone();
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
    			(int)getBounds().getWidth(),(int)getBounds().getHeight(), this.startAngle, this.arcAngle);
    	
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
     * @effects Checks if values in angleChangingSector are valid
     */
    private void checkRep() {
        assert (ovalDimension != null):
            "Error: dimnsion is null pointer";
		assert (arcAngle <MAX_ARC_ANGLE) && (arcAngle >MIN_ARC_ANGLE) :
            "Error: Illegal arcAngle Value";
    	assert (ovalDimension.getWidth() >0 ):
            "Error: Width is not a positive number";
        assert (ovalDimension.getHeight() >0 ):
            "Error: Height is not a positive number";

    }
}

