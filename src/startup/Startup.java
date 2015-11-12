package startup;

import java.beans.EventHandler;

import editor.MainGUI;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Startup {
	
	private final int width = 400;
	private final int height = 400;
	private Pane myPane;
	private Stage myStage;
	
	public Startup (Stage s) {
		this.myStage = s;
		this.myPane = new Pane();
		addButton(myPane, "Create Game");
		myStage.setScene(new Scene(myPane, width, height));
		myStage.show();
	}
	
	private void addButton(Pane p, String label) {
			Button button = new Button(label);
			button.setOnAction(e -> {
				myStage.close();
				new MainGUI();
			});
			myPane.getChildren().add(button);
	}
}
