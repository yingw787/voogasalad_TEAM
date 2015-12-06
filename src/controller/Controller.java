package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import gameEngine.Engine;
import gamePlayer.Player;
import interfaces.IRequest;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import units.Path;
import units.PlayerInfo;
import units.Unit;

public class Controller {
	private Player myPlayer;
	private Engine myEngine;
	private String myGameTitle = "Game 1";
	
	public Controller(String s) throws IOException{
		myGameTitle = s;
		myEngine = new Engine(this, new Timeline());
		myPlayer = new Player(this, new Stage());
		myEngine.writeEnvironment(myGameTitle);
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
	
	public void updateInfo(PlayerInfo playerinfo) {
		myPlayer.updateInfo(playerinfo);
	}
	
	public void startWave(int i){
		myEngine.startWave(i);
	}

	public void update(List<IRequest> requestSender) {
		myEngine.update(requestSender);
		
	}

	public void showPaths(List<Path> pathsForLevel) {
		myPlayer.showPaths(pathsForLevel);
	}

	public void resetStore() {
		myPlayer.resetStore();
	}
	
	public void showWin() {
		myPlayer.showWin();
	}

	public void showLose() {
		myPlayer.showLose();
	}
}




