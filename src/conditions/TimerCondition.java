// This entire file is part of my masterpiece.
// cw272

package conditions;

import gameEngine.environments.RuntimeEnvironment;
import units.Unit;

public class TimerCondition implements ICondition{
	private int myInterval;
	private long myCount;
	
	public TimerCondition(int delay){
		myInterval = delay;
		myCount = 0;
	}
	
	/**
	 * TODO
	 * I'm not sure if this is how timeline works, but....
	 * Make instance variable for storing a reference to the timeline
	 * Make set method to set the timeline instance variable
	 * Fill in checkCondition so that it checks if current time % myInterval == 0    
	 * - this kinda works because myInterval will generally be very small
	 */
	
	
	@Override
	public boolean checkCondition(Unit actor,RuntimeEnvironment re) {
		// TODO Auto-generated method stub
		myCount++;
		if(myCount == myInterval)
			myCount =0;
		return myCount % myInterval == 0;
	}
	
	public TimerCondition clone(){
		TimerCondition tc = new TimerCondition(myInterval);
		return tc;
		
	}

}
