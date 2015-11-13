package conditions;

import units.Unit;

public class TimerCondition implements ICondition{
	private double myInterval;
	
	public TimerCondition(double delay){
		myInterval = delay;
	}
	
	/**
	 * TODO
	 * I'm not sure if this is how timeline works, but....
	 * Make instance variable for storing a reference to the timeline
	 * Make set method to set the timeline instance variable
	 * Fill in checkCondition so that it checks if current time % myInterval == 0     <= this kinda works because myInterval will generally be very small
	 */
	
	
	@Override
	public boolean checkCondition(Unit actor) {
		// TODO Auto-generated method stub
		return false;
	}

}
