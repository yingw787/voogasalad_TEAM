package usecases;

public class example5 {
	/*User can see health of main base and enemies
	 */
	
	private EngineObject gameEngine;
	private PlayerObject gamePlayer;
	
	private void implementation(){
		gameEngine = new EngineObject();
		gamePlayer = new PlayerObject();
		
		/*The health of the enemies is implicit to the update function 
		 * in the front end, as each individual enemy holds its own health which will
		 * be accesed by a getter function for the Player to display graphically.
		 */
//		gamePlayer.updateMap(new ArrayList<Unit>());
		
		/*the health of the actual user is displayed through a separate 
		 *method in the Player called by the Engine. This passes the Player relevant 
		 *information in a wrapper class to be updated in the GUI.
		 */
//		gamePlayer.updateUserInfo(new PlayerInfo());
		
	}
		
}
