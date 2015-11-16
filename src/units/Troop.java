package units;

public class Troop extends Unit {
	
	public Troop(String name,Faction faction,double health, double cd, String img, 
			Point p, int bc, int sc){
		super(name, UnitType.Troop, faction,health, cd, img, p, bc, sc);
	}
}
