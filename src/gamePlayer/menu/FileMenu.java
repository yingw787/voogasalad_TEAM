// This entire file is part of my masterpiece.
// Abhishek Upadhyaya Ghimire

package gamePlayer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import startup.PlayerStartup;

public class FileMenu extends Menu{

	private Stage myStage;

	public FileMenu(Stage stage){
		super("File");
		myStage = stage;
		addViews();
	}

	private void addViews() {
		MenuItem openFile = new MenuItem("Open Game");
		openFile.setOnAction(e -> openGame());
		this.getItems().addAll(openFile);
	}

	private void openGame() {
		myStage.setScene(new PlayerStartup(myStage).getScene());
	}

}
