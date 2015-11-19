package startup;

import java.io.File;

import controller.Controller;
import editor.MainGUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Startup {
	private Stage myStage;
	
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

		return new Scene(startPane, 400, 400);
	}
	
	private Scene createEditorScene() {
		GridPane editorPane = new GridPane();
		editorPane.setAlignment(Pos.CENTER);
		VBox editorVBox= new VBox();
		editorVBox.setSpacing(20.0);
		editorPane.getChildren().add(editorVBox);
		
		Button backButton = new Button("Back");
		backButton.setMaxWidth(Double.MAX_VALUE);
		backButton.setOnAction(e-> {
			myStage.setScene(createStartScene());
		});
		editorVBox.getChildren().add(backButton);
		
		Button createButton = new Button("Create New Game");
		createButton.setOnAction(e -> {
			myStage.close();
			new MainGUI();
		});
		editorVBox.getChildren().add(createButton);

		return new Scene(editorPane, 400, 400);
	}
	
	private Scene createPlayerScene() {
		GridPane playerPane = new GridPane();
		playerPane.setAlignment(Pos.CENTER);
		VBox playerVBox= new VBox();
		playerVBox.setSpacing(20.0);
		playerPane.getChildren().add(playerVBox);
		
		Button backButton = new Button("Back");
		backButton.setMaxWidth(Double.MAX_VALUE);
		backButton.setOnAction(e-> {
			myStage.setScene(createStartScene());
		});
		playerVBox.getChildren().add(backButton);
		
		ChoiceBox<String> cb = new ChoiceBox<String>();
		cb.setValue("Choose a game to load");
		File gameFolder = new File("games");
		for (File game : gameFolder.listFiles()) {
			cb.getItems().add(game.getName());
		}
		playerVBox.getChildren().add(cb);
		
		Button loadButton = new Button("Load Game");
		loadButton.setMaxWidth(Double.MAX_VALUE);
		loadButton.setOnAction(e-> {
			myStage.close();
			try {
				new Controller(cb.getValue());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		playerVBox.getChildren().add(loadButton);
		
		return new Scene(playerPane, 400, 400);
	}
}
