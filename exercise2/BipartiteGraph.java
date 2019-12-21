package homework2;
import java.util.*;
/**
 * A BipartiteGraph is a Bipartite Graph where there are two types of vertices -
 * white vertices and black vertices. 
 * The graph has edges too. an edge connects between two vertices in a directed form.
 * Each edge can connect only between vertices of different colors. 
 * The Graph has the following methods, addVertice, addEdge,listBlackNodes, listWhiteNodes 
 * A typical BipartiteGraph consists of the following set of properties: {Vertices, Edges, Label}
 */

public class BipartiteGraph <L>{
	// Abs. Function:
	// Represents a Bipartite Directed Graph with black vertices and white vertices 
	// the graph is represented by {vertices, Edges, Label}
	// Rep. Invariant:
	// Vertices != null && Edges != null && Label != null  
	private HashMap<L, Vertice<L>> verticesHashMap; 
	private String graphLabel;

    public BipartiteGraph(String graphLabel_) {
        assert (graphLabel != null):
        	"Error: graphLabel is null pointer";
        
        this.verticesHashMap = new HashMap<>();
        this.graphLabel = graphLabel_;
        checkRep();        
    }
    
    public void addEdge(L edgeLabel, L srcLabel, L destLabel ) {
        checkRep();
        assert (edgeLabel != null || srcLabel != null || destLabel != null):
        	"Error: one of inputs is null pointer";

        if (!verticesHashMap.containsKey(srcLabel) || !verticesHashMap.containsKey(destLabel)){
            System.out.println("One of input vertices dosn't exist in the graph");
            return;
        }
        if ( this.verticesHashMap.get(srcLabel).getColor() == this.verticesHashMap.get(destLabel).getColor()){
            System.out.println("Two vertices with the same color must not be connected");
            return;
        }
        // check if we have some edge from parent -> child
        Node<L> parentNode = this.nodeHashMap.get(parentLabel);
        if (parentNode.childrenContainsLabel(childLabel)){
            System.err.println("there is already an edge from parent->child");
            return false;
        }

        // check if we have edge with label X from parent
        if (parentNode.childrenContainsEdgeLabel(edgeLabel)){
            System.err.println("there is already an edge from parent with edge label");
            return false;
        }

        // check if we have edge from some parent to child with label X
        Node<L> childNode = this.nodeHashMap.get(childLabel);
        if (childNode.parentsContainsEdgeLabel(edgeLabel)){
            System.err.println("there is already an edge to child with edge label");
            return false;
        }

        parentNode.addChild(childLabel, edgeLabel);
        childNode.addParent(parentLabel,edgeLabel);
        checkRep();
        return true;
    }
    
    
    public void addWhiteVertice(L verticeLabel){
        checkRep();
        assert (verticeLabel != null):
        	"Error: verticeLabel is null pointer";
        //assert (blackVertice != null):
        	//"Error: blackVertice is null pointer";
        addVertice(verticeLabel, "White");
        checkRep();
    }
    
    public void addBlackVertice(L verticeLabel){
        checkRep();
        assert (verticeLabel != null):
        	"Error: verticeLabel is null pointer";
        addVertice(verticeLabel, "Black");
        checkRep();
    }
    
    private void addVertice(L verticeLabel, String color) {
        checkRep();
        assert (verticeLabel != null):
        	"Error: verticeLabel is null pointer";
        if (verticesHashMap.containsKey(verticeLabel)){
            System.out.println("Error: This vertice already exist");
            return;
        }
        Vertice<L> newVertice = new Vertice<>(verticeLabel, color);
        verticesHashMap.put(verticeLabel, newVertice);
        checkRep();
        
    }
    
    


}
