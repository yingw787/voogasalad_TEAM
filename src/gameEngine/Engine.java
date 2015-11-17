package gameEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.Controller;
import gameEngine.environments.InitialEnvironment;
import gameEngine.environments.RuntimeEnvironment;
import gamedata.xml.XMLConverter;
import interfaces.IEngine;
import interfaces.IRequest;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import units.Level;
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
	
	private HashMap<String, List<Unit>> myPossibleUnits;
	private List<PlayerInfo> myPlayerInfo;
	private List<Level> myLevels;
	private List<Point> myPaths;
	private int myCurrentLevelInt;
	private Level myCurrentLevel;
	private InitialEnvironment myInitialEnviron;
	private RuntimeEnvironment myRuntimeEnviron;
	private ToolbarManager myTBManager;
	private MapManager myMapManager;
	private HUDManager myHUDManager;
	private int delay = 0;
	
	public Engine(Controller controller, Timeline timeline) {
		myController = controller;
		myTimeline = timeline;
		myTimeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	public void readXML() throws IOException{
		XMLConverter myConverter = new XMLConverter();
		List<Unit> towers = myConverter.getUnits("Game 1", "Tower");
		List<Unit> troops = myConverter.getUnits("Game 1", "Troop");
		myPossibleUnits = new HashMap<String,List<Unit>>();
		myPossibleUnits.put("Towers", towers);
		myPossibleUnits.put("Troops", troops);
		myPlayerInfo = myConverter.getPlayerInfo("Game 1");
		myLevels = myConverter.getLevels("Game 1");
		myPaths = new ArrayList<Point>();
		myPaths.add(new Point(0,0));
		myPaths.add(new Point(50,50));
		myPaths.add(new Point(100,50));
		myCurrentLevelInt = 0;
	}
	
	public void initialize(){
		myController.updateUserInfo(myPlayerInfo.get(0));
		myController.populateStore(myPossibleUnits);
		myMapManager = new MapManager(this, myPossibleUnits.get("Troops"), myPaths);
		myHUDManager = new HUDManager(this, myPlayerInfo.get(0));
		myInitialEnviron = new InitialEnvironment();
		myRuntimeEnviron = new RuntimeEnvironment();
	}
	
	public void writeEnvironment() throws IOException{
		myTBManager = new ToolbarManager(this);
		XMLParser parser = new XMLParser();
		parser.writeEnviroment(myInitialEnviron);
		myInitialEnviron = parser.readEnvironment();
	}
	
	public void playAnimation(boolean on){
		delay = 0;
		myMapManager.spawnNewEnemy();
		if (on){
			KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
					e -> step());
			myTimeline.setCycleCount(Timeline.INDEFINITE);
			myTimeline.getKeyFrames().addAll(frame);
			myTimeline.play();
		}
	}
	
	
	private void step(){
		delay++;
		if (delay % 120 == 0) {
			myMapManager.spawnNewEnemy();
		}
//		for (Unit unit : myMapManager.getUnitsOnBoard()) {
//		}
//		myController.updateMap(myMapManager.getUnitsOnBoard());
	}


	
	@Override
//	public void update(List<Request> requests) {
//		// TODO Auto-generated method stub
//		// request if a CollisionRequest
//		
//		for(Request r :requests){
//			r.execute(myRuntimeEnviron);
//		}
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
		myHUDManager.incrementLevel();
		myMapManager.startWave(myLevels.get(i));
		playAnimation(true);
	}


	public void testCaseMaker(){
		PlayerInfo playerinfo = new PlayerInfo(200, 3, "level1");
		myController.updateUserInfo(playerinfo);
		HashMap<String, List<Unit>> myTestMap = new HashMap<String, List<Unit>>();
		List<Unit> TowerList = new ArrayList<Unit>();
		Tower t1 = new Tower("Basic Turret", 100.0, 10.0, "turret_transparent.png", 
				new Point(10,20), 1, 150, 75);
		Tower t2 = new Tower("Basic Turret", 100.0, 10.0, "turret_transparent.png", 
				new Point(20,40), 2, 150, 75);
		Tower t3 = new Tower("Basic Turret", 100.0, 10.0, "turret_transparent.png", 
				new Point(100,30), 3, 150, 75);
		Tower t4 = new Tower("Attack Turret", 200.0, 25.0, "turret.png", 
				new Point(50,20), 4, 250, 155);
		TowerList.add(t1);
		TowerList.add(t2);
		TowerList.add(t3);
		TowerList.add(t4);
		myTestMap.put("Towers", TowerList);
		List<Unit> TroopList = new ArrayList<Unit>();
		Troop tr1 = new Troop("Basic Minion", 50.0, 2.0, "purpleminion.png",
				new Point(290,30), 5, 50, 0);
		Troop tr2 = new Troop("Basic Minion", 50.0, 2.0, "purpleminion.png",
				new Point(130,130), 6, 50, 0);
		Troop tr3 = new Troop("Caster Minion", 150.0, 5.0, "casterminion.png",
				new Point(230,230), 7, 250, 0);
		TroopList.add(tr1);
		TroopList.add(tr2);
		TroopList.add(tr3);
		myTestMap.put("Towers", TowerList);
		myTestMap.put("Troops", TroopList);
//		myController.populateStore(myTestMap);
		List<Unit> mapUnits = new ArrayList<Unit>();
		mapUnits.addAll(TroopList);
		mapUnits.addAll(TowerList);
//		myCurrentUnits = mapUnits;
		myController.updateMap(mapUnits);
	}

	public void updateUserInfo(PlayerInfo myInfo) {
		myController.updateUserInfo(myInfo);
	}
	
//	public static void main(String[] args){
//		Engine e = new Engine(null,new Timeline());
//	}
}
