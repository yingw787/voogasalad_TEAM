package gamePlayer;

import java.io.File;

import image.ImageMaker;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

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


