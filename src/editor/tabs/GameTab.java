package editor.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
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
//	private Button myTitleButton, myHelpButton, myTroopBuyingButton, myPathVisibilityButton, myDescriptionButton;
	private VBox myButtonBox;
	private List<Button> myButtons;

	/**  Constructor for editor tab for Game data
	 **/
	public GameTab(){
		initTab();
		myTabContent.getChildren().clear(); // find a better way to do this later
		myLabel = new Text("Game Data");
		myLabel.setFont(Font.font("Verdana", 30));
		myButtons = new ArrayList<Button>();
		myButtonBox = new VBox();
		myButtonBox.getChildren().addAll(myButtons);
		myTabContent.getChildren().addAll(myLabel, myButtonBox);
		clearAttributes();
	}

	private void initializeAttributes(){
		myButtons.add(makeButton("Game title: " + myData.getGame().getTitle(), e -> changeTitle()));
		myButtons.add(makeButton("Game help page: " + myData.getGame().getHelpPage(), e -> changeHelpPage()));
		myButtons.add(makeButton("Player can buy troops: " + myData.getGame().getBuyTroopOption(), e -> changeTroopBuyingOption()));
		myButtons.add(makeButton("Player see path: " + myData.getGame().getPathVisibility(), e -> changePathVisibility()));
		myButtons.add(makeButton("Game description: " + myData.getGame().getDescription(), e -> changeDescription()));
		for (Button button : myButtons) {
			button.setStyle("-fx-padding: 0 0 0 0;" + "-fx-background-color: transparent;");
		}
		myButtonBox.getChildren().addAll(myButtons);
	}
	
	private void changePathVisibility() {
		String result = askUser(new String[]{"Yes", "No"}, "Can the user see the path the troops take?");
		if(result.equals("Yes")){
			myData.getGame().setPathVisibility(true);
		}
		else {
			myData.getGame().setPathVisibility(false);
		}
		refresh();
	}

	private void changeTroopBuyingOption() {
		String result = askUser(new String[]{"Yes", "No"}, "Can the user buy troops?");
		if(result.equals("Yes")){
			myData.getGame().setBuyTroopOption(true);
		}
		else {
			myData.getGame().setBuyTroopOption(false);
		}
		refresh();
	}

	private void clearAttributes(){
		myButtons.clear();
		myButtonBox.getChildren().clear();
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
	
	private void changeDescription() {
		String newDescription = "";
		Optional<String> result = askUserForText("Change Attribute", "Please Enter a New Description for this Game", "");
		if (result.isPresent()){
			newDescription = result.get();
		} else return;
		myData.getGame().setDescription(newDescription);
		refresh();
	}

	
	private List<String> dialogData;
	private ChoiceDialog<String> dialog;
	private String askUser(String[] choices, String type){
		dialogData = Arrays.asList(choices);

		dialog = new ChoiceDialog<String>(dialogData.get(0), dialogData);
		dialog.setTitle("New Rule");
		dialog.setHeaderText(type);

		Optional<String> result = dialog.showAndWait();
		String ret = "cancelled.";
				
		if (result.isPresent()) {
		    ret = result.get();
		}
		return ret;
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
