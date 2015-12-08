package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gameEngine.requests.CollisionRequest;
import interfaces.IRequest;

public class CollisionHandler {
	private Map myMap;
	private HashMap<Double, MapUnit> myImageMap;

	public CollisionHandler(Map m){
		myMap = m;
	}
	
	public void checkCollisions(HashMap<Double, MapUnit> im) {
		this.myImageMap = im;
		outerloop: {
			for (MapUnit unit1 : myImageMap.values()) {
				for (MapUnit unit2 : myImageMap.values()) {
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
