package homework2;


import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * This class implements a vertice in a BipartiteGraph. 
 * A typical vertice consists of the following set of
 * properties: {color, label}
 * the vertice can return a list of its children or parent veritces in O(1)
 * the vertice can return an edge that leaves or enters it in  O(1)
 */
public class Vertice <L>{
	// Abs. Function:
	// Represents a Black or White vertice in a Bipartite graph, the vertice has a unique label
	// the vertice can be connected to other vertices with ingoing or outgoing edges
	// Rep. Invariant:
	// vertice has no null values in fields
	// childrenList, ParentList, childEdgeList, parentEdgeList have no duplicate label values
	// verticeColor can be Black or White only
	
	
	private ArrayList<L> childrenList;
	private ArrayList<L> parentList;
	private HashMap <L, Edge<L>> childEdgeList;
	private HashMap <L, Edge<L>> parentEdgeList;
	String verticeColor;
    private final L label;
    
        
	/**
	 * @effects Initializes this with a a given label and color.
	 * @requires Label !=null ,color !=null     
	 */

    public Vertice(L label, String color) {
    	assert(label != null):
    		"Error: label is a null pointer";
    	//assert((color != BLACK && color != "WHITE") ):
    	//	"Wrong color";
    	this.childrenList= new ArrayList<L>();
    	this.parentList= new ArrayList<L>();
    	this.childEdgeList= new HashMap <L, Edge<L>>();
    	this.parentEdgeList= new HashMap <L, Edge<L>>();
    	this.verticeColor=color;
    	this.label=label;
    	checkRep();	
    }
    
    
    /**
     * @modifies none
     * @effects if this.childrenList has child with Label- label return true, else return false;
     */ 
    public boolean doesChildExist(L label) {
    	checkRep();
    	if(label ==null) {
    		return true;
    	}
    	if(childrenList.contains(label)) {
    		checkRep();
    		return true;
    	}
    	checkRep();
    	return false;
    }
    
    /**
     * @modifies none
     * @effects if this.parentList has parent with Label-label return true, else return false;
     */    
    public boolean doesParentExist(L label) {
    	checkRep();
    	if(label ==null) {
    		return true;
    	}
    	if(parentList.contains(label)) {
    		checkRep();
    		return true;
    	}
    	checkRep();
    	return false;
    }
    
    /**
     * @modifies none
     * @effects returns color of this
     */  
    public String getColor() {
    	checkRep();
    	return this.verticeColor;
    }
    
    /**
     * @modifies none
     * @effects returns label of this
     */      
    public L getLabel() {
    	checkRep();
    	return this.label;
    }
    
    
    /**
     * @modifies childList
     * @effects adds childLabel to  childrenList and adds edgeLabel to childEdgeList returns false if edge or child exist
     * @requires childLabel !=null ,edgeLabel !=null, newEdge != null  
     */  
    
    
    
    public boolean addChild(L childLabel, L edgeLabel, Edge<L> newEdge) {
        if (edgeLabel == null || childLabel == null || newEdge ==null){
        	System.err.println("Error: labels are null pointers");
            return false;
        }
        checkRep();
        if(childrenList.contains(childLabel) || childEdgeList.containsKey(edgeLabel)) {
        	System.err.println("Error: edge already exists from parent to child");
        	return false;
        }
        childrenList.add(childLabel);
        this.childEdgeList.put(edgeLabel,newEdge );
        checkRep();
        return true;
    	
    }
    
    
    
    
    /**
     * @modifies parentList
     * @effects adds ParentLabel to parent list 
     * @requires parentLabel !=null ,edgeLabel !=null, newEdge != null 
     *
     */ 
    
    public boolean addParent(L parentLabel, L edgeLabel, Edge<L> newEdge) {
        if (edgeLabel == null || parentLabel == null || newEdge ==null){
        	System.err.println("Error: labels are null pointers");
            return false;
        }
        checkRep();
        if(childrenList.contains(parentLabel) ||  parentEdgeList.containsKey(edgeLabel) ) {
        	System.err.println("Error: edge already exists from parent to child");
        	return false;
        }
        parentList.add(parentLabel);
        this.parentEdgeList.put(edgeLabel,newEdge );
        checkRep();
        return true;
    	
    }
    
    
    /**
     * @modifies none
     * @effects return list of children 
     */ 
	
