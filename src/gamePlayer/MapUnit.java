package gamePlayer;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import units.Unit;


/*Special unit for the Map in the Player that holds a Unit and a corresponding 
 * ProgressBar
 */
public class MapUnit extends ImageView {
	private Unit myUnit;
	private ProgressBar myHealth;
	private Circle myPower;
	
	public MapUnit(Image i, Unit u) {
		super(i);
		this.myUnit = u;
		myHealth = new ProgressBar(1.0);
		myHealth.setMaxWidth(50);
		myHealth.setStyle("-fx-accent: red;");
//		myPower = new Circle();
//		myPower.setFill(Color.RED);
//		myPower.setStroke(Color.RED);
//		myPower.setOpacity(0.5);
//		myPower.setRadius(myUnit.getAttribute("Health"));

	}
	
	/*returns the ProgressBar associated with the image
	 * 
	 */
	public ProgressBar getHealth(){
		return myHealth;
	}
	
//	public Circle getPower(){
//		return myPower;
//	}
	
	/*Returns the unit associated with the image
	 * 
	 */
	public Unit getUnit(){
		return myUnit;
	}

}
