package gameEngineTests;

import junit.framework.TestCase;
import org.junit.*;

import gameEngine.MapManager; 

public class MapManagerTest extends TestCase {

	private MapManager mapManager = null; 
	
	@Before 
	public void setUp(){
		mapManager = new MapManager(); 
	}
	
	@Test 
	public void testAddedXMLCorrectly(){
		// some functions that use MapManager 
		// assertEquals(); look over stuff in lab_debuggingderby 
	}
	
}
