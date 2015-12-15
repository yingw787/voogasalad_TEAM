// This entire file is part of my masterpiece.
// cw272

package conditions;

import gameEngine.environments.RuntimeEnvironment;
import units.Unit;

public interface ICondition {
	public boolean checkCondition(Unit actor,RuntimeEnvironment re);

	public ICondition clone();

}
