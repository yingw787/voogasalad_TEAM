package gameEngine.requests;

import gameEngine.environments.RuntimeEnvironment;
import interfaces.IRequest;

/**
 * request abstract class
 * @author Wanning
 *
 */
public abstract class Request implements IRequest {
	public Request(){};
	
	public abstract void execute(RuntimeEnvironment re);
}
