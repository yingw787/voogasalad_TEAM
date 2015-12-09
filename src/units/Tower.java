package units;

public class Tower extends Unit {
	private final static String DEFAULTS_FILE = "resources/DefaultTower.properties";
	/**  Constructor for Tower object
	 *   @params Attributes of Tower object
	 **/
	public Tower(String name, double health, double cd, String img, 
			Point p, int ID, int bc, int sc){
		super(name, health, cd, img, p, ID, bc, sc);
		myStringAttributes.put("Type", "Tower");
		myAttributes.put("Range", 0.0);
	}
	
	/**  Constructor for Tower object cloning
	 *   @param Tower Tower to clone
	 **/
	public Tower(Tower t){
		super(t.getStringAttribute("Name"),t.getAttribute("Health"), t.getAttribute("CollisionDamage"),t.getStringAttribute("Image"),
			new Point(0,0), (int) t.getAttribute("ID"), (int) t.getAttribute("BuyCost"), (int) t.getAttribute("SellCost"));

		myStringAttributes.put("Type", "Tower");
		myAttributes.put("Range", 0.0);
	}
	
	public Tower(Unit u) {
		super(u);
		myAttributes.put("Range", 0.0);
	}

	/**  Constructor for default Tower object
	 *   @params Attributes of default Tower object
	 **/
	public Tower() { 
		super(DEFAULTS_FILE);
		myStringAttributes.put("Name", "");
		myStringAttributes.put("Image", "");
		myStringAttributes.put("Type", "Tower");
		myAttributes.put("Range", 0.0);
	}
	
	
	
}


