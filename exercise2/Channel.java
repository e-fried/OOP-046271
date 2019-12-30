package homework2;

import java.util.ArrayList;

public class Channel implements Simulatable<String> {

	private ArrayList<Transaction> transferList;
	private final int channelLimit;
	private String channelName;
	private int currentAmount;
	private int index_count;
	
	
	
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
	
	public ArrayList<Transaction> getTransferList(){
		return new ArrayList<Transaction>(transferList); 
	}
	
	public boolean addTransaction(Transaction incomingTransaction) {
		if (this.currentAmount >= channelLimit) {
			return false;
		} else if (this.currentAmount +incomingTransaction.getAmount() > channelLimit) {
			Transaction newTransaction = new Transaction(incomingTransaction.getProduct(), channelLimit - currentAmount);
			this.transferList.add(newTransaction);
			this.currentAmount = this.channelLimit;
			return true;
		} else { //don't reach channelLimit
			this.transferList.add(incomingTransaction);
			this.currentAmount += incomingTransaction.getAmount();
			return true;
		}
	}
	
	public void simulate(BipartiteGraph<String> graph) {
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
		
		
	}
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