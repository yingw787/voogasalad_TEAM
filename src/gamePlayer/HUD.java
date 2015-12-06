/*
 * 
 */
package gamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import controller.Controller;
import gameEngine.environments.Environment;
import gameEngine.requests.SellTowerRequest;
import interfaces.IRequest;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import units.PlayerInfo;
import units.Tower;
import units.Unit;

/**
 * This class shows the heads-up display for the player. 
 * This contains the information the player needs in order to play the game, including money, lives, and level progress for some instances of the game.
 * It also contains buttons to sell and buy the tower and turret as well as button to start the wave.
 *  <p>
 * This class implements ViewNode interface.
 *
 * @see         Player
 * @see 		IViewNode
 */


public class HUD extends Observable implements IViewNode{

	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private VBox myVBox;
	private PlayerInfo myPlayerInfo;
	private Selected selectedDisplay;
	private Button myBuyButton, mySellButton, myWaveButton; 
	private Controller myController;
	private Player myPlayer;
	private ResourceBundle myResource;
	private String buttonStyle;
	private Node myLives, myLevel, myGold;

	public HUD(Controller c, Player p){
		this.myController = c;
		this.myPlayer = p;
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
	}

	/**
	 * Initialize v box
	 * 
	 * @return the v box
	 */
	public VBox initialize(){
		myVBox = new VBox(20);
		myVBox.setStyle("-fx-background-color: linear-gradient(#FEF0C9, #61a2b1);");

		return myVBox;
	}
	
	public void update(PlayerInfo playerInfo){
		if (myPlayerInfo.getLevel()!=playerInfo.getLevel()){
			myLevel = level(playerInfo);
		}
		if (myPlayerInfo.getMoney()!=playerInfo.getMoney()){
			myGold = gold(playerInfo);
		}
		if (myPlayerInfo.getLives()!=playerInfo.getLives()){
			myLives = lives(playerInfo);
		}
		myPlayerInfo = playerInfo;
	}

