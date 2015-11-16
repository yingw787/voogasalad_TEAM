package gameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import gameEngine.environments.InitialEnvironment;
import gameEngine.environments.RuntimeEnvironment;
import interfaces.IEngine;
import interfaces.IRequest;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import units.PlayerInfo;
import units.Point;
import units.Tower;
import units.Troop;
import units.Unit;

public class Engine implements IEngine {
	private Controller myController;
	private Timeline myTimeline;
	public static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	private List<Unit> myCurrentUnits;
	private InitialEnvironment myInitialEnviron;
	private RuntimeEnvironment myRuntimeEnviron;
	
	public Engine(Controller controller, Timeline timeline) {
		myController = controller;
		myTimeline = timeline;
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		myInitialEnviron = new InitialEnvironment();
		myRuntimeEnviron = new RuntimeEnvironment();
		XMLParser parser = new XMLParser();
		parser.writeEnviroment(myInitialEnviron);
		myInitialEnviron = parser.readEnvironment();
		
	}
	
	public void playAnimation(boolean on){
		if (on){
			KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
					e -> step());
			myTimeline.setCycleCount(Timeline.INDEFINITE);
			myTimeline.getKeyFrames().addAll(frame);
			myTimeline.play();
		}
	}
	
	
	private void step(){
		for (Unit unit : myCurrentUnits) {
			//testing animation
			Point newPoint = new Point(unit.getPoint().getX()+1, unit.getPoint().getY());
			unit.setPoint(newPoint);
			unit.setHealth(unit.getHealth()-0.5);
		}
		//why bullet doesn't extends unit?
		//bullet should have a member, true represent friend, false represent enemy, it's set by the tower/zombie
		//
		myController.updateMap(myCurrentUnits);
	}


	
	@Override
	public void update(List<IRequest> requests) {
		// TODO Auto-generated method stub
		// request if a CollisionRequest
		
	}

	@Override
	public void loadNewGame(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startWave(int i) {
		// TODO release a wave of zombies
		
	}

	
	
	public void testCaseMaker(){
		PlayerInfo playerinfo = new PlayerInfo(200, 3, 1);
		myController.updateUserInfo(playerinfo);
		HashMap<String, List<Unit>> myTestMap = new HashMap<String, List<Unit>>();
		List<Unit> TowerList = new ArrayList<Unit>();

		myTestMap.put("Towers", TowerList);
		List<Unit> TroopList = new ArrayList<Unit>();

		myTestMap.put("Troops", TroopList);
		myController.populateStore(myTestMap);
		List<Unit> mapUnits = new ArrayList<Unit>();
		mapUnits.addAll(TroopList);
		mapUnits.addAll(TowerList);
		myCurrentUnits = mapUnits;
		myController.updateMap(mapUnits);
	}
	
	public static void main(String[] args){
		Engine e = new Engine(null,new Timeline());
	}
}
