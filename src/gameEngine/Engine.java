package gameEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
import gameEngine.requests.BuyTowerRequest;
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
import units.Tower;
import units.Unit;

public class Engine implements IEngine {
	private Controller myController;
	private Timeline myTimeline;
	public static final int FRAMES_PER_SECOND = 240;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private RuntimeEnvironment myRE;
	private MapManager myMapManager;
	private IDGenerator myIDGenerator;
	private int delay = 0;
	private int spawnDelay = 60;
	
	public Engine(Controller controller, Timeline timeline) {
		myController = controller;
		myTimeline = timeline;
		myTimeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	public void writeEnvironment() throws IOException{
		myIDGenerator = new IDGenerator();
		XMLConverter myConverter = new XMLConverter();
		myRE = new RuntimeEnvironment(myConverter.getUnits("Game 1", "Tower"), 
				myConverter.getUnits("Game 1", "Troop"), myConverter.getLevels("Game 1"), myConverter.getPaths("Game 1"), 
				myConverter.getPlayerInfo("Game 1"), new GameConfiguration(), new ArrayList<Rule>(), new Base(), myIDGenerator);
	
//		myTBManager = new ToolbarManager(myController);
	}
	
	public void initialize(){
		myMapManager = new MapManager(myRE, myIDGenerator);
		myController.updateUserInfo(myRE.getPlayerInfo());
		myController.populateStore(myRE.getStoreStock());
	}
	
	private void flush() {
		myController.updateMap(myRE.getUnits());
		
		//UNCOMMENTING UPDATEUSERINFO WILL LAG OUT THE GUI!!!!!
//		myController.updateUserInfo(myRE.getPlayerInfo());
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
			if (delay % spawnDelay == 0) {
				myMapManager.spawnNewEnemy();
			}
		}
		delay++;
		List<Unit> currentUnitsOnBoard = new ArrayList<Unit>(myRE.getUnits());
		for (Unit unit : currentUnitsOnBoard) {
			if (unit.getStringAttribute("Type").equals("Troop")){
				myMapManager.walkUnitOnMap(unit);
			}
		}
//		myController.updateMap(myRE.getUnits());
		
		flush();
	}

	
	@Override
	public void update(List<IRequest> requests) {
		// TODO Auto-generated method stub
		// request if a CollisionRequest
		for (IRequest r : requests) {
			r.execute(myRE);
		}
		myController.updateMap(myRE.getUnits());
		myController.updateUserInfo(myRE.getPlayerInfo());
		myController.resetStore();
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
		myRE.incrementLevel();
		myController.updateUserInfo(myRE.getPlayerInfo());
		myController.showPaths(myRE.getPathsForLevel(myRE.getLevel(i).getPathNames()));
		Level level = myRE.getLevel(i);
		spawnDelay = (int) (60.0 * level.getSpawnRate());
		myMapManager.startWave(myRE.getLevel(i), myRE.getPathsForLevel(myRE.getLevel(i).getPathNames()));
		playAnimation(true);
	}
	
	
//	public static void main(String[] args){
//		Engine e = new Engine(null,new Timeline());
//		List<IRequest> requestList = new ArrayList<IRequest>();
//		Tower tower = null;
//		tower = new Tower(e.getTBManager().myTowers.get(0));
//		IRequest rq = new BuyTowerRequest(tower, null);
//		requestList.add(rq);
//		e.update(requestList);
//	}
}
