package homework2;
/**
 * This class implements an Edge in a BipartiteGraph. 
 * An Edge is directed in the direction from srcLabel to destLabel 
 * A typical Edge consists of the following set of
 * properties: {edgeLabel,srcLabel, destLabel}
 */
public class Edge <L>{
	
	// Abs. Function:
	// Represents an edge in a Bipartite graph
	// the Edge is directed from vertice srcLabel to destLabel
	// Rep. Invariant:
	// vertice has no null values in fields edgeLabel,srcLabel,destLabel
	
	
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
	public L getParentLabel(){
		return (this.srcLabel);
	}
	public L getChildLabel(){
		return (this.destLabel);
	}
		
	
	

}
