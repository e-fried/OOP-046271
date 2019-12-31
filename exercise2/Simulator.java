package homework2;

import java.util.List;

public class Simulator<L,WO> {
	// Abs. Function:
	// Simulator represents a general system which consist of pipes and channels which have conection between them.
	// by that each one of them can be simulated.
    	// The system is represented by Bitrpartite graph where pipes are represented as black nodes
    	// and filters are represented as white nodes.
    	//
    	// Rep. Invariant:
	// simulateGraph != null
	
	private BipartiteGraph<L> simulateGraph;
	/**
	 * @modifies this
	 * @effects Constructs a new Simulator with name.
	 */
	public Simulator(String name){
		
		
		this.simulateGraph= new BipartiteGraph<L>(name);
		
			
	}
	/**
	 * @requires labelPipe != null && labelPipe has
	 *           not been used in a previous addPipe()  or
	 *           addFilter() call on this Simulatable<L> object
	 * @effects adds to this Simulator a new pipe Object  which has interface Simulatable<L> and named by
	 *  		the Label labelPipe.
	 */
	public void addPipe(L labelPipe, Simulatable<L> pipe)  {
		checkRep();
		this.simulateGraph.addBlackVertice(labelPipe, pipe);
		checkRep();
	}
	/**
	 * @requires labelFilter != null && labelFilter has
	 *           not been used in a previous addPipe()  or
	 *           addFilter() call on this Simulatable<L> object
	 * @effects adds to this simulator a new filter Object  which has interface Simulatable<L> and named by
	 *  		the Label labelFilter.
	 */
	public void addFilter(L labelFilter, Simulatable<L> filter) {
		checkRep();
		this.simulateGraph.addWhiteVertice(labelFilter, filter);
		checkRep();
	}
	/**
	 * @modifies this
	 * @effects runs this Simulator for a single time slice.
	 */
	public void simulate() {
		checkRep();

		List<L> whiteVertices = simulateGraph.listWhiteVertices();
		List<L> blackVertices = simulateGraph.listBlackVertices();
		
		for (L pipeLabel : blackVertices) {
			Simulatable<L> pipe = (Simulatable<L>) this.simulateGraph.getObject(pipeLabel);
			((Simulatable<L>) pipe).simulate(this.simulateGraph);
		}

		for (L filterLabel : whiteVertices) {
			Simulatable<L>filter = (Simulatable<L>) this.simulateGraph.getObject(filterLabel);
			((Simulatable<L>) filter).simulate(this.simulateGraph);
		}
		checkRep();
	}
	/**
     * @requires edgeLabel != null && srcLabel != null && destLabel != null
     * 			 && Objects with srcLabel and destLabel already exist in graph.  
     * 			 && node named srcLabel has no other outgoing edge labeled edgeLabel 
	 *           && node named destLabel has no other incoming edge labeled edgeLabel
	 * @modifies this.simulateGraph
	 * @effects Adds an edge from the Node Object named srcLabel to the Node Object named
	 *          destLabel in this Simulator. The new edge's label
	 *          is the String edgeLabel.
	 */
	public void conecTwoObjects(L edgeLabel, L srcLabel, L destLabel) {
		
		checkRep();
		this.simulateGraph.addEdge(edgeLabel, srcLabel, destLabel);
		checkRep();
	}
    /**
     * @return this.simulateGraph
     */
	public BipartiteGraph<L> getBipartiteGraph(){
		return simulateGraph;
	}
    /**
     * @requires objLabel != null and Node with objLabel already exist at this graph
     * @return Object of the Node with objLabel in this Simulator.
     */
	public Simulatable<L> getObj(L objLabel){
		checkRep();
		return ((Simulatable<L>) (this.simulateGraph.getObject(objLabel)));
			
		
	}
	private void checkRep(){
        assert (simulateGraph != null):
        	"Error: simulateGraph is null pointer";
        List<L> fitlers = simulateGraph.listWhiteVertices();
        List<L> pipes = simulateGraph.listBlackVertices();
		
        for (L pipe : pipes ){
            assert (simulateGraph.getObject(pipe) != null):
            	"Error: pipe is null pointer"; 	
        }
        for (L filter : fitlers ){
            assert (simulateGraph.getObject(filter) != null):
            	"Error: filter is null pointer"; 	
        }
		
	}
	
}


