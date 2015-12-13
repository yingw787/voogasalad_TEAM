// This entire file is part of my masterpiece.
// Vanessa Wu

package gamePlayer;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import units.Unit;


/*Special unit for the Map in the Player that holds a Unit and a corresponding 
 * ProgressBar
 */
public class MapUnit extends ImageView {
	private Unit myUnit;
	private ProgressBar myHealth;
	
	public MapUnit(Image i, Unit u) {
		super(i);
		this.myUnit = u;
		myHealth = new ProgressBar(1.0);
		myHealth.setMaxWidth(50);
		myHealth.setStyle("-fx-accent: red;");

	}
	
	/*returns the ProgressBar associated with the image
	 * 
	 */
	public ProgressBar getHealth(){
		return myHealth;
	}

	/*Returns the unit associated with the image
	 * 
	 */
	public Unit getUnit(){
		return myUnit;
	}

}
