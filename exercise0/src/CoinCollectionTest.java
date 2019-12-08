
public class CoinCollectionTest {
	public static void main(String[] args){
		CoinCollection coinCollection = new CoinCollection();
        Coin coin1 = new Coin(0.5);
        Coin coin2 = new Coin(0.05);
        Coin coin3 = new Coin(1);
        Coin coin4 = new Coin(2);
        Coin coin5 = new Coin(10);
        Coin coin6 = new Coin(0.5);
        Coin coin7 = new Coin(5);


        
        coinCollection.addCoin(coin1);
        coinCollection.addCoin(coin1); //check that we can't add the same coin again
        coinCollection.addCoin(coin2);
        coinCollection.addCoin(coin3);
        coinCollection.addCoin(coin4);
        coinCollection.addCoin(coin5);
        coinCollection.addCoin(coin6);
        System.out.println("wallet total value: " + coinCollection.getCollectionTotal());
        System.out.println("wallet size: " + coinCollection.getCollectionSize());
        coinCollection.addCoin(coin7);
        System.out.println("wallet size: " + coinCollection.getCollectionSize());
        System.out.println("wallet total value: " + coinCollection.getCollectionTotal());
        coinCollection.emptyCollection();
        System.out.println("wallet total value: " + coinCollection.getCollectionTotal());
        System.out.println("wallet size: " + coinCollection.getCollectionSize());




    }



}



