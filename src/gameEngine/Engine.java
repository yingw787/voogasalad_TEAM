package gameEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
import gamedata.xml.XMLConverter;
import interfaces.IEngine;
import interfaces.IRequest;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import units.IDGenerator;
import units.Level;
import units.Path;
import units.PlayerInfo;
import units.Point;
import units.Unit;

public class Engine implements IEngine {
	private Controller myController;
	private Timeline myTimeline;
	public static final int FRAMES_PER_SECOND = 120;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	private HashMap<String, List<Unit>> myPossibleUnits;
	private List<PlayerInfo> myPlayerInfo;
	private List<Level> myLevels;
	private List<Path> myPaths;
	private int myCurrentLevelInt;
	private Level myCurrentLevel;
	private RuntimeEnvironment myRuntimeEnviron;
	private ToolbarManager myTBManager;
	private MapManager myMapManager;
	private HUDManager myHUDManager;
	private IDGenerator myIDGenerator;
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
		myPaths = new ArrayList<Path>();
		List<Point> pathPoints = new ArrayList<Point>();
		pathPoints.add(new Point(0,230));
		pathPoints.add(new Point(200,230));
		pathPoints.add(new Point(200,50));
		pathPoints.add(new Point(400,50));
		pathPoints.add(new Point(400,230));
		pathPoints.add(new Point(600,230));
		myPaths.add(new Path("Path 1",pathPoints));
		myCurrentLevelInt = 0;
	}
	
	public void initialize(){
		myController.updateUserInfo(myPlayerInfo.get(0));
		myController.populateStore(myPossibleUnits);
		myIDGenerator = new IDGenerator();
<<<<<<< HEAD
		myMapManager = new MapManager(this, myPossibleUnits.get("Troops"), myPaths, myIDGenerator);
		myHUDManager = new HUDManager(this, myPlayerInfo.get(0));
=======
		myMapManager = new MapManager(myController, myPaths, myIDGenerator);
		myHUDManager = new HUDManager(myController, myPlayerInfo.get(0));
		myInitialEnviron = new InitialEnvironment();
>>>>>>> 6820d8e5b8ce990811154a11faf4a49cd44fdff5
		myRuntimeEnviron = new RuntimeEnvironment();
	}
	
	public void writeEnvironment() throws IOException{
		myTBManager = new ToolbarManager(this);
	}
	
	public void playAnimation(boolean on){
		delay = 0;
		if (on){
			myMapManager.spawnNewEnemy();
			KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
					e -> step());
			myTimeline.setCycleCount(Timeline.INDEFINITE);
			myTimeline.getKeyFrames().addAll(frame);
			myTimeline.play();
		} 
		if (!on){
			myTimeline.stop();
		}
	}
	
	
	private void step(){
		if (myMapManager.hasMoreEnemies()){
			if (delay % 60 == 0) {
				myMapManager.spawnNewEnemy();
			}
		}
		delay++;
		for (Unit unit : myMapManager.getUnitsOnBoard()) {
			myMapManager.walkUnitOnMap(unit);
		}
		myController.updateMap(myMapManager.getUnitsOnBoard());
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
		List<String> pathNames = new ArrayList<String>();
		pathNames.add("Path 1");
		myMapManager.startWave(myLevels.get(i), pathNames);
		playAnimation(true);
	}
	
//	public static void main(String[] args){
//		Engine e = new Engine(null,new Timeline());
//	}
}
