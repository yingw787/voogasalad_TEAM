package gamePlayer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import units.Unit;

public class MapUnit extends ImageView{
	private Unit myUnit;
	
	public MapUnit(Image i, Unit u) {
		super(i);
		this.myUnit = u;
	}
	
	
	public Unit getUnit(){
		return myUnit;
	}

}
