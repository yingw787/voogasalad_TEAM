package units;

import java.util.ResourceBundle;

import interfaces.IMovable;

public class Troop extends Unit implements IMovable 
{
	private static final String DEFAULTS_FILE = "resources/DefaultTroop.properties";
	protected static final String DEFAULT_RESOURCE = "resources.DefaultTroop";
	ResourceBundle myResource = ResourceBundle.getBundle(DEFAULT_RESOURCE);
	/**  Constructor for Troop object
	 *   @params Attributes of Troop object
	 **/
	public Troop(String name, double health, double cd, String img, 
			Point p, int ID, int bc, int sc){
		super(name, health, cd, img, p, ID, bc, sc);
		myStringAttributes.put("Type", "Troop");
		addMovableAttributes(Double.parseDouble(myResource.getString("Speed")));
	}
	
	/**  Constructor for default Troop object
	 *   @params Attributes of default Troop object
	 **/
	public Troop(){
		super(DEFAULTS_FILE);
		myStringAttributes.put("Name", "");
//		myAttributes.put("Health", 20.0);
//		myAttributes.put("CollisionDamage", 20.0);
		myStringAttributes.put("Image", "");
//		myAttributes.put("X", 0.0);
//		myAttributes.put("Y", 0.0);
//		myAttributes.put("ID", 0.0);
//		myAttributes.put("BuyCost", 100.0);
//		myAttributes.put("SellCost", 50.0);
		myStringAttributes.put("Type", "Troop");
		myAttributes.put("KillRewards", 10.0);
		addMovableAttributes(Double.parseDouble(myResource.getString("Speed")));
	}
	
	/**  Constructor for Troop object clone
	 *   @param Troop Troop object to clone
	 **/
	public Troop(Troop t){
		super(t.getStringAttribute("Name"),t.getAttribute("Health"), t.getAttribute("CollisionDamage"),t.getStringAttribute("Image"),
			new Point(0,0), (int) t.getAttribute("ID"), (int) t.getAttribute("BuyCost"), (int) t.getAttribute("KillReward"));
		myStringAttributes.put("Type", "Troop");
		addMovableAttributes(Double.parseDouble(myResource.getString("Speed")));
	}

	@Override
	public int addMovableAttributes(double x) {
		myAttributes.put("Speed", x);
		return 0;
	}
}
