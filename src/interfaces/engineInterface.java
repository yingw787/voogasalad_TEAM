package interfaces;

public interface engineInterface {

	public void placeTower(Request request);
	/*Game Player sends a request with the type of tower and a location on the map. If the placement is legal and the player 
	 * has enough money then the Engine creates the tower at the location and updates the front end, which will then display 
	 * the new tower for the player to see and the new gold count.*/
	
	public void buyTroop(Request request);
	/*Game Player sends a request to the Game Engine with the type of unit and the back end verifies that the user can afford 
	 * the unit. If so, then Engine creates the unit and updates the front end's gold and the units on the map. 
	 */
	
	public void loadNewGame(String title);
	/*Game Player gets a request to load a new game. Player passes the Engine a string that leads to a new XML
	 * file to load into the engine. The Engine then resets the Player by sending new information on the back end
	 * and objects to populate the front end GUI with. 
	 */
}

