package gamePlayer;

import java.util.Observable;
import java.util.ResourceBundle;

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

public class HUD extends Observable implements IViewNode{

/*
 * HUD.java is the class displaying the heads-up display for the player. 
 * This contains the information the player needs in order to play the game, including money, lives, and level progress for some instances of the game.
 */


	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private VBox myVBox;
	private Selected selectedDisplay;
	private Button myBuyButton, mySellButton, myWaveButton; 
	private View myView;
	private ResourceBundle myResource;

	public HUD(View v){
		this.myView = v;
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
	}

	public VBox initialize(){
		myVBox = new VBox(20);
		myVBox.setStyle("-fx-background-color: #FEF0C9;");
		return myVBox;
	}

	public Node gold(PlayerInfo player){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResource.getString("goldImage")));
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(50);
		imageView.setPreserveRatio(true);

		HBox myHBox = new HBox();
		myHBox.setAlignment(Pos.CENTER);
		Text money = new Text(" " + player.getMoney());
		money.setStyle("-fx-font: 30px Tahoma;");
		myHBox.getChildren().addAll(imageView,money);
		return myHBox;
	}
	
	public Node selectedDisplay(){
		selectedDisplay = new Selected(myView);
		return selectedDisplay.getDisplay();
	}

	public Node lives(PlayerInfo player){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResource.getString("lives")));
		
		ImageView imageView1 = new ImageView(image);
		imageView1.setFitHeight(50);
		imageView1.setPreserveRatio(true);

		ImageView imageView2 = new ImageView(image);
		imageView2.setFitHeight(50);
		imageView2.setPreserveRatio(true);

		ImageView imageView3 = new ImageView(image);
		imageView3.setFitHeight(50);
		imageView3.setPreserveRatio(true);

		HBox myHBox = new HBox();
		myHBox.setAlignment(Pos.CENTER);
		Text lives = new Text("Lives: ");
		lives.setStyle("-fx-font: 30px Tahoma;");
		if(player.getLives() == 3){
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
	
	public Node waveButton(){
		HBox myHBox = new HBox();
		myHBox.setAlignment(Pos.CENTER);
		myWaveButton = new Button("Start Wave");
		myWaveButton.setOnMouseClicked(e->startWave());
        myHBox.getChildren().add(myWaveButton);
        return myHBox;
	}
	
	
	public Node level(PlayerInfo player){
		HBox myHBox = new HBox();
		myHBox.setAlignment(Pos.CENTER);

		Text level = new Text("Level: " + player.getLevel());
		level.setStyle("-fx-font: 30px Tahoma;");
		myHBox.getChildren().add(level);
		return myHBox;
	}
	
	public Node buySellButton(){
		
		HBox myHBox = new HBox();
		myHBox.setStyle("-fx-background-color: white;");
		myBuyButton = new Button("Buy");
		myBuyButton.setDisable(true);
        myBuyButton.setPrefSize(150,40);
        
		myBuyButton.setOnMouseClicked(e->buyButtonClicked());
		mySellButton = new Button("Sell");
		mySellButton.setDisable(true);
		mySellButton.setOnMouseClicked(e->sellButtonClicked());
        mySellButton.setPrefSize(150,40);

		myHBox.getChildren().addAll(myBuyButton,mySellButton);
		return myHBox;
		
	}
	
	public void populate(PlayerInfo player){
		myVBox.getChildren().addAll(gold(player), lives(player), level(player), buySellButton(), waveButton(), selectedDisplay());
	}
	
	private void startWave(){
		//tell game to begin wave
	}

	private void sellButtonClicked(){
		mySellButton.setDisable(true);
		//myView.sellItem();
	}

	private void buyButtonClicked() {
		myBuyButton.setDisable(true);
		//		myView.purchaseItem();
	}

	@Override
	public void setHeight(double height){
		myVBox.setPrefHeight(height);
	}

	@Override
	public void setWidth(double width){
		myVBox.setPrefWidth(width);
	}

	public void enableBuyButton(Unit unit) {
		myBuyButton.setDisable(false);
	}
	
	public void updateSelected(MapUnit myUnit){
		selectedDisplay.update(myUnit);
	}

	public void enableSell(MapUnit myUnit) {
		if (myUnit.getUnit().getClass().toString().equals("class units.Tower")){
			mySellButton.setDisable(false);
		}
		updateSelected(myUnit);
	}

}
