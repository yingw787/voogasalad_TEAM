package gamePlayer;

import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/*
 * Manages the part of the HUD that displays selected unit
 */

public class Selected {

	@SuppressWarnings("unused")
	private Player myPlayer;
	private VBox myVBox;
	private HBox myHBox;
	
	private Text unit;
	private Text health;
	private ProgressBar healthbar;
	private Text sell;
	private ImageView image;
	
	public Selected(Player p){
		this.myPlayer = p;
		myVBox = new VBox();
		myHBox = new HBox();
		
		unit = new Text("Unit: ");
		health = new Text("Health: ");
		healthbar = new ProgressBar(0);
		sell = new Text ("Sell for: ");
		unit.setStyle("-fx-font: 14px Tahoma;");
		health.setStyle("-fx-font: 14px Tahoma;");
		sell.setStyle("-fx-font: 14px Tahoma;");
		image = new ImageView();
		myVBox.setStyle("-fx-border-width: 5px; -fx-padding: 10; -fx-spacing: 8;");
		myVBox.getChildren().addAll(unit, health, healthbar, sell);
		myHBox.getChildren().addAll(myVBox,image);
	}
	
	
	/*
	 * Updates the display of attributes given the selected unit
	 */
	public void update(MapUnit myUnit){
		unit.setText("Unit: " + myUnit.getUnit().getStringAttribute("Name"));
		healthbar.setProgress(myUnit.getUnit().getAttribute("Health")/myUnit.getUnit().getAttribute("MaxHealth"));
		health.setText("Health: " + myUnit.getUnit().getAttribute("Health") 
				+ "/" + myUnit.getUnit().getAttribute("MaxHealth"));
		sell.setText("Sell for: " + (int) myUnit.getUnit().getAttribute("SellCost"));

	}
	
	public void setImage(MapUnit myUnit){
		image.setImage(new Image(myUnit.getUnit().getStringAttribute("Image")));
		image.setPreserveRatio(true);
		image.setFitHeight(55);
	}
	
	public Node getDisplay(){
		return myHBox;
	}
	
}
