// This entire file is part of my masterpiece.
// Abhishek Upadhyaya Ghimire

package gamePlayer;

import java.util.Map;
import java.util.ResourceBundle;
import controller.Controller;
import gamePlayer.button.AButton;
import gamePlayer.button.ButtonManager;
import image.ImageMaker;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	private VBox hudBox;
	private PlayerInfo myPlayerInfo;
	private Selected selectedDisplay;
	private Controller myController;
	private Player myPlayer;
	private ResourceBundle myResource;
	@SuppressWarnings("unused")
	private Node myLives, myLevel, myTotalGold;
	private Map<String, AButton> myButtons;

	public HUD(Controller controller, Player player){
		this.myController = controller;
		this.myPlayer = player;
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
		ButtonManager buttonFactory = new ButtonManager(controller, player);
		myButtons = buttonFactory.getButtons();
	}

	/**
	 * Initializes v box
	 * @return the v box
	 */
	public VBox initialize(){
		hudBox = new VBox(Integer.parseInt(myResource.getString("vboxSpacing")));
		hudBox.setStyle("-fx-background-color: linear-gradient(#FEF0C9, #61a2b1);");
		return hudBox;
	}

	/**
	 * Updates playerinfo in HUD
	 * @param playerInfo the player info
	 */
	public void update(PlayerInfo playerInfo){
		if (myPlayerInfo.getLevel()!=playerInfo.getLevel()){
			myLevel = level(playerInfo);
		}
		if (myPlayerInfo.getMoney()!=playerInfo.getMoney()){
			myTotalGold = totalGold(playerInfo);
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
	public Node totalGold(PlayerInfo player){
		Image image = ImageMaker.getImage(myResource.getString("goldImage"));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(Integer.parseInt(myResource.getString("nodesHeight")));
		imageView.setPreserveRatio(true);

		HBox totalMoney = new HBox();
		setHBoxProperties(totalMoney);
		Text money = new Text(" " + player.getMoney());
		money.setStyle("-fx-font: 25px Tahoma;");
		totalMoney.getChildren().addAll(imageView,money);
		return totalMoney;
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

		HBox numberOfLives = new HBox();
		setHBoxProperties(numberOfLives);
		
		Text lives = new Text("Lives: ");
		lives.setStyle("-fx-font: 25px Tahoma;");
		
		Text livesMinusThree = new Text(" + " + String.valueOf((player.getLives()-3)));
		livesMinusThree.setStyle("-fx-font: 25px Tahoma;");
		if(player.getLives() > 3){
			numberOfLives.getChildren().addAll(lives,livesImages[0],livesImages[1],livesImages[2],livesMinusThree);
		}else if(player.getLives() == 3){
			numberOfLives.getChildren().addAll(lives,livesImages[0],livesImages[1],livesImages[2]);
		}else if (player.getLives() == 2){
			numberOfLives.getChildren().addAll(lives,livesImages[0],livesImages[1]);
		}else if (player.getLives() == 1){
			numberOfLives.getChildren().addAll(lives,livesImages[0]);
		}else{
			numberOfLives.getChildren().add(lives);
		}
		return numberOfLives;		
	}

	/**
	 * creates Wave button in the HUD.
	 * @return the node
	 */
	private Node waveButton(){
		HBox startWave = new HBox();
		startWave.setAlignment(Pos.CENTER);
		if(Integer.parseInt(myPlayerInfo.getLevel()) < myPlayerInfo.getMyLevelSize()){
			myButtons.get("StartWaveButton").setOnMouseClicked(e->myController.startWave(
					Integer.parseInt(myPlayerInfo.getLevel())));	
		}else{
			myButtons.get("StartWaveButton").setOnMouseClicked(e->startWaveAlert());
		}
		startWave.getChildren().add(myButtons.get("StartWaveButton"));
		return startWave;
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
	 * @param player the player
	 * @return the node
	 */
	private Node level(PlayerInfo player){
		HBox numberOfLevels = new HBox();
		setHBoxProperties(numberOfLevels);
		Text level = new Text("Level: " + player.getLevel());
		level.setStyle("-fx-font: 25px Tahoma;");
		numberOfLevels.getChildren().add(level);
		return numberOfLevels;
	}

	/**
	 * Sets the HBox properties.
	 * @param label the label
	 * @return the HBox
	 */
	private HBox setHBoxProperties(HBox label){
		label.setAlignment(Pos.CENTER);
		label.setPrefHeight(Integer.parseInt(myResource.getString("nodesHeight")));
		return label;
	}
	
	/**
	 * creates HBox for Buy and Sell buttons.
	 * @return the node
	 */
	private Node buySellButton(){
		HBox buySellButtons = new HBox();
		buySellButtons.getChildren().addAll(myButtons.get("BuyButton"),myButtons.get("SellButton"));
		return buySellButtons;
	}

	/**
	 * Populates all the Nodes in HUD's VBox.
	 * @param player the player
	 */
	public void populate(PlayerInfo player){
		myPlayerInfo = player;
		hudBox.getChildren().clear();
		myTotalGold = totalGold(player);
		myLives = lives(player);
		myLevel = level(player);
		hudBox.getChildren().addAll(totalGold(player), lives(player), level(player), buySellButton(), waveButton(), selectedDisplay());
	}

	@Override
	public void setHeight(double height){
		hudBox.setPrefHeight(height);
	}

	@Override
	public void setWidth(double width){
		hudBox.setPrefWidth(width);
	}

	/**
	 * Enables buy button.
	 * @param unit the unit
	 */
	public void enableBuyButton(Unit unit) {
		myButtons.get("BuyButton").setDisable(false);
	}

	/**
	 * Updates selected unit.
	 * @param myUnit the my unit
	 */
	public void updateSelected(MapUnit myUnit){
		selectedDisplay.update(myUnit);
	}

	/**
	 * Enables selling of unit.
	 * @param myUnit the my unit
	 */
	public void enableSell(MapUnit myUnit) {
		myButtons.get("SellButton").setDisable(false);
		selectedDisplay.setImage(myUnit);
		updateSelected(myUnit);
	}
}