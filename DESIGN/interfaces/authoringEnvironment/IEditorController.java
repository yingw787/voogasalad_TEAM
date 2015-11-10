package voogasalad_TEAM;

public interface IEditorController {
	
	/* Collects user input and created units to create and save XML file in game data
	 * Returns true if the editor state was saved
	 */
	public boolean saveGame();
	
	/* Retrieves and reads game file from game data 
	 * Fills in values of editor with contained inputs 
	 */
	public boolean openGame();
	
}