    public ArrayList<L> listChildren() {
        checkRep();
    	final ArrayList <L> returnList=copyList(this.childrenList);
        checkRep();
        return returnList;
    		
    }
    
    /**
     * @modifies none
     * @effects return list of parents 
     */  
    public ArrayList<L> listParents() {
        checkRep();
    	final ArrayList <L> returnList=copyList(this.parentList);
        checkRep();
        return returnList;
    		
    }
    
    /**
     * @modifies none
     * @param listToCopy- A list which I want to return a copy of
     * @effects returns copy of given list 
     * @requires listToCopy !=null 
     * @return ArrayList <L> copy of input list 
     */  
   private ArrayList<L> copyList(ArrayList<L> listToCopy){
    	assert (listToCopy != null):
        	"Error: Label is null pointer";
    	checkRep();
    	ArrayList<L> listToReturn=new ArrayList<L>();
    	
    	Iterator<L> iter = listToCopy.iterator();
    	while(iter.hasNext()) {
    		listToReturn.add(iter.next());
    	}
    	checkRep();
    	return listToReturn;
    }
    
    /**
     * @modifies none
     * @effects finds child by given edge label
     * @requires edgeLabel !=null 
     * @return if one of vertices's edges contain an edge with label: edgeLabel, return child label. 
     * if edgeLabel doesn't exist return null;
     */
    public L getChildByEdgeLabel(L edgeLabel) {
    	assert (edgeLabel != null):
        	"Error: Label is null pointer";
    	checkRep();
    	if(childEdgeList.containsKey(edgeLabel)) {
    		L childLabel =(L)((childEdgeList.get(edgeLabel)).getChildLabel()); 
        	checkRep();
    		return childLabel;
    	}
    	checkRep();
    	return null;
    	
    }
    
    /**
     * @modifies none
     * @effects finds child by given edge label
     * @requires edgeLabel !=null
     * @return if one of vertices's incoming edges contain an edge with label: edgeLabel, return parent label. if edgeLabel doesn't exist return null;
     */
    public L getParentByEdgeLabel(L edgeLabel) {
    	assert (edgeLabel != null):
        	"Error: Label is null pointer";
    	checkRep();
    	if(parentEdgeList.containsKey(edgeLabel)) {
    		L parentLabel =(L)((parentEdgeList.get(edgeLabel)).getParentLabel()); 
        	checkRep();
    		return parentLabel;
    	}
    	checkRep();
    	return null;
    	
    }
    /**
     * @modifies none
     * @requires edgeLabel !=null
     * @effects if one of vertices's edges contain an edge with label: edgeLabel, return true. if edgeLabel doesn't exist return false;
     */
    public boolean doesChildEdgeExist(L edgeLabel) {
    	assert (edgeLabel != null):
        	"Error: Label is null pointer";
    	checkRep();
    	if(childEdgeList.containsKey(edgeLabel)) {
        	checkRep();
    		return true;
    	}
    	checkRep();
    	return false;
    	
    }
    /**
     * @modifies none
     * @requires edgeLabel !=null
     * @effects if one of vertices's edges contain an edge with label: edgeLabel, return true. if edgeLabel doesn't exist return false;
     */
    public boolean doesParentEdgeExist(L edgeLabel) {
    	assert (edgeLabel != null):
        	"Error: Label is null pointer";
    	checkRep();
    	if(parentEdgeList.containsKey(edgeLabel)) {
        	checkRep();
    		return true;
    	}
    	checkRep();
    	return false;
    	
    }
    
    /**
     * @modifies none
     * @requires parentLabel !=null
     * @effects if this.parentList has parent with Label parentLabel return true, else return false;
     */
    public boolean hasParentWithLabel(L parentLabel) {
    	assert (parentLabel != null):
        	"Error: Label is null pointer";
    	checkRep();
    	if(this.parentList.contains(parentLabel)) {
        	checkRep();
    		return true;
    	}
    	checkRep();
    	return false;
    }
    
