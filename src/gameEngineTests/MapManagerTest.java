package gameEngineTests;

import junit.framework.TestCase;
import org.junit.*;

import gameEngine.MapManager; 

/**
 * 
 * MapManagerTest.java is the tester class for MapManager.java. 
 * MapManagerTest.java should hold all of the tests that allow for consistent TDD of MapManager.java. 
 * 
 */
public class MapManagerTest extends TestCase {

	private MapManager mapManager = null; 
	
	@Before 
	public void setUp(){
//		mapManager = new MapManager(); 
	}
	
	@Test 
	/**
	 * testing whether the XML is being parsed correctly into the MapManager. 
	 */
	public void testAddedXMLCorrectly(){
		// some functions that use MapManager 
		// assertEquals(); look over stuff in lab_debuggingderby 
	}
	
}
