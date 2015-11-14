package controller;

import java.util.HashMap;
import java.util.List;

import gameEngine.Engine;
import gamePlayer.Player;
import javafx.application.Application;
import javafx.stage.Stage;
import units.PlayerInfo;
import units.Unit;

public class Controller extends Application {
	private Player myPlayer;
	private Engine myEngine;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		myEngine = new Engine(this);
		myPlayer = new Player(this, primaryStage);
		myEngine.testCaseMaker();
	}

	public void populateStore(HashMap<String, List<Unit>> myTestMap) {
		myPlayer.populate(myTestMap);
	}

	public void updateMap(List<Unit> mapUnits) {
		myPlayer.updateMap(mapUnits);
	}

	public void updateUserInfo(PlayerInfo playerinfo) {
		myPlayer.updateUserInfo(playerinfo);
	}
}




