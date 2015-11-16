package units;

public class Bullet extends Unit {
	
	private double myDamage;
	private int myHealth = 1;
	private double myX;
	private double myY;
	private Faction myFraction; 
	
	public Bullet(int dmg, double x, double y){
		myDamage = dmg;
		myX = x;
		myY = y;
	}
	
	//Insert method to check collision condition and apply damage
	
}
