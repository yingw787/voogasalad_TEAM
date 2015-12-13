// This entire file is part of my masterpiece.
// Abhishek Upadhyaya Ghimire

package gamePlayer;

import java.util.Map;
import java.util.ResourceBundle;

import controller.Controller;
import gamePlayer.button.AButton;
import gamePlayer.button.ButtonFactory;
import image.ImageMaker;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import units.PlayerInfo;
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

public class HUD implements IViewNode{

	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private VBox myVBox;
	private PlayerInfo myPlayerInfo;
	private Selected selectedDisplay;
	@SuppressWarnings("unused")
	private Controller myController;
	private Player myPlayer;
	private ResourceBundle myResource;
	@SuppressWarnings("unused")
	private Node myLives, myLevel, myGold;
	private Map<String, AButton> myButtons;

	public HUD(Controller controller, Player player){
		this.myController = controller;
		this.myPlayer = player;
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
		myPlayerInfo = new PlayerInfo();
		ButtonFactory buttonFactory = new ButtonFactory(controller, myPlayerInfo, player);
		myButtons = buttonFactory.getButtons();

	}

	/**
	 * Initialize v box
	 * @return the v box
	 */
	public VBox initialize(){
		myVBox = new VBox(Integer.parseInt(myResource.getString("vboxSpacing")));
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
	 * @param player the player
	 * @return the node
	 */
	public Node gold(PlayerInfo player){
		Image image = ImageMaker.getImage(myResource.getString("goldImage"));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(Integer.parseInt(myResource.getString("nodesHeight")));
		imageView.setPreserveRatio(true);

		HBox myHBox = new HBox();
		myHBox.setAlignment(Pos.CENTER);
		myHBox.setPrefHeight(Integer.parseInt(myResource.getString("nodesHeight")));
		Text money = new Text(" " + player.getMoney());
		money.setStyle("-fx-font: 25px Tahoma;");
		myHBox.getChildren().addAll(imageView,money);
		return myHBox;
	}

	/**
	 * creates a Node for Selected display.
	 * @return the node
	 */
	public Node selectedDisplay(){
		selectedDisplay = new Selected(myPlayer);
		return selectedDisplay.getDisplay();
	}

	/**
	 * Creates Node for number of Lives left.
	 * @param player the player
	 * @return the node
	 */
	public Node lives(PlayerInfo player){
		Image image = ImageMaker.getImage(myResource.getString("lives"));
		ImageView[] livesImages = new ImageView[3];

		for (int i=0; i<livesImages.length;i++){
			livesImages[i] = new ImageView();
			livesImages[i].setImage(image);
			livesImages[i].setFitHeight(Integer.parseInt(myResource.getString("nodesHeight")));
			livesImages[i].setPreserveRatio(true);
		}

		HBox myHBox = new HBox();
		myHBox.setAlignment(Pos.CENTER);
		myHBox.setPrefHeight(Integer.parseInt(myResource.getString("nodesHeight")));
		Text lives = new Text("Lives: ");
		lives.setStyle("-fx-font: 25px Tahoma;");
		Text livesMinusThree = new Text(" + " + String.valueOf((player.getLives()-3)));
		livesMinusThree.setStyle("-fx-font: 25px Tahoma;");
		if(player.getLives() > 3){
			myHBox.getChildren().addAll(lives,livesImages[0],livesImages[1],livesImages[2],livesMinusThree);
		}else if(player.getLives() == 3){
			myHBox.getChildren().addAll(lives,livesImages[0],livesImages[1],livesImages[2]);
		}else if (player.getLives() == 2){
			myHBox.getChildren().addAll(lives,livesImages[0],livesImages[1]);
		}else if (player.getLives() == 1){
			myHBox.getChildren().addAll(lives,livesImages[0]);
		}else{
			myHBox.getChildren().add(lives);
		}
		return myHBox;		
	}

	/**
	 * creates Wave button in the HUD.
	 * @return the node
	 */
//	private Node waveButton(){
//		HBox myHBox = new HBox();
//		myHBox.setAlignment(Pos.CENTER);
//		buttonStyle = myResource.getString("cssHUDButtonStyle");
//		myWaveButton = new Button("Start Wave");
//		myWaveButton.setStyle(buttonStyle);
//		if(Integer.parseInt(myPlayerInfo.getLevel()) < myPlayerInfo.getMyLevelSize()){
//			myWaveButton.setOnMouseClicked(e->myController.startWave(
//					Integer.parseInt(myPlayerInfo.getLevel())));	
//		}else{
//			myWaveButton.setOnMouseClicked(e->startWaveAlert());
//		}
//		myHBox.getChildren().add(myWaveButton);
//		return myHBox;
//	}

	/**
	 * shows alert message for Start wave.
	 */
//	private void startWaveAlert() {
//		Alert alert = new Alert(AlertType.WARNING);
//		alert.setTitle("Alert Message");
//		String label = null;
//		label = "You have exceeded the total number of levels for this game";
//		alert.setContentText(label);
//		alert.showAndWait();
//	}

	/**
	 * creates HBox for game Level.
	 * @param player the player
	 * @return the node
	 */
	private Node level(PlayerInfo player){
		HBox myHBox = new HBox();
		myHBox.setAlignment(Pos.CENTER);
		myHBox.setPrefHeight(Integer.parseInt(myResource.getString("nodesHeight")));
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
	private Node buySellButton(){
//
		HBox myHBox = new HBox();
//		myBuyButton = new Button("Buy");
//		myBuyButton.setDisable(true);
//		myBuyButton.setPrefSize(150,Integer.parseInt(myResource.getString("nodesHeight")));
//		myBuyButton.setStyle(buttonStyle);
//		myBuyButton.setOnMouseClicked(e->buyButtonClicked());
//
//		mySellButton = new Button("Sell");
//		mySellButton.setDisable(true);
//		mySellButton.setOnMouseClicked(e->sellButtonClicked());
//		mySellButton.setPrefSize(150,Integer.parseInt(myResource.getString("nodesHeight")));
//		mySellButton.setStyle(buttonStyle);
//
		myHBox.getChildren().addAll(myButtons.get("BuyButton"),myButtons.get("SellButton"));
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
		myVBox.getChildren().addAll(gold(player), lives(player), level(player), buySellButton(), myButtons.get("StartWaveButton"), selectedDisplay());
	}

//	private void sellButtonClicked(){
//		Unit selectedUnit = myPlayer.getSelected().getUnit();
//		if(selectedUnit.getStringAttribute("Type").equals("Tower")){
//			Tower tower = new Tower(selectedUnit);
//			mySellButton.setDisable(true);
//			SellTowerRequest sell = new SellTowerRequest(tower);
//			List<IRequest> requestSender = new ArrayList<IRequest>();
//			requestSender.add(sell);
//			myController.update(requestSender);
//			//myView.sellItem();
//		}
//	}

//	private void buyButtonClicked() {
//		myBuyButton.setDisable(true);
//		//		myView.purchaseItem();
//	}

	@Override
	public void setHeight(double height){
		myVBox.setPrefHeight(height);
	}

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
		myButtons.get("BuyButton").setDisable(false);
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
//		mySellButton.setDisable(false);
		selectedDisplay.setImage(myUnit);
		updateSelected(myUnit);
	}

}
