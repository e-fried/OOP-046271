

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
    
    
    
    public boolean addChild(L childLabel, L edgeLabel) {
        if (edgeLabel == null || childLabel == null){
        	System.err.println("Error: labels are null pointers");
            return false;
        }
        checkRep();
        childrenList.add(childLabel);
        Edge newEdge= new Edge<L>(edgeLabel, this.label,childLabel );
        this.childEdgeList.add(edgeLabel,newEdge );
        checkRep();
        return true;
    	
    }
    
	
    

}
