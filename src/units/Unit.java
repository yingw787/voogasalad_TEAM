package units;

public class Unit {
	protected String myName;
	protected double myHealth;
	protected double myCollisionDamage;
	protected String myImage;
	protected Dimension myDimensions;
	protected Point myPosition;
	protected int myID;
	protected int myBuyCost;
	protected int mySellCost;
	
	public String getImage() {
		return myImage;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return myName;
	}

	public int getCost() {
		// TODO Auto-generated method stub
		return myBuyCost;
	}
}
