package interfaces;

import interfaces.Request;

public interface engineInterface {

	public List<Entry<ID, Units> > readAllUnits();
	/* Game Player refresh the front end scenery, so it will read the units and display them.
	 */

	public void update(List<Event>);
	/* Game Player create a list of events to record what have happened during the previous duration.
	 * Game Engine get the list of events and then run the corresponding logics.
	 */
	
	public void loadNewGame(String title);
	/*Game Player gets a request to load a new game. Player passes the Engine a string that leads to a new XML
	 * file to load into the engine. The Engine then resets the Player by sending new information on the back end
	 * and objects to populate the front end GUI with. 
	 */
	
	public void saveGame();
	/*If the user chooses to save their current game state by interacting with the GUI, the Game Player then notifies
	 * the Game Engine, which then goes and saves the data into an XML and places it into game data as a save file.
	 */
}

