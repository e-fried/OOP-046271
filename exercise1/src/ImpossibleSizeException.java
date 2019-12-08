package hw1;

import java.awt.Dimension;

/**
 * ImpossibleSizeException is an Exception caused by calling setSize with an invalid size.
 * the exception provides an alternative size to set to, using getAlternativeDimension method
 */
public class ImpossibleSizeException extends Exception{
	private final int ALTERNATIVE_HEIGHT=5;
	private final int ALTERNATIVE_WIDTH=5;
	private final Dimension alternativeDimension = new Dimension(ALTERNATIVE_HEIGHT,ALTERNATIVE_WIDTH);
	
	public ImpossibleSizeException() {
		super();
		
	}
	
	/**
    *
    * @effects Returns a valid alternative dimension to the setSize
    */
	public Dimension getAlternativeDimension() {
		return alternativeDimension;
	}
	
}
