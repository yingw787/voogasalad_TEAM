package usecases;

import java.util.ArrayList;

import interfaces.IRequest;
import units.Unit;
import gameEngine.requests.Request;
public class example4 {
	/*User can drag and drop items onto the board during gameplay
	 */
	
	private EngineObject gameEngine;
	private PlayerObject gamePlayer;
	
	private void implementation(){
		gameEngine = new EngineObject();
		gamePlayer = new PlayerObject();
		
		/*The Player can select a tower and then click on the map. An event handler
		 * will handle for clicks on the map, and in such a case call a method
		 * in the Engine to send it a request that contains information about
		 * what type of tower and the coordinates of where the user wants it to be 
		 * placed.
		 */
		gameEngine.update(new ArrayList<Request>());
		
		/*If the location is legal, the Engine will create the object at the location
		 * and then update the front end with the altered list of Units
		 */
//		gamePlayer.updateMap(new ArrayList<Unit>());
	}
}
