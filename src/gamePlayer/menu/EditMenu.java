package gamePlayer.menu;

import gamePlayer.Map;
import javafx.scene.control.Menu;

public class EditMenu extends Menu{
	private Map myMap;
	
	public EditMenu(Map m){
		super("Edit");
		this.myMap= m;
		myMap.initialize();
		addViews();
	}

	private void addViews() {
//		MenuItem addBackground = new MenuItem("Add Background Map");
//		addBackground.setOnAction(e -> uploadMap());
//		this.getItems().addAll(addBackground);
	}
}


