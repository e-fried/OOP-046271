import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
/**
 * A wallet can contain a number of coins. There could be several coins of the same value, 
 * but the same coin cannot appear in the wallet twice
 */
public class Wallet {
	private  double balance;
	private  int numOfCoins;
	private List <Coin> CoinList; 
    /**
     * @effects Creates a new empty wallet
     */
    public Wallet() {
    	this.CoinList = new ArrayList<Coin>();
    	this.balance=0;
    	this.numOfCoins=0;
    }


    /**
     * @modifies this
     * @effects Adds a coin to the wallet
     * @return true if the coin was successfully added to the wallet;
     * 		   false otherwise
     */
    public boolean addCoin(Coin coin) {
    	if(CoinList.contains(coin)) {
            System.out.println("Coin already exists in List");
    		return false;
    	}
    	double coinVal=coin.getValue();
    	if((coinVal ==0.05) || (coinVal ==0.5) || (coinVal ==1) || (coinVal ==5) || (coinVal ==10)) {
    		this.CoinList.add(coin);
    		this.numOfCoins+=1;
    		this.balance=(balance+= coinVal);
    		return true;
    	}
        System.out.println("Not a valid coin size");
    	return false;
    }
    
    
    /**
     * @modifies none
     * @effects none
     * @return positive value if b is greater than a, and negative value if a is greater than b
     */
    class SortLargestFirst implements Comparator<Coin> 
    { 
        public int compare(Coin a, Coin b) 
        { 
            return (int)(b.getValue()-a.getValue()); 
        } 
    }
    
    
    /**
	 * @requires sum > 0
     * @modifies this
     * @effects tries to match at least the sum "sum" with coins in the wallet. 
	 *			If transaction is possible, removes the paid coins from the wallet; else; changes nothing
     * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double pay(double sum) {
    	if (sum <= this.balance) {
    		double mySum=0;
    		double currentCoin;
    		Iterator < Coin > iter = CoinList.iterator();
        	while((iter.hasNext()) && (mySum <= sum)) {
        		currentCoin=iter.next().getValue();
        		mySum+=currentCoin;
        		iter.remove();
        		this.balance-=currentCoin;
        		this.numOfCoins--;
        	
        	}
        	return mySum;
    	}
    	return 0;
    }
    
    
    /**
 	 * @requires sum > 0
      * @modifies this
      * @effects tries to match at least the sum "sum" with minimum number of coins in the wallet. 
 	 *			If transaction is possible, removes the paid coins from the wallet; else; changes nothing
      * @return the amount actually paid, 0 if amount could not be obtained
      */
    public double payMinimum(double sum) {
    	if (sum <= this.balance) {
    		Collections.sort(CoinList, new SortLargestFirst()) ;
    		double mySum=0;
    		double currentCoin;
    		Iterator < Coin > iter = CoinList.iterator();
        	while((iter.hasNext()) && (mySum <= sum)) {
        		currentCoin=iter.next().getValue();
        		mySum+=currentCoin;
        		iter.remove();
        		this.balance-=currentCoin;
        		this.numOfCoins--;
        	
        	}
        	return mySum;
    	}
    	return 0;
    }
    
    
    /**
 	 * @requires sum > 0
      * @modifies this
      * @effects tries to match exactly the sum "sum" with maximum number of coins in the wallet. 
 	 *			If transaction is possible, removes the paid coins from the wallet; else; changes nothing
      * @return the amount actually paid, 0 if amount could not be obtained
      */
    
    public double payExactMaximum(double sum) {
    	if (sum <= this.balance) {
    		Collections.sort(CoinList, new SortLargestFirst()) ;
    		
    		 List <Coin> HistogramList=new ArrayList<Coin>();
    		 if (isSubsetSum(HistogramList, CoinList.size()-1, sum)) {
    			int i=0;
    			for(Coin coin:HistogramList) {
        			this.CoinList.remove(coin);
        			i++;
        		}
    			numOfCoins-=i;
    			balance-=sum;
    			return sum;
        	}
        	
    	}
    	return 0;
    }
    
    
    /**
   	 * @requires none
        * @modifies HistogramList
        * @effects finds subset of coins that match sum, and adds them to histogram list 
        * @return true if there exists a subset of coins matching exactly sum, false otherwise
        */
      
    private boolean isSubsetSum(List<Coin> HistogramList ,int n, double sum) { 
    	// Base Cases 
    	if (sum == 0) {
    		return true;
    	}
    	if ( (n == -1) && (sum != 0)) {
    		return false; 
    	}
    	if ((this.CoinList.get(n)).getValue() > sum) { 
    		HistogramList.remove(this.CoinList.get(n));
    		return isSubsetSum(HistogramList, n - 1, sum); 
    	}
		HistogramList.add(this.CoinList.get(n));
    	boolean sumWithCurrent=isSubsetSum(HistogramList, n - 1, sum - this.CoinList.get(n).getValue());
    	if(sumWithCurrent) {
    		return true;
    	}
		HistogramList.remove(this.CoinList.get(n));
    	boolean sumWithoutCurrent=isSubsetSum(HistogramList, n - 1, sum);
    	if(sumWithoutCurrent) {
    		return true;
    	}
    	return false; 
    } 

    
    /**
     * @return the current total value of coins in the wallet
     */
    public double getWalletTotal() {
    	return this.balance;
    	
    }


    /**
     * @return the number of coins in the wallet
     */
    public int getWalletSize() {
    	return this.numOfCoins;
    }


    /**
     * @modifies this
     * @effects Empties the the wallet. After this method is called,
	 * 			both getWalletSize() and getWalletTotal() will return 0
	 * 			if called
     */
    public void emptyWallet() {
    	CoinList.clear();
    	this.balance=0;
    	this.numOfCoins=0;
    }


    /**
     * @return true if this wallet contains a coin with value "value"
     *  	   false otherwise
     */
    public boolean containsCoin(double value) {
    	int LastIndex=CoinList.size();
    	for(int i=0;i<LastIndex;i++) {
    		if(CoinList.get(i).getValue()==value) {
    			return true;
    		}
    	}
    	return false;
    	
    	
    }
	
	
	/**
     * @return true if this wallet contains an amount of money with value "value"
     *  	   false otherwise
     */
    public boolean containsAmmount(double value) {
    	if (this.balance >= value) {
    		return true;
    	}
    	return false;
    }

	
	
}
