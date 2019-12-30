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
		this.donateList= new ArrayList<Transaction>();
		this.storageBuffer= new ArrayList<Transaction>();
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
	public void addTransaction(Transaction transSent) {
		donateList.add(transSent);
		
	}
	
	public void updateDonateList() {
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
		

		for (int i = 0; i < ChannelList.size(); i++) {
			String ChannelName =ChannelList.get(i % ChannelList.size());// TODO need to change i constantly?
			Channel currentChannel = (Channel) (graph.getObject(ChannelName));
			
			for(Iterator<Transaction> it=donateList.iterator();it.hasNext();) {
				Transaction tmp =it.next();
				if(currentChannel.addTransaction(tmp)){
					donateList.remove(tmp);
					return;
				}
				
			}
		}
		
		
		
	}
	public double getDonateSum(){
		checkRep();
		double sum=0;
		for(Iterator<Transaction> it= donateList.iterator();it.hasNext();) {
			sum+=(it.next()).getAmount();
		}
		checkRep();
		return sum;
	
	}
	public double getStorageSum(){
		checkRep();
		double sum=0;
		for(Iterator<Transaction> it= storageBuffer.iterator();it.hasNext();) {
			
			sum+=(it.next()).getAmount();
		}
		checkRep();
		return sum;
	
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
