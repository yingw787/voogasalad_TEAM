package units;

public class Unit {
	protected String myName;
	protected double myHealth;
	protected double myCollisionDamage;
	protected String myImage;
//	protected Dimension myDimensions;
	protected Point myPosition;
	protected int myID;
	protected int myBuyCost;
	protected int mySellCost;
	
	public Unit(String name, double health, double cd, String img, 
			Point p, int ID, int bc, int sc){
		this.myName = name;
		this.myHealth = health;
		this.myCollisionDamage = cd;
		this.myImage = img;
		this.myPosition = p;
		this.myID = ID;
		this.myBuyCost = bc;
		this.mySellCost = sc;
	}
	
	
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


	public int getID(){
		return myID;
	}


	public Point getPoint() {
		// TODO Auto-generated method stub
		return myPosition;
	}
	
	public void setPoint(Point p){
		myPosition = p;
	}

}
