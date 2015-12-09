package gameEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import units.Point;
import units.Unit;

public class Engine implements IEngine {
	
	private Controller myController;
	private Timeline myTimeline;
	private static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private RuntimeEnvironment myRE;
	private MapManager myMapManager;
	private int delay = 0;
	private int spawnDelay = 60;
	private String helpPage;
	
	public Engine(Controller controller) {
		myController = controller;
		myTimeline = new Timeline();
		myTimeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	public String getHelp(String gameTitle) {
		XMLConverter myConverter = new XMLConverter();
		helpPage = myConverter.getHelp(gameTitle);
		return helpPage;
	}
	
	public void writeEnvironment(String gameTitle) throws IOException{
		XMLConverter myConverter = new XMLConverter();
		myRE = new RuntimeEnvironment(myConverter.getUnits(gameTitle, "Tower"), 
				myConverter.getUnits(gameTitle, "Troop"), myConverter.getLevels(gameTitle), myConverter.getPaths(gameTitle), 
				myConverter.getPlayerInfo(gameTitle), new GameConfiguration(), new ArrayList<Rule>(), new Base(), myConverter.getPathVisibility(gameTitle));

	}
	
	public void initialize(){
		myMapManager = new MapManager(this);
		myController.updateUserInfo(myRE.getPlayerInfo());
		myController.populateStore(myRE.getStoreStock());
		
		// render the path before the first level starts (upon initialization)
		myController.showPaths(myRE.getPathsForLevel(myRE.getLevel(0).getPathNames()), myRE.getPathVisibility());
		
	}
	
	public void playAnimation(boolean on){
		delay = 0;
		if (on){
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
	
	public Controller getController(){
		return myController; 
	}
	
	public void step(){
		for (Unit unit : myRE.getUnits()) {
			for(Rule rule : unit.getRules()){
				rule.run(unit, myRE,this.myController);
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
		if (checkLose()) {
			myTimeline.stop();
			myController.showLose();
		}
		if (checkWin()) {
			myTimeline.stop();
			myController.showWin();
		
		}
	}

	
	@Override
	public void update(List<IRequest> requests) {
		// TODO Auto-generated method stub
		// request if a CollisionRequest
		for (IRequest r : requests) {
			r.execute(this);

		}
	}
	
	
	//myPlayer.showWin();
	

	public RuntimeEnvironment getRuntimeEnvironment(){
		return myRE; 
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
		myController.showPaths(myRE.getPathsForLevel(myRE.getLevel(i).getPathNames()), myRE.getPathVisibility());
		//		spawnDelay = (int) (60.0 * level.getSpawnRate());
		spawnDelay = (int) (60.0 * 1);
		myMapManager.startWave(myRE.getLevel(i), myRE.getPathsForLevel(myRE.getLevel(i).getPathNames()));
		playAnimation(true);
	}
	
	// THIS IS BROKEN RIGHT NOW
	public void redisplayPath(){
		// RIGHT NOW THIS IS BROKEN 
		System.out.println("I am in Engine.java --> redisplayPath() right now");
		
		String level = myRE.getPlayerInfo().getLevel();
		System.out.println(level);
		int level_int = Integer.parseInt(level); 
		myController.showPaths(myRE.getPathsForLevel(myRE.getLevel(level_int).getPathNames()), myRE.getPathVisibility());
	}
		
	private boolean checkWin() {
		int level = Integer.parseInt(myRE.getPlayerInfo().getLevel());
		int totalLevel = myRE.getPlayerInfo().getMyLevelSize();
		boolean isEnd = level == totalLevel && myMapManager.noMoreEnemies();
		if (!isEnd) {
			return false;
		}
		List<Unit> units = myRE.getUnits();
		for (Unit i : units) {
			if (i.getStringAttribute("Type") == "Troop") {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkLose() {
		int live = myRE.getPlayerInfo().getLives();
		return (live < 0);
	}

	public Timeline getTimeline() {
		return myTimeline;
	}

	public void setTimeline(Timeline myTimeline) {
		this.myTimeline = myTimeline;
	}

}
