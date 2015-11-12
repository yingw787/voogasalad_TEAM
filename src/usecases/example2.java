package usecases;
import java.util.ArrayList;

import interfaces.Request;
import interfaces.Unit;

/*Player can buy his own troops to send out; they travel backward on the path and collide with enemy troops*/

public class example2 {
	private EngineObject gameEngine;
	private PlayerObject gamePlayer;
	
	private void implementation(){
		gameEngine = new EngineObject();
		gamePlayer = new PlayerObject();
	
		/*The player interacts with the GUI to purchase a new troop. The Player then sends a Request to the Engine
		 * detailing the type of Troop*/  
		gameEngine.update(new ArrayList<Request>());
		
		/*If the player can afford the troop, the Engine then creates the object and updates the front end
		 * with a list of all the units, including this new troop*/
//		gamePlayer.updateMap(new ArrayList<Unit>());
	}
}
