package interfaces;
import java.util.List;

public interface playerInterface {
	public void populate(List<Unit> store);
	/*passes the front end a list of all the objects that can be purchased*/
	
	public void updateMap(List<Unit> units);
	/*passes the player all of the objects that exist in the back end to 
	update the front end. This allows the front end to display all the objects
	that exist in the back end*/
	
	public void updateUserInfo(UserInfoStatus uis);
	/*passes the player the user's current information like gold, health, 
	 etc.*/

	public void showWin();
	/*tells the player a win condition has been met*/

	public void showLose();
	/*tells the player a lose condition has been met*/
}
