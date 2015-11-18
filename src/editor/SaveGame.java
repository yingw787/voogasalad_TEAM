package editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import editor.tabData.DataController;
import gamedata.xml.XMLConverter;
import javafx.scene.control.Button;
import units.Bullet;
import units.Game;
import units.Level;
import units.Path;
import units.PlayerInfo;
import units.Tower;
import units.Troop;
import units.Unit;

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
		
		XMLConverter c = new XMLConverter();
		String gameTitle = ((Game) myGame.get(0)).getTitle();
		for (Object tower : myTowers) {
			try {
				c.toXML(tower, gameTitle, "Tower", ((Tower) tower).getStringAttribute("Name"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (Object troop : myTroops) {
			try {
				c.toXML(troop, gameTitle, "Troop", ((Troop)troop).getStringAttribute("Name"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (Object level : myLevels) {
			try {
				c.toXML(level, gameTitle, "Level", ((Level)level).getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (Object game : myGame) {
			try {
				c.toXML(game, gameTitle, "Game", gameTitle);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (Object path : myPaths) {
			try {
				c.toXML(path, gameTitle, "Path", ((Path) path).getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Button getSaveButton() {
		return mySaveButton;
	}
	
}
