package startup;

import java.io.File;

import controller.Controller;
import editor.MainGUI;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Startup {
	private Stage myStage;
	private double startSceneWidth = 400;
	private double startSceneHeight = 100;
	private double editorSceneWidth = 400;
	private double editorSceneHeight = 200;
	private double playerSceneWidth = 400;
	private double playerSceneHeight = 200;
	
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

		return new Scene(startPane, startSceneWidth, startSceneHeight);
	}
	
	private Scene createEditorScene() {
		GridPane editorPane = new GridPane();

		Button backButton = new Button("Back");
		backButton.setOnAction(e-> {
			myStage.setScene(createStartScene());
		});
		ToolBar myToolBar = new ToolBar();
		myToolBar.getItems().add(backButton);
		myToolBar.prefWidthProperty().bind(myStage.widthProperty());
		editorPane.add(myToolBar, 0, 0);

		VBox editorVBox= new VBox();
		editorVBox.setSpacing(20.0);
		editorVBox.setAlignment(Pos.CENTER);
		editorPane.add(editorVBox, 0, 1);
		
		GamesMenu myGamesMenu = new GamesMenu();
		editorVBox.getChildren().add(myGamesMenu);
		
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(10.0);
		buttonBox.setAlignment(Pos.CENTER);
		editorVBox.getChildren().add(buttonBox);

		Button createButton = new Button("Create New Game");
		createButton.setOnAction(e -> {
			myStage.close();
			new MainGUI();
		});
		buttonBox.getChildren().add(createButton);
		
		Button editButton = new Button("Edit Selected Game");
		editButton.setOnAction(e -> {
			// TODO: implement edit ability for existing games
		});
		buttonBox.getChildren().add(editButton);
		
		SimpleStringProperty selected = myGamesMenu.getSelected();
		selected.addListener(e -> {editButton.setText("Edit " + selected.get());});
//		Label test = new Label();
//		test.textProperty().bind(selected);
//		buttonBox.getChildren().add(test);

		return new Scene(editorPane, editorSceneWidth, editorSceneHeight);
	}
	
	private Scene createPlayerScene() {
		GridPane playerPane = new GridPane();

		Button backButton = new Button("Back");
		backButton.setOnAction(e-> {
			myStage.setScene(createStartScene());
		});
		ToolBar myToolBar = new ToolBar();
		myToolBar.getItems().add(backButton);
		myToolBar.prefWidthProperty().bind(myStage.widthProperty());
		playerPane.add(myToolBar, 0, 0);

		VBox playerVBox = new VBox();
		playerVBox.setSpacing(20.0);
		playerVBox.setAlignment(Pos.CENTER);
		playerPane.add(playerVBox, 0, 1);
		
		GamesMenu myGamesMenu = new GamesMenu();
		playerVBox.getChildren().add(myGamesMenu);
		
		SimpleStringProperty selected = myGamesMenu.getSelected();
//		Label test = new Label();
//		test.textProperty().bind(selected);
//		playerVBox.getChildren().add(test);
		
		Button loadButton = new Button("Load This Game");
		loadButton.setOnAction(e-> {
			myStage.close();
			try {
				new Controller(selected.getValue());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		selected.addListener(e -> {loadButton.setText("Load " + selected.get());});
		playerVBox.getChildren().add(loadButton);
		
		return new Scene(playerPane, playerSceneWidth, playerSceneHeight);
	}
}
