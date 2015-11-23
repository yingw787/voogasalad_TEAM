package startup;

import controller.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerStartup {
	private Stage myStage;
	private double playerSceneWidth = 450;
	
	public PlayerStartup(Stage stage) {
		myStage = stage;
	}
	
	public Scene getScene() {
		BorderPane playerPane = new BorderPane();

		Button backButton = new Button("Back");
		backButton.setOnAction(e-> {
			myStage.setScene(new Startup(myStage).getScene());
		});
		ToolBar myToolBar = new ToolBar();
		myToolBar.getItems().add(backButton);
		myToolBar.prefWidthProperty().bind(myStage.widthProperty());
		playerPane.setTop(myToolBar);

		GamesMenu myGamesMenu = new GamesMenu();
		myGamesMenu.setPadding(new Insets(10, 0, 10, 10));
		playerPane.setCenter(myGamesMenu);
		SimpleStringProperty selected = myGamesMenu.getSelected();
		
		VBox loadBox = new VBox();
		loadBox.setPadding(new Insets(10, 10, 10, 10));
		loadBox.setSpacing(10);
		loadBox.setAlignment(Pos.CENTER);
		playerPane.setBottom(loadBox);

		Label description = new Label();
//		description.prefWidthProperty().bind(playerPane.widthProperty());
		description.setWrapText(true);
		selected.addListener(e -> {description.setText("Description for " + selected.getValue() + " would go here");});

		Button loadButton = new Button("Load");
		loadButton.disableProperty().bind(selected.isNull());
		loadButton.setOnAction(e-> {
			myStage.close();
			try {
				new Controller(selected.getValue());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		loadBox.getChildren().addAll(description, loadButton);

		return new Scene(playerPane, playerSceneWidth, playerPane.getPrefHeight());
	}
}
