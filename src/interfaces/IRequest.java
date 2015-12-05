package interfaces;

import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;

public interface IRequest {
	public void execute(RuntimeEnvironment re, Controller myController);
}
