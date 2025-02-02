

/**
	A Coin can have a value of 0.05, 0.5, 1, 5, 10
 */
public class Coin {
	private double value;
	
    /**
     * @requires value in {0.05, 0.5, 1, 5, 10}
     * @modifies this
     * @effects Creates and initializes new Coin with the value, value
     */
    public Coin(double value) {
    	if( value>0 ) {
    		this.value=value;
    	}
    	else {
    		System.out.println("Please enter one of the follwing values 0.05, 0.5, 1, 5, 10 \n");
    	}
    }
  

    /**
     * @return the value of the Coin
     */
    public double getValue() {
    	return value;
    }
}

