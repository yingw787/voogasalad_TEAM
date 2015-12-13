package editor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import units.Tower;
import units.Troop;

public class SaveGame {

	private Button mySaveButton;
	private DataController myDataController;
	//private Optional<ButtonType> overwrite;
	private boolean duplicate = false;
	
	/**  Constructor for SaveGame object which saves current state of editor
	 *   @params DataController DataController to retrieve current state from
	 **/
	public SaveGame(DataController data) {
		myDataController = data;
		mySaveButton = new Button("Save Current Game");
		mySaveButton.setOnAction(e-> saveData());
	}
	
	private void saveData() {
		List<List<Object>> myGameObjects = new ArrayList<>();
		List<Object> myTowers = myDataController.getData("Towers").getData();
		List<Object> myTroops = myDataController.getData("Troops").getData();
		List<Object> myBullets = myDataController.getData("Bullets").getData();
		List<Object> myPaths = myDataController.getData("Paths").getData();
		List<Object> myLevels = myDataController.getData("Levels").getData();
		List<Object> myGame = myDataController.getData("Game").getData();
		List<Object> myPlayerInfo = myDataController.getData("Player").getData();
		myGameObjects.addAll(Arrays.asList(myTowers, myTroops, myBullets, myPaths, myLevels, myGame, myPlayerInfo));
		
		XMLConverter c = new XMLConverter();
		String gameTitle = ((Game) myGame.get(0)).getTitle();
		
		if (gameIsInvalid(myGameObjects)) {
			return;
		};
		
		if (gameTitle.equals("") || gameTitle == null) {
			Alert noName = new Alert(AlertType.ERROR);
			noName.setHeaderText("No Name");
			noName.setContentText("You cannot save your game until you give it a name.");
			noName.show();
			return;
		}
		
		File f = new File("games/"+gameTitle);
		if(f.exists()) { 
			Alert warning = new Alert(AlertType.WARNING, 
					"An existing game with name " + gameTitle + " already exists. Overwrite?");
			warning.showAndWait();
		//	overwrite = warning.showAndWait();
			duplicate = true;
		}
		
	//	if (overwrite.get() == ButtonType.OK || duplicate == false) { 
		if (duplicate == true) removeDirectory(f);
		
		for (Object tower : myTowers) {
			try {
				c.toXML(tower, gameTitle, "Tower", ((Tower) tower).getStringAttribute("Name"));
			} catch (IOException e) {
				//ln("Cannot convert towers to XML");
			}
		}
		for (Object bullet : myBullets) {
			try {
				c.toXML(bullet, gameTitle, "Bullet", ((Bullet) bullet).getStringAttribute("Name"));
			} catch (IOException e) {
				//ln("Cannot convert bullets to XML");
			}
		}
		for (Object troop : myTroops) {
			try {
				c.toXML(troop, gameTitle, "Troop", ((Troop)troop).getStringAttribute("Name"));
			} catch (IOException e) {
				//ln("Cannot convert troops to XML");
			}
		}
		for (Object level : myLevels) {
			try {
				c.toXML(level, gameTitle, "Level", ((Level)level).getName());
			} catch (IOException e) {
				//ln("Cannot convert levels to XML");
			}
		}
		for (Object game : myGame) {
			try {
				c.toXML(game, gameTitle, "Game", "Game");
			} catch (IOException e) {
				//ln("Cannot convert game to XML");
			}
		}
		for (Object path : myPaths) {
			try {
				c.toXML(path, gameTitle, "Path", ((Path) path).getName());
			} catch (IOException e) {
				//ln("Cannot convert paths to XML");
			}
		}
		for (Object player : myPlayerInfo) {
			try {
				c.toXML(player, gameTitle, "Player", "Player info");
			} catch (IOException e) {
				//ln("Cannot convert player information to XML");
			}
		}
				
		Alert confirmation = new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("Success!");
		confirmation.setHeaderText("Game Created");
		confirmation.setContentText("Game has been saved!");
		confirmation.show();
		
	//	overwrite = null;
		duplicate = false;
	}
//	}
	
	public Button getSaveButton() {
		return mySaveButton;
	}
	
	public void removeDirectory(File dir) {
	    if (dir.isDirectory()) {
	        File[] files = dir.listFiles();
	        if (files != null && files.length > 0) {
	            for (File aFile : files) {
	                removeDirectory(aFile);
	            }
	        }
	        dir.delete();
	    } else {
	        dir.delete();
	    }
	}
	
	public boolean gameIsInvalid(List<List<Object>> allLists) {
		for (List<Object> list : allLists) {
			if (list.size() == 0) { 
				Alert error = new Alert(AlertType.ERROR, 
						"Your game is missing some components... Make sure all of the tabs are filled in.");
				error.show();
				return true;
			}
		}
		return false;
	}
}
