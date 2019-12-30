package homework2;

import java.util.*;

/**
 * This class implements a testing driver for BipartiteGraph. The driver
 * manages BipartiteGraphs whose nodes and edges are Strings.
 */
public class BipartiteGraphTestDriver {

    private Map<String, BipartiteGraph<String>> graphs;

    /**
     * @modifies this
     * @effects Constructs a new test driver.
     */
    public BipartiteGraphTestDriver () {
    	this.graphs= new HashMap<String, BipartiteGraph<String>>();
       
    }

    
    /**
     * @requires graphName != null
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public void createGraph(String graphName) {
        assert (graphName != null):
        	"Error: one of inputs is null pointer";
        if(graphs.containsKey(graphName)) {
        	System.err.println("Error: graph with this label already Exists");
        	return;
        }
        BipartiteGraph<String> newGraph= new BipartiteGraph<String>(graphName); 
        graphs.put(graphName,newGraph);
        
    	
    }

    
    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a black node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addBlackNode(String graphName, String nodeName, Object object) {
        assert (graphName != null || nodeName != null):
        	"Error: one of inputs is null pointer";
        if(graphs.containsKey(graphName)) {
        	if(!(graphs.get(graphName).containsVertice(nodeName))) {
            	graphs.get(graphName).addBlackVertice(nodeName,object);
            	return;

        	}
        	System.err.println("Error: vertice with this label already Exists");
        	return;
        }
        System.err.println("Error: Graph with the label graphName doesn't exist");
    	
    }

    
    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addWhiteNode(String graphName, String nodeName,Object object) {
        assert (graphName != null || nodeName != null):
        	"Error: one of inputs is null pointer";
        if(graphs.containsKey(graphName)) {
        	if(!(graphs.get(graphName).containsVertice(nodeName))) {
            	graphs.get(graphName).addWhiteVertice(nodeName,object);
            	return;
        	}
        	System.err.println("Error: vertice with this label already Exists");
        	return;
        }
        System.err.println("Error: Graph with the label graphName doesn't exist");
    	
    	
    }

    
    /**
     * @requires createGraph(graphName)
     *           && ((addBlackNode(parentName) && addWhiteNode(childName))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(String graphName,
    					String parentName, String childName, 
                        String edgeLabel) {
        assert (graphName != null || parentName != null || childName != null || edgeLabel != null):
        	"Error: one of inputs is null pointer";
        if(graphs.containsKey(graphName)) {
        	graphs.get(graphName).addEdge(edgeLabel, parentName,childName );
        }
    	
    	
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes(String graphName) {
    	assert(graphName != null):
    		"Error: graphName is a null pointer";
    	if(graphs.containsKey(graphName)) {
    		ArrayList<String> blackList =graphs.get(graphName).listBlackVertices();
    		java.util.Collections.sort(blackList);
    		return String.join(" ", blackList);
    	}
    	return null;
    	
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes(String graphName) {
    	assert(graphName != null):
    		"Error: graphName is a null pointer";
    	if(graphs.containsKey(graphName)) {
    		ArrayList<String> whiteList =graphs.get(graphName).listWhiteVertices();
    		java.util.Collections.sort(whiteList);
    		return String.join(" ", whiteList);
    	}
    	return null;
    	
    	
    }

    
    /**
     * @requires createGraph(graphName) && createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String graphName, String parentName) {
    	assert(graphName != null || parentName != null):
    		"Error: graphName or paretnName are a null pointer";
    	if(graphs.containsKey(graphName)) {
    		ArrayList<String> childrenList =graphs.get(graphName).getChildrenList(parentName);
    		if(childrenList !=null) {
    			java.util.Collections.sort(childrenList);
    			return String.join(" ", childrenList);
    		}
    	}
		return null;
    	
    }

    
    /**
     * @requires createGraph(graphName) && createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public String listParents(String graphName, String childName) {
    	assert(graphName != null || childName != null):
    		"Error: graphName or paretnName are a null pointer";
    	if(graphs.containsKey(graphName)) {
    		ArrayList<String> parentList =graphs.get(graphName).getParentList(childName);
    		if(parentList !=null) {
    			java.util.Collections.sort(parentList);
    			return String.join(" ", parentList);
    		}
    	}
		return null;
    }

    
    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getChildByEdgeLabel(String graphName, String parentName,
    								   String edgeLabel) {
    	assert( (graphName != null) || (parentName != null) || (edgeLabel != null)):
    		"Error: inputs are a null pointer";
    	if(graphs.containsKey(graphName)) {
    		String childLabel= graphs.get(graphName).childDetect(edgeLabel, parentName);
    		return childLabel;
    	}
    	return null;
    	
    }

    
    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the 
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getParentByEdgeLabel(String graphName, String childName,
    									String edgeLabel) {
    	assert( (graphName != null) || (childName != null) || (edgeLabel != null)):
    		"Error: inputs are a null pointer";
    	if(graphs.containsKey(graphName)) {
    		String parentLabel= graphs.get(graphName).parentDetect(edgeLabel, childName);
    		return parentLabel;
    	}
    	return null;
    	
    	
    }
}