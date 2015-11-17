package units;

public class Bullet extends Unit{

	public Bullet(String name, double health, double cd, String img, 
			Point p, int ID, int bc, int sc){
		super(name, health, cd, img, p, ID, bc, sc);
	//Insert method to check collision condition and apply damage
	
	}
	

	public Bullet(){
		super();
	}
	

	public Bullet(Bullet b){
		super(b.getStringAttribute("Name"),b.getAttribute("Health"), b.getAttribute("CollisionDamage"),b.getStringAttribute("Image"),
			new Point(0,0), (int) b.getAttribute("ID"), (int) b.getAttribute("BuyCost"), (int) b.getAttribute("SellCost"));
	}
}
