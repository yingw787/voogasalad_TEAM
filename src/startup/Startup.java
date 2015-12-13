package startup;

import image.ImageMaker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Startup {
	private Stage myStage;
	private GridPane myStartPane;
	
	public Startup(Stage stage) {
		myStage = stage;
		GridPane startPane = new GridPane();
		startPane.setAlignment(Pos.CENTER);
		VBox startVBox = new VBox();
		startVBox.setPadding(new Insets(20, 20, 20, 20));
		startVBox.setSpacing(20.0);
		startPane.getChildren().add(startVBox);
		
		ImageView title = new ImageView(ImageMaker.getImage("voogasaladflames.gif")); //voogasaladglitter.gif
		
		Button editorButton = new Button("Create/Edit a Game");
		editorButton.setOnAction(e-> {
			myStage.setScene(new EditorStartup(myStage).getScene());
		});

		Button playButton = new Button("Play a Game");
		playButton.setOnAction(e-> {
			myStage.setScene(new PlayerStartup(myStage).getScene());
		});

		startVBox.getChildren().addAll(title, editorButton, playButton);
		editorButton.setAlignment(Pos.CENTER);
		playButton.setAlignment(Pos.CENTER);
		startVBox.setAlignment(Pos.CENTER);
		myStartPane = startPane;
	}
	
	public Scene getScene() {
		return new Scene(myStartPane, myStartPane.getPrefWidth(), myStartPane.getPrefHeight());
	}
}