	/**
	 * create HBox for amount of Gold.
	 *
	 * @param player the player
	 * @return the node
	 */
	public Node gold(PlayerInfo player){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResource.getString("goldImage")));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(30);
		imageView.setPreserveRatio(true);

		HBox myHBox = new HBox();
		myHBox.setAlignment(Pos.CENTER);
		myHBox.setPrefHeight(30);
		Text money = new Text(" " + player.getMoney());
		money.setStyle("-fx-font: 25px Tahoma;");
		myHBox.getChildren().addAll(imageView,money);
		return myHBox;
	}

	/**
	 * creates a Node for Selected display.
	 *
	 * @return the node
	 */
	public Node selectedDisplay(){
		selectedDisplay = new Selected(myPlayer);
		return selectedDisplay.getDisplay();
	}

	/**
	 * Creates Node for number of Lives left.
	 *
	 * @param player the player
	 * @return the node
	 */
	public Node lives(PlayerInfo player){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResource.getString("lives")));

		ImageView imageView1 = new ImageView(image);
		imageView1.setFitHeight(30);
		imageView1.setPreserveRatio(true);

		ImageView imageView2 = new ImageView(image);
		imageView2.setFitHeight(30);
		imageView2.setPreserveRatio(true);

		ImageView imageView3 = new ImageView(image);
		imageView3.setFitHeight(30);
		imageView3.setPreserveRatio(true);

		HBox myHBox = new HBox();
		myHBox.setAlignment(Pos.CENTER);
		myHBox.setPrefHeight(30);
		Text lives = new Text("Lives: ");
		lives.setStyle("-fx-font: 25px Tahoma;");
		Text livesMinusThree = new Text(" + " + String.valueOf((player.getLives()-3)));
		livesMinusThree.setStyle("-fx-font: 25px Tahoma;");
		if(player.getLives() > 3){
			myHBox.getChildren().addAll(lives,imageView1,imageView2,imageView3,livesMinusThree);
		}else if(player.getLives() == 3){
			myHBox.getChildren().addAll(lives,imageView1,imageView2,imageView3);
		}else if (player.getLives() == 2){
			myHBox.getChildren().addAll(lives,imageView1,imageView2);
		}else if (player.getLives() == 1){
			myHBox.getChildren().addAll(lives,imageView1);
		}else{
			myHBox.getChildren().add(lives);
		}
		return myHBox;		
	}

	/**
	 * creates Wave button in the HUD.
	 *
	 * @return the node
	 */
	public Node waveButton(){
		HBox myHBox = new HBox();
		myHBox.setAlignment(Pos.CENTER);
		myWaveButton = new Button("Start Wave");
		myWaveButton.setStyle(buttonStyle);
		if(Integer.parseInt(myPlayerInfo.getLevel()) < myPlayerInfo.getMyLevelSize()){
			myWaveButton.setOnMouseClicked(e->myController.startWave(
					Integer.parseInt(myPlayerInfo.getLevel())));	
		}else{
			myWaveButton.setOnMouseClicked(e->startWaveAlert());
		}
		myHBox.getChildren().add(myWaveButton);
		return myHBox;
	}


	/**
	 * shows alert message for Start wave.
	 */
	private void startWaveAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Alert Message");
		String label = null;
		label = "You have exceeded the total number of levels for this game";
		alert.setContentText(label);
		alert.showAndWait();
	}

	/**
	 * creates HBox for game Level.
	 *
	 * @param player the player
	 * @return the node
	 */
	public Node level(PlayerInfo player){
		HBox myHBox = new HBox();
		myHBox.setAlignment(Pos.CENTER);
		myHBox.setPrefHeight(30);
		Text level = new Text("Level: " + player.getLevel());
		level.setStyle("-fx-font: 25px Tahoma;");
		myHBox.getChildren().add(level);
		return myHBox;
	}

	/**
	 * creates HBox for Buy and Sell buttons.
	 *
	 * @return the node
	 */
	public Node buySellButton(){

		HBox myHBox = new HBox();
		buttonStyle = myResource.getString("cssHUDButtonStyle");
		myBuyButton = new Button("Buy");
		myBuyButton.setDisable(true);
		myBuyButton.setPrefSize(150,30);
		myBuyButton.setStyle(buttonStyle);
		myBuyButton.setOnMouseClicked(e->buyButtonClicked());

		mySellButton = new Button("Sell");
		mySellButton.setDisable(true);
		mySellButton.setOnMouseClicked(e->sellButtonClicked());
		mySellButton.setPrefSize(150,30);
		mySellButton.setStyle(buttonStyle);

		myHBox.getChildren().addAll(myBuyButton,mySellButton);
		return myHBox;

	}

	/**
	 * Populates all the Nodes in HUD's VBox.
	 *
	 * @param player the player
	 */
	public void populate(PlayerInfo player){
		myPlayerInfo = player;
		myVBox.getChildren().clear();
		myGold = gold(player);
		myLives = lives(player);
		myLevel = level(player);
		myVBox.getChildren().addAll(gold(player), lives(player), level(player), buySellButton(), waveButton(), selectedDisplay());
	}

	private void sellButtonClicked(){
		Unit selectedUnit = myPlayer.getSelected().getUnit();
		if(selectedUnit.getStringAttribute("Type").equals("Tower")){
			Tower tower = new Tower(selectedUnit);
			mySellButton.setDisable(true);
			SellTowerRequest sell = new SellTowerRequest(tower);
			List<IRequest> requestSender = new ArrayList<IRequest>();
			requestSender.add(sell);
			myController.update(requestSender);
			//myView.sellItem();
			System.out.println("abhishek");
		}
	}

	private void buyButtonClicked() {
		myBuyButton.setDisable(true);
		//		myView.purchaseItem();
	}

	/* (non-Javadoc)
	 * @see gamePlayer.IViewNode#setHeight(double)
	 */
	@Override
	public void setHeight(double height){
		myVBox.setPrefHeight(height);
	}

	/* (non-Javadoc)
	 * @see gamePlayer.IViewNode#setWidth(double)
	 */
	@Override
	public void setWidth(double width){
		myVBox.setPrefWidth(width);
	}

	/**
	 * Enables buy button.
	 *
	 * @param unit the unit
	 */
	public void enableBuyButton(Unit unit) {
		myBuyButton.setDisable(false);
	}

	/**
	 * Updates selected unit.
	 *
	 * @param myUnit the my unit
	 */
	public void updateSelected(MapUnit myUnit){
		selectedDisplay.update(myUnit);
	}

	/**
	 * Enables selling of unit.
	 *
	 * @param myUnit the my unit
	 */
	public void enableSell(MapUnit myUnit) {
//		if (myUnit.getUnit().getClass().toString().equals("class units.Tower")){
			mySellButton.setDisable(false);
//		}
		selectedDisplay.setImage(myUnit);
		updateSelected(myUnit);
	}

}
