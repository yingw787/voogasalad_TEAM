package usecases;


/*User can open games repeatedly without quitting (launched from a file menu
 * at the top of the GUI) 
 */

public class example1 {
	private Engine gameEngine;
	private PlayerObject gamePlayer;
	
	private void implementation(){
		gameEngine = new Engine();
		gamePlayer = new PlayerObject();
	
		/*The player interacts with a file menu dropdown populated with a list of games that are already completed
		If the player selects a new game to load, including the current game playing, the Player passes the string 
		address of that game to the Engine. The Engine then reloads the back end and then updates the Player
		to represent this. */  
		
		String title = ""; //obtained in gamePlayer
		gameEngine.loadNewGame(title);
		
	}
	
}
