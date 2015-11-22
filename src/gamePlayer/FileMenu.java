package gamePlayer;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileMenu extends Menu{
	public FileMenu(){
		super("File");
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
		System.out.println("New File");
	}

	private void saveGame() {
		System.out.println("Save");
	}

}
