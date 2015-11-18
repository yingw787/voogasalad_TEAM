package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import gameEngine.Engine;
import gameEngine.requests.Request;
import gamePlayer.Player;
import gamedata.xml.XMLConverter;
import interfaces.IRequest;
import javafx.animation.Timeline;
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
		myEngine = new Engine(this, new Timeline());
		myPlayer = new Player(this, primaryStage);
		myEngine.writeEnvironment();
		myEngine.initialize();
	}
	
	// store is in-game purchases of towers and units and the like 
	public void populateStore(HashMap<String, List<Unit>> myStoreStock) {
		myPlayer.populate(myStoreStock);
	}

	public void updateMap(List<Unit> mapUnits) {
		myPlayer.updateMap(mapUnits);
	}

	public void updateUserInfo(PlayerInfo playerinfo) {
		myPlayer.updateUserInfo(playerinfo);
	}
	
	public void startWave(int i){
		myEngine.startWave(i);
	}

	public void update(List<IRequest> requestSender) {
		myEngine.update(requestSender);
		
	}
}




