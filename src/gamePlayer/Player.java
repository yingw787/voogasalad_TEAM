package gamePlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import controller.Controller;
import gameEngine.requests.PauseRequest;
import gameEngine.requests.TwiceSpeedRequest;
import interfaces.IPlayer;
import interfaces.IRequest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import startup.Startup;
import units.Path;
import units.PlayerInfo;
import units.Unit;

public class Player implements IPlayer {
	private ResourceBundle myDefaults = ResourceBundle.getBundle("resources/Default");
	private Controller myController;
	private int myWidth = Integer.parseInt(myDefaults.getString("Width"));
	private int myHeight = Integer.parseInt(myDefaults.getString("Height"));
	private Stage myStage;
	private HUD myHUD;
	private Store myStore;
	private Map myMap;
	private Scene myScene;
	private Menus myMenus;
	private PlayerInfo myPlayerInfo;
	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private ResourceBundle myResource;
	private HBox pausePlay;

	public Player(Controller controller, Stage s) throws IOException {
		this.myController = controller;
		this.myStage = s;
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
		Group root = new Group();
		myHUD = new HUD(myController, this);
		myMap = new Map(myController, myStage, this);
		myMenus = new Menus(myController, myMap, myStage);
		myStore = new Store(this);
		BorderPane borderPane = new BorderPane();
		populate(borderPane);
		root.getChildren().add(borderPane);
		myScene = new Scene(root, myWidth, myHeight);
		myStage.setScene(myScene);
		initialize(s);
		
		myStage.setOnCloseRequest(e -> {
			myStage.close();
			Stage stage = new Stage();
			stage.setScene(new Startup(stage).getScene());
			stage.show();
		});
	}

	public MapUnit getSelected(){
		return myMap.getSelected();
	}

	public Stage getStage(){
		return myStage;
	}

	private void populate(BorderPane bp) throws IOException{
		bp.setTop(topMenuBar());
		bp.setLeft(myMap.initialize());
		bp.setRight(myHUD.initialize());
		bp.setBottom(myStore.initialize());
		bp.setStyle("-fx-background-color: linear-gradient(#FEF0C9, #61a2b1);");
		configure();
	}

	public void initialize(Stage stage) {
		stage.setWidth(Integer.parseInt(myDefaults.getString("Width")));
		stage.setHeight(Integer.parseInt(myDefaults.getString("Height")));
		stage.show();
	}

	@Override
	public void updateMap(List<Unit> units) {
		myMap.updateMap(units);

	}

	@Override
	public void updateUserInfo(PlayerInfo player) {
		myHUD.populate(player);
		myPlayerInfo = player;
	}

	public void updateInfo(PlayerInfo player) {
		myHUD.update(player);
		myPlayerInfo = player;
	}

	@Override
	public void showWin() {
		Alert win = new Alert(AlertType.INFORMATION);
		win.setHeaderText("You win!");
		win.setContentText("Congratulations, you win!");
		win.show();
	}

	@Override
	public void showLose() {
		Alert lose = new Alert(AlertType.INFORMATION);
		lose.setHeaderText("You lose.");
		lose.setContentText("Sorry, you lost. :(");
		lose.show();
	}

	public void startWave(int i){
		myController.startWave(i);
	}

	@Override
	public void populate(HashMap<String, List<Unit>> store) {
		myStore.setStock(store);
	}

	private Node topMenuBar() throws IOException{
		HBox result = new HBox();
		result.getChildren().addAll(myMenus.initialize(),topButtons());
		return result;

	}

	private void configure(){
		myStore.setWidth(myWidth);
		myStore.setHeight(myHeight*Double.parseDouble(myResource.getString("storeHeightConstant")));
		myHUD.setWidth(myWidth*Double.parseDouble(myResource.getString("hudWidthConstant")));
		myHUD.setHeight(myHeight*Double.parseDouble(myResource.getString("hudHeightConstant")));
		myMap.setWidth(myWidth*Double.parseDouble(myResource.getString("mapWidthConstant")));
		myMap.setHeight(myHeight*Double.parseDouble(myResource.getString("mapHeightConstant")));
		myMenus.setHeight(myHeight*Double.parseDouble(myResource.getString("menuHeightConstant")));
	}

	public int getMoney() {
		return myPlayerInfo.getMoney();
	}

	public void enableBuyButton(Unit unit) {
		myHUD.enableBuyButton(unit);
	}

	public void enableSell(MapUnit mapUnit) {
		myHUD.enableSell(mapUnit);
	}

	public void updateSelected(MapUnit myUnit){
		myHUD.updateSelected(myUnit);
	}

	public void enableTowerPurchase(Unit u) {
		myMap.enableTowerPurchase(u);
	}

	public void showPaths(List<Path> pathsForLevel) {
		myMap.showPaths(pathsForLevel);
	}

	public void resetStore() {
		myStore.resetStock();
	}

	private Node topButtons(){
		HBox buttonBox = new HBox();
		pausePlay = new HBox();
		
		Image pauseImage = new Image(getClass().getClassLoader().getResourceAsStream(myResource.getString("pauseButton")));
		ImageView pauseButton = getImageView(pauseImage);

		Image playImage = new Image(getClass().getClassLoader().getResourceAsStream(myResource.getString("playButton")));
		ImageView playButton = getImageView(playImage);

		Image fastForwardImage = new Image(getClass().getClassLoader().getResourceAsStream(myResource.getString("fastForwardButton")));
		ImageView fastForwardButton = getImageView(fastForwardImage);

		fastForwardButton.setOnMouseClicked(e->fastForwardClicked());
		getPausePlay().getChildren().add(pauseButton);
		getPausePlay().setOnMouseClicked(e->pausePlayClicked(pauseButton,playButton));
		buttonBox.getChildren().addAll(getPausePlay(), fastForwardButton);
		return buttonBox;
	}

	private ImageView getImageView(Image image){
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(Integer.parseInt(myResource.getString("nodesHeight")));
		imageView.setPreserveRatio(true);
		return imageView;
	}
	
	private void pausePlayClicked(ImageView initial, ImageView finalImg) {
		PauseRequest pause = new PauseRequest();
		List<IRequest> requestSender = new ArrayList<IRequest>();
		if(getPausePlay().getChildren().contains(initial)){
			getPausePlay().getChildren().remove(initial);
			getPausePlay().getChildren().add(finalImg);
			requestSender.add(pause);
			myController.update(requestSender);

		}else{
			getPausePlay().getChildren().remove(finalImg);
			getPausePlay().getChildren().add(initial);
			requestSender.add(pause);
			myController.update(requestSender);

		}
	}

	private void fastForwardClicked() {
		TwiceSpeedRequest fast = new TwiceSpeedRequest();
		List<IRequest> requestSender = new ArrayList<IRequest>();
		requestSender.add(fast);
		myController.update(requestSender);		
	}

	private HBox getPausePlay() {
		return pausePlay;
	}

}
