package gamePlayer;

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
		MenuItem savePrefs = new MenuItem("Save Game");
		savePrefs.setOnAction(e -> saveGame());
		this.getItems().addAll(openFile, savePrefs);
	}

	private void openGame() {
		myStage.setScene(new PlayerStartup(myStage).getScene());
	}

	private void saveGame() {
		//TODO: implement saving current game from Player
	}

}
