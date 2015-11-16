package gamePlayer;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import units.Unit;

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
	
	public ProgressBar getHealth(){
		return myHealth;
	}
	
	public Unit getUnit(){
		return myUnit;
	}

}
