package usecases;

import java.util.ArrayList;

import interfaces.Unit;
import interfaces.UserInfoStatus;

public class example5 {
	/*User can see health of main base and enemies
	 */
	
	private Engine gameEngine;
	private Player gamePlayer;
	
	private void implementation(){
		gameEngine = new Engine();
		gamePlayer = new Player();
		
		/*The health of the enemies is implicit to the update function 
		 * in the front end, as each individual enemy holds its own health which will
		 * be accesed by a getter function for the Player to display graphically.
		 */
		gamePlayer.updateMap(new ArrayList<Unit>());
		
		/*the health of the actual user is displayed through a separate 
		 *method in the Player called by the Engine. This passes the Player relevant 
		 *information in a wrapper class to be updated in the GUI.
		 */
		gamePlayer.updateUserInfo(new UserInfoStatus());
		
	}
		
}
