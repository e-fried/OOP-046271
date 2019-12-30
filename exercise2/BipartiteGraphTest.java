package homework2;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
 */
public class BipartiteGraphTest {

	@Test
    public void testExample() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        
        //create a graph
        driver.createGraph("graph1");
        
        //add a pair of nodes
        driver.addBlackNode("graph1", "n1",null);
        driver.addWhiteNode("graph1", "n2",null);
        
        //add an edge
        driver.addEdge("graph1", "n1", "n2", "edge");
        
        //check neighbors
        assertEquals("wrong black nodes", "n1", driver.listBlackNodes("graph1"));
        assertEquals("wrong white nodes", "n2", driver.listWhiteNodes("graph1"));
        assertEquals("wrong children", "n2", driver.listChildren ("graph1", "n1"));
        assertEquals("wrong children", "", driver.listChildren ("graph1", "n2"));
        assertEquals("wrong parents", "", driver.listParents ("graph1", "n1"));
        assertEquals("wrong parents", "n1", driver.listParents ("graph1", "n2"));
    }
    
    
    @Test
    public void checkNull() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph1");



        //assertEquals("Error: verticeLabel is null pointer", "", driver.addBlackNode("graph1", null));
        assertEquals("Error: verticeLabel is null pointer", "", driver.listBlackNodes("graph1"));
        //driver.addWhiteNode("graph1", null);
        assertEquals("Error: verticeLabel is null pointer", "", driver.listWhiteNodes("graph1"));

        driver.addBlackNode("graph1", "black_1",null);
        driver.addWhiteNode("graph1", "white_1",null);
        driver.addEdge("graph1", null, "white_1", "edge_1");
        driver.addEdge("graph1", "black_1", null, "edge_1");
       
        assertEquals("Error: edgeLabel is null pointer", "", driver.listParents("graph1", "white_1"));
        
        assertEquals("Error: edgeLabel is null pointer", "", driver.listChildren("graph1", "black_1"));
    }

//    private void assertEquals(String string, String string2, Object addBlackNode) {
//		// TODO Auto-generated method stub
//		
//	}


	@Test
    public void sameColorEdge() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph1");

        driver.addBlackNode("graph1", "black_1",null);
        driver.addWhiteNode("graph1", "white_1",null);
        driver.addBlackNode("graph1", "black_2",null);
        driver.addWhiteNode("graph1", "white_2",null);

        driver.addEdge("graph1", "black_1", "black_2", "edge_1");
        assertEquals("Error: add edge to vertice with the same color", "", driver.listChildren("graph1", "black_1"));
        assertEquals("Error: add edge to vertice with the same color", "", driver.listParents("graph1", "black_2"));

        driver.addEdge("graph1", "white_1", "white_2", "edge_1");
        assertEquals("Error: add edge to vertice with the same color", "", driver.listChildren("graph1", "white_1"));
        assertEquals("Error: add edge to vertice with the same color", "", driver.listParents("graph1", "white_2"));
    }

    @Test
    public void sameLabelNode() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph1");

        driver.addBlackNode("graph1", "adi",null);
        driver.addBlackNode("graph1", "adi",null);
        driver.addWhiteNode("graph1", "adi",null);
        assertEquals("Error: adding node with the same label", "adi", driver.listBlackNodes("graph1"));
        assertEquals("Error: adding node with the same label", "", driver.listWhiteNodes("graph1"));

        driver.addWhiteNode("graph1", "elisha",null);
        driver.addWhiteNode("graph1", "elisha",null);
        driver.addBlackNode("graph1", "elisha",null);
        assertEquals("Error: adding node with the same label", "adi", driver.listBlackNodes("graph1"));
        assertEquals("Error: adding node with the same label", "elisha", driver.listWhiteNodes("graph1"));
    }

    @Test
    public void checkAddEdgeToMissingNode() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph1");

        driver.addBlackNode("graph1", "black_1",null);
        driver.addWhiteNode("graph1", "white_1",null);
        driver.addEdge("graph1", "black_1", "white_2", "edge_1");
        driver.addEdge("graph1", "black_2", "white_1", "edge_1");
        assertEquals("Error: adding edge to no exist node", "", driver.listChildren("graph1", "black_1"));
        assertEquals("Error: adding edge to no exist node", "", driver.listParents("graph1", "white_1"));
    }

    @Test
    public void checkGetByEdgeLabel() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph1");

        driver.addBlackNode("graph1", "black_1",null);
        driver.addWhiteNode("graph1", "white_1",null);
        driver.addEdge("graph1", "black_1", "white_1", "edge_1");
        assertEquals("Error: getChildByEdgeLabel", "white_1", driver.getChildByEdgeLabel("graph1", "black_1", "edge_1"));
        assertEquals("Error: getChildByEdgeLabel- null", null, driver.getChildByEdgeLabel("graph1", "white_1", "edge_1"));
        assertEquals("Error: getParentByEdgeLabel- null", null, driver.getParentByEdgeLabel("graph1", "black_1", "edge_1"));
        assertEquals("Error: getParentByEdgeLabel", "black_1", driver.getParentByEdgeLabel("graph1", "white_1", "edge_1"));
    }
    
  
}
