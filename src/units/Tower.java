package units;

public class Tower extends Unit {

	public Tower(String name, double health, double cd, String img, 
			Point p, int ID, int bc, int sc){
		super(name, health, cd, img, p, ID, bc, sc);
		myStringAttributes.put("Type", "Tower");
	}
	
	public Tower(Tower t){
		super(t.getStringAttribute("Name"),t.getAttribute("Health"), t.getAttribute("CollisionDamage"),t.getStringAttribute("Image"),
			new Point(0,0), (int) t.getAttribute("ID"), (int) t.getAttribute("BuyCost"), (int) t.getAttribute("SellCost"));

		myStringAttributes.put("Type", "Tower");
	}

	public Tower() {
		super();
	}
}


