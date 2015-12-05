package gameEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import gameEngine.environments.RuntimeEnvironment;
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
import units.Point;
import units.Troop;
import units.Unit;

public class Engine implements IEngine {
	private Controller myController;
	private Timeline myTimeline;
	public static final int FRAMES_PER_SECOND = 120;
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
	
	public void writeEnvironment(String gameTitle) throws IOException{
		myIDGenerator = new IDGenerator();
		XMLConverter myConverter = new XMLConverter();
		myRE = new RuntimeEnvironment(myConverter.getUnits(gameTitle, "Tower"), 
				myConverter.getUnits(gameTitle, "Troop"), myConverter.getLevels(gameTitle), myConverter.getPaths(gameTitle), 
				myConverter.getPlayerInfo(gameTitle), new GameConfiguration(), new ArrayList<Rule>(), new Base(), myIDGenerator);
	}
	
	public void initialize(){
		myMapManager = new MapManager(myRE, myIDGenerator);
		myController.updateUserInfo(myRE.getPlayerInfo());
		myController.populateStore(myRE.getStoreStock());
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
		if (!myMapManager.noMoreEnemies()){
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
			else if(unit.getStringAttribute("Type").equals("Bullet")){
				Point p = unit.getPoint();
				p.setX(p.getX() + unit.getAttribute("SpedX"));
				p.setY(p.getY() + unit.getAttribute("SpedY"));
				unit.setPoint(p);
			}
		}
		myController.updateMap(myRE.getUnits());
	}

	
	@Override
	public void update(List<IRequest> requests) {
		// TODO Auto-generated method stub
		// request if a CollisionRequest
		for (IRequest r : requests) {
			r.execute(myRE,myController);

		}
		if (myRE.checkLose()) {
			myController.showLose();
		}
		if (myRE.checkWin()) {
			myController.showWin();
		}
	}
	
	
	//myPlayer.showWin();
	

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
//		spawnDelay = (int) (60.0 * level.getSpawnRate());
		spawnDelay = (int) (60.0 * 1);
		myMapManager.startWave(myRE.getLevel(i), myRE.getPathsForLevel(myRE.getLevel(i).getPathNames()));
		playAnimation(true);
	}
	
	

}
