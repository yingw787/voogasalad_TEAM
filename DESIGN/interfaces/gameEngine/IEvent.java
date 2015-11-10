package interfaces;

import interfaces.Request;

public interface eventInterface {

	public Event();
	/* Game Player create a list of events to record what have happened during the previous duration.
	 * Game Engine get the list of events and then run the corresponding logics.
	 */
}

public class BuyTower implements eventInterface {
	public void setTowerType();
	/* An example of specific event
	 */
}

