package editor.tabs;

import java.util.Observable;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import editor.IView;
import editor.tabData.GameData;
import editor.tabData.ITabData;

public class GameTab extends ATab implements IView, ITab {
	private GameData myData;
	private Text myLabel;
	private Button myTitleButton;
	private Button myHelpButton;
	
	public GameTab(){
		initTab();
		myLabel = new Text("Game Data");
		myLabel.setFont(Font.font("Verdana", 30));
		myTabContent.getChildren().add(myLabel);
		clearAttributes();
	}
	
	
	private void initializeAttributes(){
		myTitleButton = new Button("Game title: " + myData.getGame().getTitle());
		myTitleButton.setStyle("-fx-padding: 0 0 0 0;"
				+ "-fx-background-color: transparent;");
		myTitleButton.setOnAction(e -> changeTitle());
		myHelpButton = new Button("Game help page: " + myData.getGame().getHelpPage());
		myHelpButton.setStyle("-fx-padding: 0 0 0 0;"
				+ "-fx-background-color: transparent;");
		myHelpButton.setOnAction(e -> changeHelpPage());
		myTabContent.getChildren().addAll(myTitleButton, myHelpButton);
	}
	
	private void clearAttributes(){
		myTabContent.getChildren().removeAll(myTitleButton, myHelpButton);
	}

	private void refresh(){
		clearAttributes();
		initializeAttributes();
	}
	
	private void changeHelpPage() {
		String newUrl = "";
		Optional<String> result = askUserForText("Change Attribute", "Please Enter a new Url for the help page of this game", "");
		if (result.isPresent()){
			newUrl = result.get();
		} else return;
		myData.getGame().setHelpPage(newUrl);
		refresh();
	}


	private void changeTitle() {
		String newTitle = "";
		Optional<String> result = askUserForText("Change Attribute", "Please Enter a New Title for this Game", "");
		if (result.isPresent()){
			newTitle = result.get();
		} else return;
		myData.getGame().setTitle(newTitle);
		refresh();
	}

	private Optional<String> askUserForText(String title, String header, String content) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		Optional<String> result = dialog.showAndWait();
		return result;
	}

	@Override
	public void setData(ITabData data) {
		myData = (GameData) data;
		initializeAttributes();
	}

}
