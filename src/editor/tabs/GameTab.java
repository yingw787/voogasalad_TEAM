package editor.tabs;

import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import editor.IView;
import editor.tabData.GameData;
import editor.tabData.ITabData;

/**  Editor tab for Game data
 **/
public class GameTab extends ATab implements IView, ITab {
	private GameData myData;
	private Text myLabel;
	private Button myTitleButton;
	private Button myIconButton;
	private Button myHelpButton;
	
	/**  Constructor for editor tab for Game data
	 **/
	public GameTab(){
		initTab();
		myTabContent.getChildren().clear(); // find a better way to do this later
		myLabel = new Text("Game Data");
		myLabel.setFont(Font.font("Verdana", 30));
		myTabContent.getChildren().add(myLabel);
		clearAttributes();
	}

	private void initializeAttributes(){
		myTitleButton = makeButton("Game title: " + myData.getGame().getTitle(), e -> changeTitle());
		myTitleButton.setStyle("-fx-padding: 0 0 0 0;"
				+ "-fx-background-color: transparent;");
		
		myIconButton = new Button();
		ImageView myIcon = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("purpleminion.png")));
		myIcon.setFitHeight(50);
		myIcon.setPreserveRatio(true);
		myIconButton.setGraphic(myIcon);
		myIconButton.setText("my icon");
		myIconButton.setStyle("-fx-padding: 0 0 0 0;"
				+ "-fx-background-color: transparent;");
		myIconButton.setContentDisplay(ContentDisplay.TOP);
		
		myHelpButton = makeButton("Game help page: " + myData.getGame().getHelpPage(), e -> changeHelpPage());
		myHelpButton.setStyle("-fx-padding: 0 0 0 0;"
				+ "-fx-background-color: transparent;");
		myTabContent.getChildren().addAll(myTitleButton, myIconButton, myHelpButton);
	}
	
	
	
	
	private void clearAttributes(){
		myTabContent.getChildren().removeAll(myTitleButton, myIconButton, myHelpButton);
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