    /**
     * @modifies none
     * @effects assures this and its fields don't change to invalid values during run time
     */
    private void checkRep() {
    	assert (this.label != null):
        	"Error: Label is null pointer";
    	assert (this.verticeColor.equals("Black") || this.verticeColor.equals("White")):
        	"Error: invaid color";
    	assert (this.childrenList !=null):
        	"Error: childrenList is a null pointer";
    	assert (this.parentList !=null):
        	"Error: parentList is a null pointer";
    	assert (this.childEdgeList !=null):
        	"Error: childEdgeList is a null pointer";
    	assert (this.parentEdgeList !=null):
        	"Error: parentEdgeList is a null pointer";
    	
    	// check that there aren't 2 labels that are equal in childrenList
    	/*Iterator<L> childenIter = this.childrenList.iterator();
    	while(childenIter.hasNext()) {
    		Iterator<L> tempIter=childenIter;
    		while(tempIter.hasNext()) {
    			assert(!(childenIter.equals(tempIter.next()))):
    				"Error: Label is null pointer";
    		}
    		childenIter.next();
    		
    	}*/
      
        HashSet <L> noDupChildrenSet = new HashSet<L>(this.childrenList);
        assert(noDupChildrenSet.size()>= childrenList.size()):
        	"Error: there are duplicate labels in childernList";
        
        HashSet <L> noDupParentSet = new HashSet<L>(this.parentList);
        assert(noDupParentSet.size()>= parentList.size()):
        	"Error: there are duplicate labels in ParentList";
        
//        Iterator<java.util.Map.Entry<L, Edge<L>>> cIt = childEdgeList.entrySet().iterator();
//        while (cIt.hasNext()) {
//            Map.Entry pair = (Map.Entry)cIt.next();
//            assert(pair.getKey().equals(((Edge<L>) pair.getValue()).getChildLabel())):
//            	"Error vertice child label doesn't match childEdgeList label ";
//            cIt.remove(); // avoids a ConcurrentModificationException
//        }
//        Iterator<java.util.Map.Entry<L, Edge<L>>> pIt = parentEdgeList.entrySet().iterator();
//        while (pIt.hasNext()) {
//            Map.Entry pair = (Map.Entry)pIt.next();
//            assert(pair.getKey().equals(((Edge<L>) pair.getValue()).getParentLabel())):
//            	"Error vertice parent label doesn't match parentEdgeList label ";
//            pIt.remove(); // avoids a ConcurrentModificationException
//        }


    	// check that there aren't 2 labels that are equal in childrenList
        /*
    	Iterator<L> parentIter = this.parentList.iterator();
    	while(parentIter.hasNext()) {
    		Iterator<L> tempIter=parentIter;
    		while(tempIter.hasNext()) {
    			assert(!(parentIter.equals(tempIter.next()))):
    				"Error: Label is null pointer";
    		}
    		parentIter.next();
    		
    	}
    	*/
    	
    }
	
	
    /**
     * @modifies childrenList
     * @effects removes ParentLabel to parent list 
     * @requires childLabel !=null ,edgeLabel !=null  
     * @return true if remove succeed, false if not
     */  
    public boolean removeChild(L childLabel, L edgeLabel) {
        if (edgeLabel == null || childLabel == null){
        	System.err.println("Error: labels are null pointers");
            return false;
        }
        checkRep();
        childrenList.remove(childLabel);
        this.childEdgeList.remove(edgeLabel);
        checkRep();
        return true;	
    }
    
    /**
     * @modifies parentList
     * @effects removes ParentLabel to parent list 
     * @requires parentLabel !=null ,edgeLabel !=null  
     * @return true if remove succeed, false if not
     */ 
    
    public boolean removeParent(L parentLabel, L edgeLabel) {
        if (edgeLabel == null || parentLabel == null){
        	System.err.println("Error: labels are null pointers");
            return false;
        }
        checkRep();
        parentList.remove(parentLabel);
        this.parentEdgeList.remove(edgeLabel);
        checkRep();
        return true;	
    }
    

}
