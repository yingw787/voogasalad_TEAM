package gameEngine.requests;

import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
import interfaces.IRequest;

/**
 * request abstract class
 * @author Wanning
 *
 */
public abstract class Request implements IRequest {
	public Request(){};
	
	public abstract void execute(RuntimeEnvironment re,Controller myController);
	
	
    protected void update(RuntimeEnvironment re,Controller myController){
    	myController.resetStore();
		myController.updateUserInfo(re.getPlayerInfo());
    }
}
