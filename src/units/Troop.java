package units;

public class Troop extends Unit {
	
	public Troop(String name, double health, double cd, String img, 
			Point p, int ID, int bc, int sc){
		super(name, health, cd, img, p, ID, bc, sc);
		myStringAttributes.put("Type", "Troop");
	}
	
	public Troop(){
		super();
		myStringAttributes.put("Name", "");
		myAttributes.put("Health", 0.0);
		myAttributes.put("CollisionDamage", 0.0);
		myStringAttributes.put("Image", "");
		myAttributes.put("X", 0.0);
		myAttributes.put("Y", 0.0);
		myAttributes.put("ID", 0.0);
		myAttributes.put("BuyCost", 0.0);
		myAttributes.put("SellCost", 0.0);
		myStringAttributes.put("Type", "Troop");
	}
	
	public Troop(Troop t){
		super(t.getStringAttribute("Name"),t.getAttribute("Health"), t.getAttribute("CollisionDamage"),t.getStringAttribute("Image"),
			new Point(0,0), (int) t.getAttribute("ID"), (int) t.getAttribute("BuyCost"), (int) t.getAttribute("SellCost"));
		myStringAttributes.put("Type", "Troop");
	}
}
