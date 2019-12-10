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
public abstract class LocationChangingShape extends Shape implements Animatable {

	// Abs. Function:
	// Represents the change in location by (vx, vy) units at every step time unit 
	// vx is the velocity on the X axis, and vy represents the velocity on the Y axis
	// Rep. Invariant:
	// vx and vy are integer numbers
	private int vx ;
	private int vy ;
	
	/**
	 * @effects Initializes this with a a given location and color. Each
	 *          of the horizontal and vertical velocities of the new
	 *          object is set to a random integral value i such that
	 *          -5 <= i <= 5 and i != 0
	 */
	LocationChangingShape(Point location, Color color) {
		super(location,color);
		Random rand = new Random();
		int positiveRandX = rand.nextInt(4) +1;
		int positiveRandY = rand.nextInt(4) +1;
		int signRandX = rand.nextInt(1);
		int signRandY = rand.nextInt(1);
		if (signRandX==0){
			vx = positiveRandX*-1;
		}
		else {
			vx = positiveRandX;	
		}
		
		if (signRandY==0){
			vy = positiveRandY*-1;
		}
		else {
			vy = positiveRandY;
		}
			
    }


    /**
     * @return the horizontal velocity of this.
     */
    public int getVelocityX() {
    	return vx;
    	
    }


    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
    	return vy;
    	
    }


    /**
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     * 			vertical velocity of this to velocityY.
     */
    public void setVelocity(int velocityX, int velocityY) {
    	// TODO: Implement this method
    	this.vx=velocityX;
    	this.vy=velocityY;
    	
    }


    /**
     * @modifies this
     * @effects Let p = location
     * 				v = (vx, vy) = velocity
     * 				r = the bounding rectangle of this
     *         	If (part of r is outside bound) or (r is within bound but
     *          adding v to p would bring part of r outside bound) {
     * 				If adding v to p would move r horizontally farther away
     * 				from the center of bound,
     * 					vx = -vx
     * 				If adding v to p would move r vertically farther away
     * 				from the center of bound,
     * 					vy = -vy
     *          }
     * 			p = p + v
     */
    public void step(Rectangle bound) {
    	assert bound != null ;
    	Rectangle recBound = new Rectangle(getBounds());
    	Point outLocation = new Point((int)recBound.getX(),(int)recBound.getY());
    	if (HorizontalCheck(bound,recBound) == false){
    		vx=-vx;
    	}
    	if (VerticalCheck(bound,recBound) == false){
    		vy=-vy;
    	}
    	outLocation.translate(vx, vy);
    	setLocation(outLocation);
    }
    /**
     * @effects Checks if recBound (before or after the step) is inside x bound and return true if so and false  
     *  otherwise
     */
    private boolean HorizontalCheck(Rectangle bound, Rectangle recBound){
    	boolean stepCheck,baseCheck; 
        baseCheck =  (recBound.getMaxX() <= bound.getMaxX()) && (bound.getMinX() <= recBound.getMinX());
    	stepCheck =  ((recBound.getMaxX()+vx) <= bound.getMaxX()) && ((bound.getMinX()+vx) <= recBound.getMinX());
    	return(stepCheck||baseCheck);
    }
    /**
     * @effects Checks if recBound (before or after the step) is inside y bound and return true if so and false  
     *  otherwise
     */
    private boolean VerticalCheck(Rectangle bound, Rectangle recBound){
    	boolean stepCheck,baseCheck; 
        baseCheck =  (recBound.getMaxY() <= bound.getMaxY()) && (bound.getMinY() <= recBound.getMinY());
    	stepCheck =  ((recBound.getMaxY())+vy) <= bound.getMaxY() && ((bound.getMinY()+vy) <= recBound.getMinY());
    	return(stepCheck||baseCheck);
    	
    }
    /**
     * @effects Creates and returns a copy of this.
     */
	@Override
    public Object clone() {
    	checkRep();
    	LocationChangingShape locationChangeClone;
    	locationChangeClone=(LocationChangingShape)super.clone();
    	locationChangeClone.vx=this.vx;
    	locationChangeClone.vy=this.vy;
    	checkRep();
    	return locationChangeClone;

    }
    /**
     * @effects Checks if values in shape are valid
     */
    private void checkRep() {

    }
}

