package conditions;

import gameEngine.environments.RuntimeEnvironment;
import units.Unit;

public interface ICondition {
	public boolean checkCondition(Unit actor,RuntimeEnvironment re);
}
