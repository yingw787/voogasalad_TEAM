package gamePlayer;

import java.util.ResourceBundle;

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
	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	
	private Text unit;
	private Text health;
	private ProgressBar healthbar;
	private Text sell;
	private ImageView image;
	private ResourceBundle myResource;
	
	public Selected(Player p){
		this.myPlayer = p;
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
		myVBox = new VBox();
		myHBox = new HBox();
		
		unit = new Text("Unit: ");
		health = new Text("Health: ");
		healthbar = new ProgressBar(0);
		sell = new Text ("Sell for: ");
		unit.setStyle(myResource.getString("selectedStyle"));
		health.setStyle(myResource.getString("selectedStyle"));
		sell.setStyle(myResource.getString("selectedStyle"));
		image = new ImageView();
		myVBox.setStyle(myResource.getString("selectedVBoxStyle"));
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
