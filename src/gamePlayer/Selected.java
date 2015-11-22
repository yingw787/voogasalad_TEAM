package gamePlayer;

import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Selected {

	private Player myPlayer;
	private VBox myVBox;
	
	private Text unit;
	private Text health;
	private ProgressBar healthbar;
	private Text sell;
	
	public Selected(Player p){
		this.myPlayer = p;
		myVBox = new VBox();
		
		unit = new Text("Unit: ");
		health = new Text("Health: ");
		healthbar = new ProgressBar(0);
		sell = new Text ("Sell for: ");
		unit.setStyle("-fx-font: 18px Tahoma;");
		health.setStyle("-fx-font: 18px Tahoma;");
		sell.setStyle("-fx-font: 18px Tahoma;");
		myVBox.setStyle("-fx-border-width: 5px; -fx-padding: 10; -fx-spacing: 8;");
		myVBox.getChildren().addAll(unit, health, healthbar, sell);
	}
	
	public void update(MapUnit myUnit){
		unit.setText("Unit: " + myUnit.getUnit().getStringAttribute("Name"));
		healthbar.setProgress(myUnit.getUnit().getAttribute("Health")/myUnit.getUnit().getAttribute("MaxHealth"));
		health.setText("Health: " + myUnit.getUnit().getAttribute("Health") 
				+ "/" + myUnit.getUnit().getAttribute("MaxHealth"));
		sell.setText("Sell for: " + (int) myUnit.getUnit().getAttribute("SellCost"));
	}
	
	public Node getDisplay(){
		return myVBox;
	}
	
}
