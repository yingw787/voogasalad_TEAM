package units;

public class Tower extends Unit {

	public Tower(String name, double health, double cd, String img, 
			Point p, int ID, int bc, int sc){
		super(name, health, cd, img, p, ID, bc, sc);
		myStringAttributes.put("Type", "Tower");
	}
	
	
}


