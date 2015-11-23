package startup;

import java.io.File;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class GamesMenu extends FlowPane {
	private SimpleStringProperty selected;
	private Label testLabel;

	public GamesMenu() {
		super();
		this.setHgap(10);
		this.setVgap(10);
		selected = new SimpleStringProperty();
		File gameFolder = new File("games");
		for (File game : gameFolder.listFiles()) {
			this.getChildren().add(new GameItem(game.getName()));
		}
	}
	
	private class GameItem extends Button {
		GameItem(String name) {
			super();
			ImageView myIcon = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("purpleminion.png")));
			myIcon.setFitHeight(50);
			myIcon.setPreserveRatio(true);
			this.setGraphic(myIcon);
			this.setText(name);
			this.setContentDisplay(ContentDisplay.TOP);
			this.setOnAction(e -> {selected.setValue(name);});
		}
	}
	
	public SimpleStringProperty getSelected() { //maybe return Observable?
		return selected;
	}
	
}
