package homework2;

import java.util.ArrayList;
import java.util.Iterator;

public class Participant implements Simulatable<String> {
	
	private ArrayList<Transaction> donateList;
	private final String productNeeded;
	private int amountNeeded;
	private ArrayList<Transaction> storageBuffer;
	private String participantName;
	
	
	public Participant(String name, String product, int amount) {
		assert(name!=null):
			"Error: name is null pointer";
		ArrayList<Transaction> donateList= new ArrayList<Transaction>();
		ArrayList <Transaction> storageBuffer= new ArrayList<Transaction>();
		this.productNeeded=product;
		this.amountNeeded=amount;
		this.participantName=name;
		checkRep();
		
	}
	
	public ArrayList<Transaction> getDonateList(){
		return new ArrayList<Transaction>(donateList);	
	}
	public ArrayList<Transaction> getStorageBuffer(){
		return new ArrayList<Transaction>(storageBuffer);	
	}
	public void addTransaction(Transaction transToSend) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateDonateList() {
		//run checks on input from Channel
		if(amountNeeded>0) {
			for(Iterator<Transaction> it= donateList.iterator();it.hasNext();it.next()) {
				if(productNeeded.equals(((Transaction) it).getProduct())) {
					int amountAvailable = (int)((Transaction)it).getAmount();
					if(amountAvailable <= amountNeeded) {
						storageBuffer.add((Transaction)it);
						amountNeeded-=amountAvailable;
						donateList.remove(it);
						break;
					}
					else {//amountAvailable > amountNeeded
						Transaction transToDonate= new Transaction(productNeeded,amountAvailable-amountNeeded );
						Transaction transToStore= new Transaction(productNeeded,amountNeeded );
						storageBuffer.add(transToStore);
						donateList.add(transToDonate);
						donateList.remove(it);
						amountNeeded=0;
						break;

						
					}
					
				}
			}
		}
	}

	public void simulate(BipartiteGraph<String> graph) {
		if(donateList.isEmpty()) {
			return;
		}
		//Send donations on to Channel
		ArrayList<String> ChannelList=graph.getChildrenList(participantName);
		if(ChannelList==null) {
			return;
		}
		int index_count=0;

		for (int i = 0; i < ChannelList.size(); i++) {
			String ChannelName =ChannelList.get(i % ChannelList.size());// TODO need to change i constantly?
			Channel currentChannel = (Channel) (graph.getObject(ChannelName));
			
			for(Iterator<Transaction> it=donateList.iterator();it.hasNext();it.next()) {
				if(currentChannel.addTransaction((Transaction)it)){
					donateList.remove(it);
					return;
				}
				
			}
		}
		
		
		
	}
	
	
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
