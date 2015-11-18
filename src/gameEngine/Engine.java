package gameEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
import gameEngine.requests.Request;
import gamedata.xml.XMLConverter;
import interfaces.IEngine;
import interfaces.IRequest;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import rules.Rule;
import units.Base;
import units.IDGenerator;
import units.Level;
import units.Path;
import units.PlayerInfo;
import units.Point;
import units.Unit;

public class Engine implements IEngine {
	private Controller myController;
	private Timeline myTimeline;
	public static final int FRAMES_PER_SECOND = 180;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	private HashMap<String, List<Unit>> myPossibleUnits;
	private List<PlayerInfo> myPlayerInfo;
	private List<Level> myLevels;
	private List<Path> myPaths;
	private int myCurrentLevelInt;
	private Level myCurrentLevel;
	private RuntimeEnvironment myRE;
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
	
	public void writeEnvironment() throws IOException{
		XMLConverter myConverter = new XMLConverter();
		myRE = new RuntimeEnvironment(myConverter.getUnits("Game 1", "Tower"), 
				myConverter.getUnits("Game 1", "Troop"), myConverter.getLevels("Game 1"), myConverter.getPaths("Game 1"), 
				myConverter.getPlayerInfo("Game 1"), new GameConfiguration(), new ArrayList<Rule>(), new Base());
	
//		myTBManager = new ToolbarManager(myController);
	}
	
	public void initialize(){
		myController.updateUserInfo(myRE.getPlayerInfo());
		myController.populateStore(myRE.getStoreStock());
		myIDGenerator = new IDGenerator();
		myMapManager = new MapManager(myController, myRE.getPaths(), myIDGenerator);
		myHUDManager = new HUDManager(myController, myRE.getPlayerInfo());
	}
	
	private void flush() {
		List<Unit> l = new ArrayList<Unit>();
		l.addAll(myRE.getUnits());
		myController.updateMap(l);
		myHUDManager.updateUserInfo();
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
		for (Unit unit : myRE.getUnits()) {
			for(Rule rule : unit.getRules()){
				rule.run(unit, myRE);
			}
		}
		
		if (myMapManager.hasMoreEnemies()){
			if (delay % 60 == 0) {
				myMapManager.spawnNewEnemy();
			}
		}
		delay++;
		List<Unit> currentUnitsOnBoard = new ArrayList<Unit>(myMapManager.getUnitsOnBoard());
		for (Unit unit : currentUnitsOnBoard) {
			if (unit.getStringAttribute("Type").equals("Troop")){
				myMapManager.walkUnitOnMap(unit);
			}
		}
		myController.updateMap(myMapManager.getUnitsOnBoard());
		
//		flush();
	}

	
	@Override
	public void update(List<IRequest> requests) {
		// TODO Auto-generated method stub
		// request if a CollisionRequest
		for (IRequest r : requests) {
			r.execute(myRE);
		}
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
		pathNames.add("Path 2");
		myMapManager.startWave(myLevels.get(i), pathNames);
		playAnimation(true);
	}
	
//	public static void main(String[] args){
//		Engine e = new Engine(null,new Timeline());
//	}
}
