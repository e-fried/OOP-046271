

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

public class Vertice <L>{

	
	private ArrayList<L> childrenList;
	private ArrayList<L> parentList;
	private HashMap <L, Edge> childEdgeList;
	String verticeColor;
    private L label;
    
    

    public Vertice(L label, String color) {
    	assert(label != null):
    		"Error: label is a null pointer";
    	//assert((color != BLACK && color != "WHITE") ):
    	//	"Wrong color";
    	this.childrenList= new ArrayList<L>();
    	this.parentList= new ArrayList<L>();
    	this.childEdgeList= new HashMap <L, Edge>;
    	this.verticeColor=color;
    	this.label=label;
    	checkRep();	
    }
    
    
    /**
     * @modifies none
     * @effects if this.childrenList has child with Label label return true, else return false;
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
     * @effects if this.parentList has parent with Label label return true, else return false;
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
     * @modifies parentList
     * @effects adds childLabel to  childrenList and adds edgeLabel to childEdgeList returns false if edge or child exist
     *
     */  
    
    
    public boolean addChild(L childLabel, L edgeLabel) {
        if (edgeLabel == null || childLabel == null){
        	System.err.println("Error: labels are null pointers");
            return false;
        }
        checkRep();
        if(childrenList.contains(childLabel) || childEdgeList.containsKey(edgeLabel)) {
        	System.err.println("Error: edge already exists from parent to child");
        	return false;
        }
        childrenList.add(childLabel);
        Edge newEdge= new Edge<L>(edgeLabel, this.label,childLabel );
        this.childEdgeList.add(edgeLabel,newEdge );
        checkRep();
        return true;
    	
    }
    
    
    
    
    /**
     * @modifies parentList
     * @effects adds ParentLabel to parent list 
     *
     */  
    
    public boolean addParent(L parentLabel, L edgeLabel) {
        if (edgeLabel == null || parentLabel == null){
        	System.err.println("Error: labels are null pointers");
            return false;
        }
        checkRep();
        if(childrenList.contains(parentLabel) ) {
        	System.err.println("Error: edgae already exists from parent to child");
        	return false;
        }
        parentList.add(parentLabel);
        //Edge newEdge= new Edge<L>(edgeLabel, this.label,ParentLabel );
        //this.childEdgeList.add(edgeLabel,newEdge );
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
     * @effects if one of vertices's edges contain an edge with label: edgeLabel, return child label. if edgeLabel doesn't exist return null;
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
     * @effects if one of vertices's edges contain an edge with label: edgeLabel, return true. if edgeLabel doesn't exist return false;
     */
    public boolean doesEdgeLabelExist(L edgeLabel) {
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
     * @effects assures this and its fields don't change to invalid values
     */
    private void checkRep() {
    	
    }
	
    

}
