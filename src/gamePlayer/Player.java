package gamePlayer;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;

public class Player extends Application {
	private ResourceBundle myDefaults = ResourceBundle.getBundle("resources/Default");
	private View myView;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setWidth(Integer.parseInt(myDefaults.getString("Width")));
		stage.setHeight(Integer.parseInt(myDefaults.getString("Height")));
		myView = new View(stage);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
