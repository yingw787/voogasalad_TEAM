package units;

public class Tower extends Unit {

	/**  Constructor for Tower object
	 *   @params Attributes of Tower object
	 **/
	public Tower(String name, double health, double cd, String img, 
			Point p, int ID, int bc, int sc){
		super(name, health, cd, img, p, ID, bc, sc);
		myStringAttributes.put("Type", "Tower");
	}
	
	/**  Constructor for Tower object cloning
	 *   @param Tower Tower to clone
	 **/
	public Tower(Tower t){
		super(t.getStringAttribute("Name"),t.getAttribute("Health"), t.getAttribute("CollisionDamage"),t.getStringAttribute("Image"),
			new Point(0,0), (int) t.getAttribute("ID"), (int) t.getAttribute("BuyCost"), (int) t.getAttribute("SellCost"));

		myStringAttributes.put("Type", "Tower");
	}
	
	public Tower(Unit u) {
		super(u);
	}

	/**  Constructor for default Tower object
	 *   @params Attributes of default Tower object
	 **/
	public Tower() { 
		super();
		myStringAttributes.put("Name", "");
		myAttributes.put("Health", 100.0);
		myAttributes.put("CollisionDamage", 0.0);
		myStringAttributes.put("Image", "");
		myAttributes.put("X", 0.0);
		myAttributes.put("Y", 0.0);
		myAttributes.put("ID", 0.0);
		myAttributes.put("BuyCost", 200.0);
		myAttributes.put("SellCost", 0.0);
		myStringAttributes.put("Type", "Tower");
	}
}


