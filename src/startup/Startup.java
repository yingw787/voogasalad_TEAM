package startup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Startup {
	private Stage myStage;
	private double startSceneWidth = 400;
	
	public Startup(Stage stage) {
		myStage = stage;
	}
	
	public Scene getScene() {
		GridPane startPane = new GridPane();
		startPane.setAlignment(Pos.CENTER);
		VBox startVBox = new VBox();
		startVBox.setPadding(new Insets(20, 20, 20, 20));
		startVBox.setSpacing(20.0);
		startPane.getChildren().add(startVBox);
		
		Button editorButton = new Button("Create/Edit a Game");
		editorButton.setMaxWidth(Double.MAX_VALUE);
		editorButton.setOnAction(e-> {
			myStage.setScene(new EditorStartup(myStage).getScene());
		});

		Button playButton = new Button("Play a Game");
		playButton.setMaxWidth(Double.MAX_VALUE);
		playButton.setOnAction(e-> {
			myStage.setScene(new PlayerStartup(myStage).getScene());
		});

		startVBox.getChildren().addAll(editorButton, playButton);

		return new Scene(startPane, startSceneWidth, startPane.getPrefHeight());
	}
}
