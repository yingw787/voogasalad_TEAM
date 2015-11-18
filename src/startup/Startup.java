package startup;

import java.util.ArrayList;
import java.util.List;

import editor.MainGUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Startup {
	
	private final int width = 400;
	private final int height = 400;
	private GridPane myPane;
	private Stage myStage;
	private List<String> al = new ArrayList<String>();
	
	public Startup (Stage s) {
		// testing arraylist
		al.add("Game 1");
		al.add("Game 2");
		al.add("Game 3");
		this.myStage = s;
		this.myPane = new GridPane();
		addCreateOption();
		addLoadOption(al);
		myStage.setScene(new Scene(myPane, width, height));
		myStage.show();
	}
	
	private void addCreateOption() {
			VBox vbox = new VBox();
			Button button = new Button("Create Game");
			button.setOnAction(e -> {
				myStage.close();
				new MainGUI();
			});
			vbox.getChildren().add(button);
			myPane.add(vbox, 0, 0, 2, 3);
	}
	
	private void addLoadOption(List<String> games) {
		VBox vbox = new VBox(10);
		ChoiceBox<String> cb = new ChoiceBox<String>();
		cb.setValue("Choose a game to load");
		for (String game : games) {
			cb.getItems().add(game);
		}
		Button button = new Button("Load Game");
		button.setMaxWidth(Double.MAX_VALUE);
		button.setOnAction(e -> loadGame(games, (String) cb.getValue()));
		vbox.getChildren().addAll(cb, button);
		myPane.add(vbox, 0, 4, 3, 2);
	}
	
	private void loadGame(List<String> games, String game) {
		if (games.contains(game)) 
			System.out.println("Loading game "+game);
	}
}
