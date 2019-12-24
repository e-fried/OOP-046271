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
        //check vertices existence
        if (!verticesHashMap.containsKey(srcLabel) || !verticesHashMap.containsKey(destLabel)){
            System.out.println("One of input vertices dosn't exist in the graph");
            return;
        }
        //check color laws
        if ( this.verticesHashMap.get(srcLabel).getColor() == this.verticesHashMap.get(destLabel).getColor()){
            System.out.println("Two vertices with the same color must not be connected");
            return;
        }
        //check edges laws
        if (verticesHashMap.get(destLabel).hasParentWithLabel(srcLabel)){
            System.out.println("There is forbbiden to create another edge from father vertice to child vertice");
            return;
        }
        //check child edge label laws
        if (verticesHashMap.get(destLabel).doesParentEdgeExist(edgeLabel)){
            System.out.println("There is forbbiden to create another edge to child "
            		+ "vertice with the same label name");
            return;
        }
        //check father edge label laws
        if (verticesHashMap.get(srcLabel).doesChildEdgeExist(edgeLabel)){
            System.out.println("There is forbbiden to create another edge from father "
            		+ "vertice with the same label name");
            return;
        }

        
        Edge<L> edge = new Edge<L>(edgeLabel ,srcLabel ,destLabel);

        if (!verticesHashMap.get(srcLabel).addChild(destLabel, edgeLabel, edge)){
        	System.out.println("addChild error");
        }
        
        if(!verticesHashMap.get(destLabel).addParent(srcLabel,edgeLabel, edge)){
        	System.out.println("addParent error");
        }
        checkRep();
        return;
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
    
    public boolean containsVertice(L verticeLabel) {
        assert (verticeLabel != null):
        	"Error: verticeLabel is null pointer";
        checkRep();
        if(verticesHashMap.containsKey(verticeLabel)){
        	return true;
        }
        return false;
    	
    }
    public ArrayList<L> listWhiteVertices() {
    	ArrayList <L> whiteList=new ArrayList<L>();
    	String str = "White";
    	for (Vertice<L> vertice : verticesHashMap.values()) {
    	    if(vertice.getColor().equals(str)) {
    	    	whiteList.add(vertice.getLabel());
    	    }
    	}
    	
    	checkRep();
    	return whiteList;
    }

    public ArrayList<L> listBlackVertices() {
    	ArrayList <L> blackList=new ArrayList<L>();
    	String str = "Black";
    	for (Vertice<L> vertice : verticesHashMap.values()) {
    	    if(vertice.getColor().equals(str)) {
    	    	blackList.add( vertice.getLabel());
    	    }
    	}
    	
    	checkRep();
    	return blackList;
    }
    private void checkRep(){
    	
    }
    
    


}
