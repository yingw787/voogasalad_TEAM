package units;

public class Troop extends Unit {
	
	public Troop(String name, double health, double cd, String img, 
			Point p, int ID, int bc, int sc){
		super(name, health, cd, img, p, ID, bc, sc);
		myStringAttributes.put("Type", "Troop");
	}
	
	public Troop(){
		super();
	}
}
