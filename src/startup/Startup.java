package startup;

import java.io.File;

import controller.Controller;
import editor.MainGUI;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Startup {
	private Stage myStage;
	private double startSceneWidth = 400;
	private double editorSceneWidth = 400;
	private double playerSceneWidth = 400;
	
	// i know there is a lot of duplicated code right now but i will refactor later!!! - susan
	
	public Startup (Stage s) {
		this.myStage = s;
		myStage.setScene(createStartScene());
		myStage.show();
	}
	
	private Scene createStartScene() {
		GridPane startPane = new GridPane();
		startPane.setAlignment(Pos.CENTER);
		VBox startVBox = new VBox();
		startVBox.setPadding(new Insets(20, 20, 20, 20));
		startVBox.setSpacing(20.0);
		startPane.getChildren().add(startVBox);
		
		Button editorButton = new Button("Create/Edit a Game");
		editorButton.setMaxWidth(Double.MAX_VALUE);
		editorButton.setOnAction(e-> {
			myStage.setScene(createEditorScene());
		});

		Button playButton = new Button("Play a Game");
		playButton.setMaxWidth(Double.MAX_VALUE);
		playButton.setOnAction(e-> {
			myStage.setScene(createPlayerScene());
		});

		startVBox.getChildren().addAll(editorButton, playButton);

		return new Scene(startPane, startSceneWidth, startPane.getPrefHeight());
	}
	
	private Scene createEditorScene() {
		BorderPane editorPane = new BorderPane();

		Button backButton = new Button("Back");
		backButton.setOnAction(e-> {
			myStage.setScene(createStartScene());
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
			new MainGUI();
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
			new Alert(AlertType.ERROR, "We still need to implement this lol").show();
		});
//		gameChoiceBox.getSelectionModel().selectedItemProperty().addListener(									//dynamically change button text
//				e -> {editButton.setText("Edit " + gameChoiceBox.getSelectionModel().getSelectedItem());});
		buttonBox.getChildren().add(editButton);

		return new Scene(editorPane, editorSceneWidth, editorPane.getPrefHeight());
	}
	
	private Scene createPlayerScene() {
		BorderPane playerPane = new BorderPane();

		Button backButton = new Button("Back");
		backButton.setOnAction(e-> {
			myStage.setScene(createStartScene());
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
