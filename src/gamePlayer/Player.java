package gamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import interfaces.IPlayer;
import units.Unit;
import javafx.application.Application;
import javafx.stage.Stage;
import units.PlayerInfo;
import units.Tower;
import units.Troop;

public class Player extends Application implements IPlayer {
	private ResourceBundle myDefaults = ResourceBundle.getBundle("resources/Default");
	private View myView;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setWidth(Integer.parseInt(myDefaults.getString("Width")));
		stage.setHeight(Integer.parseInt(myDefaults.getString("Height")));
		myView = new View(stage);
		stage.show();
		testCaseMaker();
	}
	
	private void testCaseMaker(){
		HashMap<String, List<Unit>> myTestMap = new HashMap<String, List<Unit>>();
		List<Unit> TowerList = new ArrayList<Unit>();
		TowerList.add(new Tower("Basic Turret", "turret_transparent.png", 150));
		TowerList.add(new Tower("Basic Turret", "turret_transparent.png", 150));
		TowerList.add(new Tower("Basic Turret", "turret_transparent.png", 150));
		TowerList.add(new Tower("Attack Turret", "turret.png", 250));
		myTestMap.put("Towers", TowerList);
		List<Unit> TroopList = new ArrayList<Unit>();
		TroopList.add(new Troop("Basic Minion", "purpleminion.png", 150));
		TroopList.add(new Troop("Basic Minion", "purpleminion.png", 150));
		TroopList.add(new Troop("Caster Minion", "casterminion.png", 300));
		myTestMap.put("Troops", TroopList);
		populate(myTestMap);
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

	@Override
	public void populate(HashMap<String, List<Unit>> store) {
		myView.populateStore(store);
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
