package gameEngine.requests;

import gameEngine.Engine;
import units.*;

/**
 * request for collision
 * @author Wanning
 *
 */
public class CollisionRequest extends Request {
	private Unit myUnit1 ;
	private Unit myUnit2;
	
	/**
	 * constructor
	 * @param unit1
	 * @param unit2
	 */
	public CollisionRequest (Unit unit1, Unit unit2) {
		super();
		myUnit1 = unit1;
		myUnit2 = unit2;
	}
	
	/**
	 * execute the collision logic
	 */
	@Override
	public void execute(Engine e) {
		if(myUnit1.getFaction() != myUnit2.getFaction()){
			myUnit1.setAttribute("Health", myUnit1.getAttribute("Health") - myUnit2.getAttribute("CollisionDamage"));
			myUnit2.setAttribute("Health", myUnit2.getAttribute("Health") - myUnit1.getAttribute("CollisionDamage"));

		}
	}
	
}
