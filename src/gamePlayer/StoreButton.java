package gamePlayer;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import units.Unit;

public class StoreButton extends ToggleButton {
	private Unit myUnit;
	
	public StoreButton(String string, ImageView imageview, Unit u) {
		super(string, imageview);
		this.myUnit = u;
	}
	
	public Unit getUnit(){
		return myUnit;
	}

}
