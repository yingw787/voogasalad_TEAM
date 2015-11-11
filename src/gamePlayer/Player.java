package gamePlayer;

import java.util.List;
import java.util.ResourceBundle;

import interfaces.IPlayer;
import interfaces.Unit;
import javafx.application.Application;
import javafx.stage.Stage;
import units.PlayerInfo;

public class Player extends Application implements IPlayer {
	private ResourceBundle myDefaults = ResourceBundle.getBundle("resources/Default");
	private View myView;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setWidth(Integer.parseInt(myDefaults.getString("Width")));
		stage.setHeight(Integer.parseInt(myDefaults.getString("Height")));
		myView = new View(stage);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void populate(List<Unit> store) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMap(List<Unit> units) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserInfo(PlayerInfo player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showWin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showLose() {
		// TODO Auto-generated method stub
		
	}
}
