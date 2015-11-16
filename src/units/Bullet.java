package units;

public class Bullet  extends Unit{

	
	public Bullet(String name,Faction faction, double cd, String img, 
			Point p){
		super(name,UnitType.Bullet,faction,1,cd,img,p,0,0);
	}
	
	//Insert method to check collision condition and apply damage
	
}
