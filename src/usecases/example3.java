package usecases;
import java.util.ArrayList;

import interfaces.Unit;

public class example3 {
	/*User can define rounds/levels with different/modified enemies; 
	 * Player will see pauses between them and be able to choose when to 
	 * advance to the next round/level
	 */
	
	private Engine gameEngine;
	private PlayerObject gamePlayer;
	
	private void implementation(){
		gameEngine = new Engine();
		gamePlayer = new PlayerObject();
		
		/*The user can define the content of different waves in the Authoring 
		 * Environment. When the game is actually run, the front end will have a 
		 * "next wave" or "start" button that can be pressed in order to 
		 * indicate to the back end that a new wave needs to be loaded.
		 *Pressing this button makes the Player first call the Engine to 
		 *indicate to them that a new wave can start being run. 
		 */
		int wave = 0;
		gameEngine.startWave(wave);
		
		/*Afterwards, the Engine will begin to load new enemies, which are 
		 * displayed in the front end through the update method.
		 */
		gamePlayer.updateMap(new ArrayList<Unit>());
	}
	
	
}
