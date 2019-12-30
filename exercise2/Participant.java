package homework2;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * This class implements a Participant which can donate products to other Participants through Channels
 * A typical Participant consists of the following set of
 * properties: {donateList, productNeeded, amountNeeded, storageBuffer, participantName}
 * donateList: a List of different products and their amount, the Participant wants to donate
 * productNeeded, amountNeeded: the Product this Participant would like and the amount of these
 * storageBuffer: holds the products this participant recieved from others
 */
public class Participant implements Simulatable<String> {
	// Abs. Function:
	// Represents a Participant in a system used to transfer donations between Participants using channels 
	// the Participants are connected only to other Channels by ingoing or outgoing edges
	// the Participant holds donations of Transactions he would like to transfer
	// Rep. Invariant:
	// amountNeeded is a positive value
	// aname of product or participant must not be null
	private ArrayList<Transaction> donateList;
	private final String productNeeded;
	private int amountNeeded;
	private ArrayList<Transaction> storageBuffer;
	private String participantName;
	
	/**
	 * @effects Initializes this with a a given name, a name of product needed, and amount of this product
	 * @requires amount >=0 ,name !=null, product !=null     
	 */
	public Participant(String name, String product, int amount) {
		assert(name!=null):
			"Error: name is null pointer";
		assert(product!=null):
			"Error: product is null pointer";
		this.donateList= new ArrayList<Transaction>();
		this.storageBuffer= new ArrayList<Transaction>();
		this.productNeeded=product;
		this.amountNeeded=amount;
		this.participantName=name;
		checkRep();
		
	}
    /**
     * @modifies none
     * @effects return list of donationList in Participant 
     */  
	public ArrayList<Transaction> getDonateList(){
		checkRep();
		return new ArrayList<Transaction>(donateList);	
	}
	
    /**
     * @modifies none
     * @effects return list of storageBuffer in Participant 
     */  
	public ArrayList<Transaction> getStorageBuffer(){
		checkRep();
		return new ArrayList<Transaction>(storageBuffer);	
	}
    /**
     * @modifies this.donateList
     * @effects adds Transaction to donateList  
     * @requires transSent !=null
     *
     */ 
	public void addTransaction(Transaction transSent) {
		checkRep();
		donateList.add(transSent);
		checkRep();
		
	}
	
    /**
     * @modifies this, this.donateList
     * @effects adds Transaction to donateList  
     * 
     *
     */ 
	public void updateDonateList() {
		checkRep();
		//run checks on input from Channel
		if(amountNeeded>0) {
			for(Iterator<Transaction> it= donateList.iterator();it.hasNext();) {
				Transaction tmp = it.next();
				if(productNeeded.equals(tmp.getProduct())) {
					int amountAvailable = (int)(tmp).getAmount();
					if(amountAvailable <= amountNeeded) {
						storageBuffer.add(tmp);
						amountNeeded-=amountAvailable;
						donateList.remove(tmp);
						break;
					}
					else {//amountAvailable > amountNeeded
						Transaction transToDonate= new Transaction(productNeeded,amountAvailable-amountNeeded );
						Transaction transToStore= new Transaction(productNeeded,amountNeeded );
						storageBuffer.add(transToStore);
						donateList.add(transToDonate);
						donateList.remove(tmp);
						amountNeeded=0;
						break;

						
					}
					
				}
			}
		}
		checkRep();
	}
	
	/**
	 * @modifies this, graph
	 * @effects Simulates this Participants operations in one iteration
	 */
	public void simulate(BipartiteGraph<String> graph) {
		checkRep();
		if(donateList.isEmpty()) {
			return;
		}
		//Send donations on to Channel
		ArrayList<String> ChannelList=graph.getChildrenList(participantName);
		if(ChannelList==null) {
			return;
		}
		

		for (int i = 0; i < ChannelList.size(); i++) {
			String ChannelName =ChannelList.get(i % ChannelList.size());// TODO need to change i constantly?
			Channel currentChannel = (Channel) (graph.getObject(ChannelName));
			
			for(Iterator<Transaction> it=donateList.iterator();it.hasNext();) {
				Transaction tmp =it.next();
				if(currentChannel.addTransaction(tmp)){
					donateList.remove(tmp);
					checkRep();
					return;
				}
				
			}
		}
		
		
		
	}
	
    /**
     * @modifies none
     * @effects returns donateList transaction amount summed
     */  
	public double getDonateSum(){
		checkRep();
		double sum=0;
		for(Iterator<Transaction> it= donateList.iterator();it.hasNext();) {
			sum+=(it.next()).getAmount();
		}
		checkRep();
		return sum;
	
	}
	
    /**
     * @modifies none
     * @effects returns storageBuffer transaction amount summed
     */  
	public double getStorageSum(){
		checkRep();
		double sum=0;
		for(Iterator<Transaction> it= storageBuffer.iterator();it.hasNext();) {
			
			sum+=(it.next()).getAmount();
		}
		checkRep();
		return sum;
	
	}
	
     * @modifies none
     * @effects assures this and its fields don't change to invalid values during run time
     */
	private void checkRep() {
		assert  (donateList !=null):
			"Error: donateList == null";
		assert  (productNeeded !=null):
			"Error: productNeeded == null";
		assert  (storageBuffer !=null):
			"Error: storageBuffer == null";
		assert  (participantName !=null):
			"Error: participantName == null";
		assert  (amountNeeded >=0):
			"Error: amountNeeded is negative";
			
		
	}



}
