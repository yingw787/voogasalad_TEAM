package editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import editor.tabData.DataController;
import gamedata.xml.XMLConverter;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
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
	
	/**  Constructor for SaveGame object which saves current state of editor
	 *   @params DataController DataController to retrieve current state from
	 **/
	public SaveGame(DataController data) {
		myDataController = data;
		mySaveButton = new Button("Save Current Game");
		mySaveButton.setOnAction(e-> saveData());
	}
	
	private void saveData() {
		List<Object> myTowers = myDataController.getData("Towers").getData();
		List<Object> myTroops = myDataController.getData("Troops").getData();
		List<Object> myBullets = myDataController.getData("Bullets").getData();
		List<Object> myPaths = myDataController.getData("Paths").getData();
		List<Object> myLevels = myDataController.getData("Levels").getData();
		List<Object> myGame = myDataController.getData("Game").getData();
		List<Object> myPlayerInfo = myDataController.getData("Player").getData();
		
		XMLConverter c = new XMLConverter();
		String gameTitle = ((Game) myGame.get(0)).getTitle();
		
		if (gameTitle.equals("") || gameTitle == null) {
			Alert noName = new Alert(AlertType.ERROR);
			noName.setHeaderText("No Name");
			noName.setContentText("You cannot save your game until you give it a name.");
			noName.show();
			return;
		}
		
		for (Object tower : myTowers) {
			try {
				c.toXML(tower, gameTitle, "Tower", ((Tower) tower).getStringAttribute("Name"));
			} catch (IOException e) {
				System.out.println("Cannot convert towers to XML");
			}
		}
		for (Object bullet : myBullets) {
			try {
				c.toXML(bullet, gameTitle, "Bullet", ((Bullet) bullet).getStringAttribute("Name"));
			} catch (IOException e) {
				System.out.println("Cannot convert bullets to XML");
			}
		}
		for (Object troop : myTroops) {
			try {
				c.toXML(troop, gameTitle, "Troop", ((Troop)troop).getStringAttribute("Name"));
			} catch (IOException e) {
				System.out.println("Cannot convert troops to XML");
			}
		}
		for (Object level : myLevels) {
			try {
				c.toXML(level, gameTitle, "Level", ((Level)level).getName());
			} catch (IOException e) {
				System.out.println("Cannot convert levels to XML");
			}
		}
		for (Object game : myGame) {
			try {
				c.toXML(game, gameTitle, "Game", "Game");
			} catch (IOException e) {
				System.out.println("Cannot convert game to XML");
			}
		}
		for (Object path : myPaths) {
			try {
				c.toXML(path, gameTitle, "Path", ((Path) path).getName());
			} catch (IOException e) {
				System.out.println("Cannot convert paths to XML");
			}
		}
		for (Object player : myPlayerInfo) {
			try {
				c.toXML(player, gameTitle, "Player", "Player info");
			} catch (IOException e) {
				System.out.println("Cannot convert player information to XML");
			}
		}
				
		Alert confirmation = new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("Success!");
		confirmation.setHeaderText("Games Created");
		confirmation.setContentText("Game Has Been Created!");
		confirmation.show();
	}
	
	public Button getSaveButton() {
		return mySaveButton;
	}
	
}
