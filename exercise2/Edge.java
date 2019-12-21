package homework2;

public class Edge <L>{
	
	private L edgeLabel ;
	private L srcLabel ;
	private L destLabel ;
	
	public Edge (L edgeLabel_ ,L srcLabel_ ,L destLabel_) {
		this.edgeLabel=edgeLabel_;
		this.srcLabel = srcLabel_;
		this.destLabel = destLabel_;
	}
	public L getEdgeLabel(){
		return (this.edgeLabel);
	}
	public L getFatherLabel(){
		return (this.srcLabel);
	}
	public L getChildLabel(){
		return (this.destLabel);
	}
		
	
	

}
