package gamePlayer;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;

public class StoreButton extends ToggleButton {
	public StoreButton(String string, ImageView imageview) {
		super(string, imageview);
	}
	
	private int myBuyCost;
	private String myName;
	private int myDamage;

}
