package homework2;

import java.util.ArrayList;
/**
 * This class implements a channel which is used in order to transfer transactions between Participants
 * A typical Channel consists of the following set of
 * properties: {transferList, channelLimit, channelName, currentAmount, index_count}
 * tranferList: a List of different transactions currently occupying the Channel
 * ChannelLimit: maxValue of all products occupying the Channel
 * currentAmount: current Value of all products occupying the Channel
 */
public class Channel implements Simulatable<String> {
	
	// Abs. Function:
	// Represents a Channel in a system used to transfer products between Participants 
	// the Channel is connected only to other Participants by ingoing or outgoing edges
	// the channel holds all the transactions occupying the Channel, 
	// that their summed value must be smaller than the channelLimit 
	// Rep. Invariant:
	// Channel has no null values in fields
	// channelLimit must be greater than 0
	// currentAmount must be smaller the channelLimit

	private ArrayList<Transaction> transferList;
	private final int channelLimit;
	private String channelName;
	private int currentAmount;
	private int index_count;
	
	/**
	 * @effects Initializes this with a a given name and channelLimit.
	 * @requires Limit >0 ,name !=null     
	 */
	
	public Channel(String name, int limit ) {
		assert(limit >0):
			"Error channel limit is smaller than 0";
		assert(name !=null):
			"Error: ChannelName is a null pointer";
		this.transferList =new ArrayList<Transaction>();
		this.channelLimit=limit;
		this.channelName=name;
		this.currentAmount=0;
		this.index_count=0;
		checkRep();
		
	}
    /**
     * @modifies none
     * @effects return list of Transaction in Channel 
     */  
	public ArrayList<Transaction> getTransferList(){
		checkRep();
		return new ArrayList<Transaction>(transferList); 
	}
    /**
     * @modifies transferList
     * @effects adds Transaction to transferList  
     * @requires incomingTransaction !=null
     *
     */ 
	public boolean addTransaction(Transaction incomingTransaction) {
		checkRep();
		if (this.currentAmount >= channelLimit) {
			return false;
		} else if (this.currentAmount +incomingTransaction.getAmount() > channelLimit) {
			Transaction newTransaction = new Transaction(incomingTransaction.getProduct(), channelLimit - currentAmount);
			this.transferList.add(newTransaction);
			this.currentAmount = this.channelLimit;
			checkRep();
			return true;
		} else { //don't reach channelLimit
			this.transferList.add(incomingTransaction);
			this.currentAmount += incomingTransaction.getAmount();
			checkRep();
			return true;
		}
	}
	/**
	 * @modifies this, graph
	 * @effects Simulates this Channel operations in one iteration
	 */
	public void simulate(BipartiteGraph<String> graph) {
		checkRep();
		if(transferList.isEmpty()) {
			return;
		}
		ArrayList<String> childList =graph.getChildrenList(channelName);
		if(childList==null) {
			
			return;
		}
		if(childList.size()==0) {
			
			return;
		}
		String ParticipantName =childList.get(index_count % childList.size());
		Participant ParticipantToSend=(Participant)graph.getObject(ParticipantName);
		Transaction transToSend=transferList.get(0);
		transferList.remove(0);
		ParticipantToSend.addTransaction(transToSend);
		ParticipantToSend.updateDonateList();
		index_count++;
		currentAmount-=transToSend.getAmount();
		checkRep();

		
		
	}
	
     /**
     * @modifies none
     * @effects assures this and its fields don't change to invalid values during run time
     */
	private void checkRep() {
		assert(transferList !=null):
			"Error: transferList is a null pointer";
		assert(channelName !=null):
			"Error: channelName is a null pointer";
		assert(channelLimit >0):
			"Error channel limit is smaller than 0";
		assert(currentAmount < channelLimit):
			"Error: currentAmount Exceeds channel limit ";
		
	}
}
