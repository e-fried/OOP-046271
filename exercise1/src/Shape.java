

import java.awt.*;


/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of
 * a set of properties: {location, color, shape, size}.
 * Shapes are mutable and cloneable.
 */
public abstract class Shape implements Cloneable {

	private Point location;
	private Color color;


	// Abs. Function:
	// represents a Geometric shape bounded by a Rectangle, 
	// The top left corner of the Rectangle is at location, and the color of the shape is at Shape
	// Rep. Invariant:
	// location != null and is of type Point, color != null and is of type Color
	
	/**
	 * @effects Initializes this with a a given location and color.
	 */
    public Shape(Point location, Color color) {
    	checkRep();
    	setLocation(location);
    	setColor(color);
    	checkRep();
    }


    /**
     * @return the top left corner of the bounding rectangle of this.
     */
    public Point getLocation() {
    	checkRep();
    	return new Point(location);
    	
    	
    }


    /**
     * @modifies this
     * @effects Moves this to the given location, i.e. this.getLocation()
     * 			returns location after call has completed.
     */
    public void setLocation(Point location) {
    	checkRep();
    	this.location = (Point)location.clone();
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
    public abstract void setSize(Dimension dimension)
    	throws ImpossibleSizeException;

    
    /**
     * @return the bounding rectangle of this.
     */
    public abstract Rectangle getBounds();
  

    /**
     * @return true if the given point lies inside the bounding rectangle of
     * 		   this and false otherwise.
     */
    public boolean contains(Point point) {
    	checkRep();
    	return getBounds().contains(point);
    }
        

    /**
     * @return color of this.
     */
    public Color getColor() {
    	checkRep();
    	return color;
    	checkRep();
    }


    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
    	checkRep();
    	this.color = color;
    	checkRep();
    }


    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public abstract void draw(Graphics g);


    /**
     * @effects Creates and returns a copy of this.
     */
    public Object clone() {
    	checkRep();
    	Shape shapeClone;
    	shapeClone=(Shape)super.clone();
    	shapeClone.location=(Point)location.clone();
    	shapeClone.color=color;
    	checkRep();
    	return shapeClone;

    }
    
    /**
     * @effects Checks if values in shape are valid
     */
    private void checkRep() {
        assert color != null:
            "Error: color is null pointer";
        assert location != null:
            "Error: location is null pointer";
    }
}