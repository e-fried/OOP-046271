package homework2;

import java.util.List;

public class Simulator<L,WO> {
	
	
	private BipartiteGraph<L> simulateGraph;
	public Simulator(String name){
		
		
		this.simulateGraph= new BipartiteGraph<L>(name);
		
			
	}
	public void addPipe(L labelPipe, Simulatable<L> pipe)  {
		checkRep();
		this.simulateGraph.addBlackVertice(labelPipe, pipe);
		checkRep();
	}

	public void addFilter(L labelFilter, Simulatable<L> filter) {
		checkRep();
		this.simulateGraph.addWhiteVertice(labelFilter, filter);
		checkRep();
	}
	public void simulate() {
		checkRep();

		List<L> whiteVertices = simulateGraph.listWhiteVertices();
		List<L> blackVertices = simulateGraph.listBlackVertices();
		
		for (L pipeLabel : blackVertices) {
			WO pipe = (WO) this.simulateGraph.getObject(pipeLabel);
			((Simulatable<L>) pipe).simulate(this.simulateGraph);
		}

		for (L filterLabel : whiteVertices) {
			WO filter = (WO) this.simulateGraph.getObject(filterLabel);
			((Simulatable<L>) filter).simulate(this.simulateGraph);
		}
		checkRep();
	}
	
	public void conecTwoObjects(L edgeLabel, L srcLabel, L destLabel) {
		
		checkRep();
		this.simulateGraph.addEdge(edgeLabel, srcLabel, destLabel);
		checkRep();
	}
	public BipartiteGraph<L> getBipartiteGraph(){
		return simulateGraph;
	}

	public Simulatable<L> getObj(L objLabel){
		checkRep();
		return ((Simulatable<L>) (this.simulateGraph.getObject(objLabel)));
			
		
	}
	private void checkRep(){
		
	}
	
}


