package startup;

import java.io.File;

import editor.MainGUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditorStartup {
	private Stage myStage;
	private double editorSceneWidth = 400;
	
	public EditorStartup(Stage stage) {
		myStage = stage;
	}
	
	public Scene getScene() {
		BorderPane editorPane = new BorderPane();

		Button backButton = new Button("Back");
		backButton.setOnAction(e-> {
			myStage.setScene(new Startup(myStage).getScene());
		});
		ToolBar myToolBar = new ToolBar();
		myToolBar.getItems().add(backButton);
		myToolBar.prefWidthProperty().bind(myStage.widthProperty());
		editorPane.setTop(myToolBar);

		VBox editorVBox= new VBox();
		editorVBox.setPadding(new Insets(20, 20, 20, 20));
		editorVBox.setSpacing(20.0);
		editorVBox.setAlignment(Pos.CENTER);
		editorPane.setCenter(editorVBox);

		Button createButton = new Button("Create New Game");
		createButton.setOnAction(e -> {
			myStage.close();
			new MainGUI(null);
		});
		editorVBox.getChildren().add(createButton);

		editorVBox.getChildren().add(new Label("OR"));
		
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(10.0);
		buttonBox.setAlignment(Pos.CENTER);
		editorVBox.getChildren().add(buttonBox);
		
		buttonBox.getChildren().add(new Label("Select a game to edit: "));
		
		ChoiceBox<String> gameChoiceBox = new ChoiceBox<String>();
		File gameFolder = new File("games");
		for (File game : gameFolder.listFiles()) {
			gameChoiceBox.getItems().add(game.getName());
		}
		buttonBox.getChildren().add(gameChoiceBox);
		
		Button editButton = new Button("Go ->");
		editButton.disableProperty().bind(gameChoiceBox.getSelectionModel().selectedItemProperty().isNull());
		editButton.setOnAction(e -> {
			// TODO: implement edit ability for existing games
			myStage.close();
			new MainGUI(gameChoiceBox.getSelectionModel().getSelectedItem());
			
//			new Alert(AlertType.ERROR, "We still need to implement this lol").show();
		});
		buttonBox.getChildren().add(editButton);

		return new Scene(editorPane, editorSceneWidth, editorPane.getPrefHeight());
	}
}
