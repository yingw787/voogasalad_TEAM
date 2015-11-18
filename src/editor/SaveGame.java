package editor;

import java.util.ArrayList;
import java.util.List;

import editor.tabData.DataController;
import javafx.scene.control.Button;
import units.Bullet;
import units.Game;
import units.Level;
import units.Path;
import units.Tower;
import units.Troop;

public class SaveGame {

	Button mySaveButton;
	DataController myDataController;
	
	public SaveGame(DataController data) {
		myDataController = data;
		mySaveButton = new Button("Save Current Game");
		mySaveButton.setOnAction(e-> saveData());
	}
	
	@SuppressWarnings({ "unchecked" })
	private void saveData() {
		List<Object> myTowers = myDataController.getData("Towers").getData();
		List<Object> myTroops = myDataController.getData("Troops").getData();
		List<Object> myBullets = myDataController.getData("Bullets").getData();
		List<Object> myPaths = myDataController.getData("Paths").getData();
		List<Object> myLevels = myDataController.getData("Levels").getData();
		List<Object> myGame = myDataController.getData("Game").getData();
		
		System.out.println(myTowers);
		System.out.println(myTroops);
		System.out.println(myBullets);
		System.out.println(myPaths);
		System.out.println(myLevels);
		System.out.println(myGame);
	}
	
	public Button getSaveButton() {
		return mySaveButton;
	}
	
}
