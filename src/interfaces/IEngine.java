package interfaces;

import java.util.List;

import gameEngine.requests.Request;

public interface IEngine {

	public void update(List<Request> requests);
	/*Game Player sends a request with the type of tower and a location on the map. If the placement is legal and the player 
	 * has enough money then the Engine creates the tower at the location and updates the front end, which will then display 
	 * the new tower for the player to see and the new gold count.*/
	
	public void loadNewGame(String title);
	/*Game Player gets a request to load a new game. Player passes the Engine a string that leads to a new XML
	 * file to load into the engine. The Engine then resets the Player by sending new information on the back end
	 * and objects to populate the front end GUI with. 
	 */
	
	public void saveGame();
	/*If the user chooses to save their current game state by interacting with the GUI, the Game Player then notifies
	 * the Game Engine, which then goes and saves the data into an XML and places it into game data as a save file.
	 */

	public void startWave(int i);
	/*Tells the Engine to begin loading the next wave in the game, specified by 
	 *a number
	 */
}

