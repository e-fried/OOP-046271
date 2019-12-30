package homework2;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
 */
public class SimulatorTest {

	@Test
    public void testExample() {
		SimulatorTestDriver driver = new SimulatorTestDriver();

		// create graph
		driver.createSimulator("sim1");

		// add a pair of nodes
		driver.addParticipant("sim1", "p1", "berries", 500);
		driver.addParticipant("sim1", "p2", "coke", 300);
		driver.addChannel("sim1", "c1", 300);

		// add an edge
		driver.addEdge("sim1", "p1", "c1", "e1");
		driver.addEdge("sim1", "c1", "p2", "e2");

		Transaction tx = new Transaction("coke", 100);
		driver.sendTransaction("sim1", "c1", tx);

		driver.printAllEdges("sim1");


		assertEquals("Error Wrong p1 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
		assertEquals("Error Wrong p1 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
		assertEquals("Error Wrong p2 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p2"));
		assertEquals("Error Wrong p2 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
		assertEquals("Error Wrong c1 amount", "100", driver.listContents("sim1", "c1"));

		// simulate
		driver.simulate("sim1");


		assertEquals("Error Wrong p1 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
		assertEquals("Error Wrong p1 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
		assertEquals("Error Wrong p2 storage amount", 100, driver.getParticipantStorageAmount("sim1", "p2"));
		assertEquals("Error Wrong p2 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
		assertEquals("Error Wrong c1 amount", "", driver.listContents("sim1", "c1"));
		

    }
	@Test
    public void checkLimitEqualsValue() {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        driver.createSimulator("sim1");
        driver.addParticipant("sim1", "p1","water", 200);
        driver.addParticipant("sim1", "p2","Honey", 10);
        driver.addParticipant("sim1", "p3","Steak",10);
        driver.addChannel("sim1", "c1", 1000);
        driver.addChannel("sim1", "c2", 50);
        driver.addEdge("sim1", "p1", "c1", "e1");
        driver.addEdge("sim1", "c1", "p2", "e2");
        driver.addEdge("sim1", "p2", "c2", "e3");
        driver.addEdge("sim1", "c2", "p3", "e4");

        Transaction tx1 = new Transaction("fries", 50);
        Transaction tx2 = new Transaction("honey", 50);
        driver.sendTransaction("sim1", "c1", tx1);
        driver.sendTransaction("sim1", "c1", tx2);
		//first run
        driver.simulate("sim1");

        assertEquals("Error Wrong p2 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
		assertEquals("Error Wrong p1 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
		assertEquals("Error Wrong p1 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
		assertEquals("Error Wrong p2 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p2"));
        assertEquals("error listContents", "50", driver.listContents("sim1", "c2"));

    }
	
	@Test
    public void checkLimitExceedsValue() {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        driver.createSimulator("sim1");
        driver.addParticipant("sim1", "p1","water", 200);
        driver.addParticipant("sim1", "p2","Honey", 10);
        driver.addParticipant("sim1", "p3","Steak",10);
        driver.addChannel("sim1", "c1", 1000);
        driver.addChannel("sim1", "c2", 50);
        driver.addEdge("sim1", "p1", "c1", "e1");
        driver.addEdge("sim1", "c1", "p2", "e2");
        driver.addEdge("sim1", "p2", "c2", "e3");
        driver.addEdge("sim1", "c2", "p3", "e4");

        Transaction tx1 = new Transaction("fries", 100);
        Transaction tx2 = new Transaction("Honey", 50);
        driver.sendTransaction("sim1", "c1", tx1);
        driver.sendTransaction("sim1", "c1", tx2);
		//first run
        driver.simulate("sim1");

        assertEquals("Error Wrong p1 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
		assertEquals("Error Wrong p1 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
		assertEquals("Error Wrong p2 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
		assertEquals("Error Wrong p2 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p2"));
		assertEquals("Error Wrong p3 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p3"));
		assertEquals("Error Wrong p3 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p3"));
        assertEquals("error listContents", "50", driver.listContents("sim1", "c1"));
        assertEquals("error listContents", "50", driver.listContents("sim1", "c2"));
		//second run
		driver.simulate("sim1");
        assertEquals("Error Wrong p1 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
        assertEquals("Error Wrong p1 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
		assertEquals("Error Wrong p2 storage amount", 10, driver.getParticipantStorageAmount("sim1", "p2"));
		assertEquals("Error Wrong p2 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
		assertEquals("Error Wrong p3 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p3"));
		assertEquals("Error Wrong p3 recycle amount", 50, driver.getParticipantToRecycleAmount("sim1", "p3"));
        assertEquals("error listContents", "", driver.listContents("sim1", "c1"));
        assertEquals("error listContents", "40", driver.listContents("sim1", "c2"));
		
    }

	@Test
    public void playerFailsToTransfer() {
        SimulatorTestDriver driver = new SimulatorTestDriver();
		// create graph
        driver.createSimulator("sim1");
        driver.addParticipant("sim1", "p1","water", 200);
        driver.addParticipant("sim1", "p2","Honey", 10);
        driver.addChannel("sim1", "c1", 1000);
        driver.addChannel("sim1", "c2", 50);
        driver.addEdge("sim1", "p1", "c1", "e1");
        driver.addEdge("sim1", "c1", "p2", "e2");
        driver.addEdge("sim1", "p2", "c2", "e3");

        Transaction tx1 = new Transaction("fries", 100);
        Transaction tx2 = new Transaction("Honey", 50);
        driver.sendTransaction("sim1", "c1", tx1);
        driver.sendTransaction("sim1", "c2", tx2);
		//first run
        driver.simulate("sim1");
        assertEquals("Error Wrong p1 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
        assertEquals("Error Wrong p1 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
		assertEquals("Error Wrong p2 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p2"));
		assertEquals("Error Wrong p2 recycle amount", 100, driver.getParticipantToRecycleAmount("sim1", "p2"));
        assertEquals("error listContents", "", driver.listContents("sim1", "c1"));
        assertEquals("error listContents", "50", driver.listContents("sim1", "c2"));
		
    }
	
	public void testLoop(){

		SimulatorTestDriver driver = new SimulatorTestDriver();

		// create graph
		driver.createSimulator("sim1");

		// add a pair of vertices
		driver.addParticipant("sim1", "p1", "Marijuana", 400);
		driver.addParticipant("sim1", "p2", "Gummy", 300);
		driver.addParticipant("sim1", "p3", "sugar", 200);
		driver.addChannel("sim1", "c1", 2000);
		driver.addChannel("sim1", "c2", 1000);
		driver.addChannel("sim1", "c3", 500);

		// add edges
		driver.addEdge("sim1", "p1", "c1", "e1");
		driver.addEdge("sim1", "c1", "p2", "e2");
		driver.addEdge("sim1", "p2", "c2", "e3");
		driver.addEdge("sim1", "c2", "p1", "e4");
		driver.addEdge("sim1", "p1", "c3", "e5");
		driver.addEdge("sim1", "c3", "p3", "e6");

		Transaction tx1 = new Transaction("Gummy", 250);
		Transaction tx2 = new Transaction("pretzels", 400);
		Transaction tx3 = new Transaction("sugar", 100);
		Transaction tx4 = new Transaction("sugar", 200);

		driver.sendTransaction("sim1", "c2", tx1);
		driver.sendTransaction("sim1", "c2", tx2);
		driver.sendTransaction("sim1", "c2", tx3);
		driver.sendTransaction("sim1", "c2", tx4);

		assertEquals("Error Wrong p1 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
		assertEquals("Error Wrong p1 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
		assertEquals("Error Wrong p2 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p2"));
		assertEquals("Error Wrong p2 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
		assertEquals("Error Wrong p3 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p3"));
		assertEquals("Error Wrong p3 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p3"));
		assertEquals("Error Wrong c1 amount supply", "", driver.listContents("sim1", "c1"));
		assertEquals("Error Wrong c2 amount supply", "250 400 100 200", driver.listContents("sim1", "c2"));
		assertEquals("Error Wrong c3 amount supply", "", driver.listContents("sim1", "c3"));
        //first run
		driver.simulate("sim1");

		assertEquals("Error Wrong p1 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
		assertEquals("Error Wrong p1 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
		assertEquals("Error Wrong p2 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p2"));
		assertEquals("Error Wrong p2 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
		assertEquals("Error Wrong p3 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p3"));
		assertEquals("Error Wrong p3 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p3"));
		assertEquals("Error Wrong c1 amount supply", "250", driver.listContents("sim1", "c1"));
		assertEquals("Error Wrong c2 amount supply", "400 100 200", driver.listContents("sim1", "c2"));
		assertEquals("Error Wrong c3 amount supply", "", driver.listContents("sim1", "c3"));

		//second run
		driver.simulate("sim1");

		assertEquals("Error Wrong p1 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
		assertEquals("Error Wrong p1 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
		assertEquals("Error Wrong p2 storage amount", 250, driver.getParticipantStorageAmount("sim1", "p2"));
		assertEquals("Error Wrong p2 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
		assertEquals("Error Wrong p3 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p3"));
		assertEquals("Error Wrong p3 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p3"));
		assertEquals("Error Wrong c1 amount supply", "400", driver.listContents("sim1", "c1"));
		assertEquals("Error Wrong c2 amount supply", "100 200", driver.listContents("sim1", "c2"));
		assertEquals("Error Wrong c3 amount supply", "", driver.listContents("sim1", "c3"));

		//third run
		driver.simulate("sim1");

		assertEquals("Error Wrong p1 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p1"));
		assertEquals("Error Wrong p1 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p1"));
		assertEquals("Error Wrong p2 storage amount", 250, driver.getParticipantStorageAmount("sim1", "p2"));
		assertEquals("Error Wrong p2 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p2"));
		assertEquals("Error Wrong p3 storage amount", 0, driver.getParticipantStorageAmount("sim1", "p3"));
		assertEquals("Error Wrong p3 recycle amount", 0, driver.getParticipantToRecycleAmount("sim1", "p3"));
		assertEquals("Error Wrong c1 amount supply", "100", driver.listContents("sim1", "c1"));
		assertEquals("Error Wrong c2 amount supply", "200 400", driver.listContents("sim1", "c2"));
		assertEquals("Error Wrong c3 amount supply", "", driver.listContents("sim1", "c3"));
		

	}

    //  TODO: Add black-box tests
    
  
}
