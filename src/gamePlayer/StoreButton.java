package gamePlayer;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import units.Unit;

/*A button that extends ToggleButton that holds a corresponding unit
 * 
 */
public class StoreButton extends ToggleButton {
	private Unit myUnit;
	
	public StoreButton(String string, ImageView imageview, Unit u) {
		super(string, imageview);
		this.myUnit = u;
	}
	
	/*Returns the unit held in the Button
	 */
	public Unit getUnit(){
		return myUnit;
	}

}
