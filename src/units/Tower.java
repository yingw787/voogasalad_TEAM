package units;

public class Tower extends Unit {

	public Tower(String name,Faction faction , double health, double cd, String img, 
			Point p, int bc, int sc){
		super(name, UnitType.Tower, faction,health, cd, img, p, bc, sc);
	}
	
	
}


