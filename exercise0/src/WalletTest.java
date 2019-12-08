
public class WalletTest {
	public static void main(String[] args){
		Wallet wallet = new Wallet();
        Coin coin1 = new Coin(0.5);
        Coin coin2 = new Coin(0.05);
        Coin coin3 = new Coin(1);
        Coin coin4 = new Coin(5);
        Coin coin5 = new Coin(10);
        Coin coin6 = new Coin(0.5);


        
        wallet.addCoin(coin1);
        wallet.addCoin(coin1); //check that we can't add the same coin again
        wallet.addCoin(coin2);
        wallet.addCoin(coin3);
        wallet.addCoin(coin4);
        System.out.println("wallet contains coin of value 10: " + wallet.containsCoin(10));
        wallet.addCoin(coin5);
        wallet.addCoin(coin6);
        System.out.println("wallet contains coin of value 10: " + wallet.containsCoin(10));
        System.out.println("wallet total value: " + wallet.getWalletTotal());
        System.out.println("wallet size: " + wallet.getWalletSize());
        System.out.println("wallet pay : " + wallet.payExactMaximum(1.05));
        System.out.println("wallet total value: " + wallet.getWalletTotal());
        System.out.println("wallet size: " + wallet.getWalletSize());
        System.out.println("wallet pay minimum: " + wallet.payMinimum(7));
        System.out.println("wallet total value: " + wallet.getWalletTotal());
        System.out.println("wallet size: " + wallet.getWalletSize());
        System.out.println("wallet pay : " + wallet.pay(2));
        System.out.println("wallet size: " + wallet.getWalletSize());
        System.out.println("wallet total value: " + wallet.getWalletTotal());
        System.out.println("wallet contains total value of 10: " + wallet.containsAmmount(10));
        wallet.emptyWallet();
        System.out.println("wallet total value: " + wallet.getWalletTotal());
        System.out.println("wallet size: " + wallet.getWalletSize());




    }



}


