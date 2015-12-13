// This entire file is part of my masterpiece.
// Vanessa Wu

package gamePlayer;

import java.util.ArrayList;
import java.util.List;

import gameEngine.requests.CollisionRequest;
import interfaces.IRequest;

public class CollisionHandler {
	private Map myMap;

	public CollisionHandler(Map m){
		myMap = m;
	}
	
	public void checkCollisions(MapHolder mapHolder) {
		outerloop: {
			for (MapUnit unit1 : mapHolder.getImageMap().values()) {
				for (MapUnit unit2 : mapHolder.getImageMap().values()) {
					if ((unit1.equals(unit2)) || (unit1.getUnit().getStringAttribute("Type")
							.equals(unit2.getUnit().getStringAttribute("Type")))) {
						continue;
					} else {
						if (unit1.getBoundsInLocal().intersects(unit2.getBoundsInLocal())) {
							CollisionRequest cr = new CollisionRequest(unit1.getUnit(), unit2.getUnit());
							List<IRequest> requestSender = new ArrayList<IRequest>();
							requestSender.add(cr);
							myMap.sendRequest(requestSender);
							break outerloop;
						}
					}
				}
			}
		}

	}
}
