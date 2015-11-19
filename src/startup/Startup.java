package startup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import editor.MainGUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Startup {
	
	private final int width = 400;
	private final int height = 400;
	private GridPane myPane;
	private Stage myStage;
	private VBox myVBox;
	private List<String> myListOfGames = new ArrayList<String>();
	
	public Startup (Stage s) {
		this.myStage = s;
		this.myPane = new GridPane();
		myPane.setAlignment(Pos.CENTER);
		myVBox = new VBox();
		myVBox.setSpacing(20.0);
		myPane.getChildren().add(myVBox);
		addCreateOption();
		addLoadOption(myListOfGames);
		myStage.setScene(new Scene(myPane, width, height));
		myStage.show();
	}
	
	private void addCreateOption() {
			Button button = new Button("Create Game");
			button.setOnAction(e -> {
				myStage.close();
				new MainGUI();
			});
			myVBox.getChildren().add(button);
	}
	
	private void addLoadOption(List<String> games) {
		ChoiceBox<String> cb = new ChoiceBox<String>();
		cb.setValue("Choose a game to load");
		
		// add in existing games to choice box
		File gameFolder = new File("games");
		for (File game : gameFolder.listFiles()) {
			cb.getItems().add(game.getName());
		}
		
		Button button = new Button("Load Game");
		button.setMaxWidth(Double.MAX_VALUE);
		button.setOnAction(e-> {
			myStage.close();
			try {
				new Controller(cb.getValue());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		myVBox.getChildren().addAll(cb,button);
	}
}
