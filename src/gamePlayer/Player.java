package gamePlayer;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import controller.Controller;
import interfaces.IPlayer;
import javafx.stage.Stage;
import units.PlayerInfo;
import units.Unit;

public class Player implements IPlayer {
	private ResourceBundle myDefaults = ResourceBundle.getBundle("resources/Default");
	private View myView;
	private Controller myController;

	public Player(Controller controller, Stage s) {
		this.myController = controller;
		initialize(s);
	}
	
	public void initialize(Stage stage) {
		stage.setWidth(Integer.parseInt(myDefaults.getString("Width")));
		stage.setHeight(Integer.parseInt(myDefaults.getString("Height")));
		myView = new View(stage, myController);
		stage.show();
	}
	

	@Override
	public void updateMap(List<Unit> units) {
		myView.updateMap(units);
		
	}

	@Override
	public void updateUserInfo(PlayerInfo player) {
		myView.updateUserInfo(player);
	}

	@Override
	public void showWin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showLose() {
		// TODO Auto-generated method stub
		
	}
	
	public void startWave(int i){
		myController.startWave(i);
	}

	@Override
	public void populate(HashMap<String, List<Unit>> store) {
		myView.populateStore(store);
		
	}
	
}
