package editor.tabs;

import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import editor.IView;
import editor.tabData.ITabData;
import editor.tabData.PlayerData;

public class PlayerTab extends ATab implements IView, ITab {
	private PlayerData myData;
	private Text myLabel;
	private Button myMoneyButton;
	private Button myLivesButton;
	
	public PlayerTab(){
		initTab();
		myTabContent.getChildren().clear(); 
		myLabel = new Text("Player Information");
		myLabel.setFont(Font.font("Verdana", 30));
		myTabContent.getChildren().add(myLabel);
		clearAttributes();
	}

	private void initializeAttributes(){
		myMoneyButton = makeButton("Initial money amount: " + myData.getPlayerInfo().getMoney(), e -> changeMoney());
		myMoneyButton.setStyle("-fx-padding: 0 0 0 0;"
				+ "-fx-background-color: transparent;");
		myLivesButton = makeButton("Total number of lives: " + myData.getPlayerInfo().getLives(), e -> changeLives());
		myLivesButton.setStyle("-fx-padding: 0 0 0 0;"
				+ "-fx-background-color: transparent;");
		myTabContent.getChildren().addAll(myMoneyButton, myLivesButton);
	}
	
	private void clearAttributes(){
		myTabContent.getChildren().removeAll(myMoneyButton, myLivesButton);
	}

	private void refresh(){
		clearAttributes();
		initializeAttributes();
	}
	
	private void changeLives() {
		String newLives = "";
		Optional<String> result = askUserForText("Change Attribute", "Please Enter a New Lives Amount for this Game", "");
		if (result.isPresent()){
			newLives = result.get();
		} else return;
		myData.getPlayerInfo().setLives(Integer.parseInt(newLives));
		refresh();
	}


	private void changeMoney() {
		String newMoney = "";
		Optional<String> result = askUserForText("Change Attribute", "Please Enter a New Initial Money Amount for this Game", "");
		if (result.isPresent()){
			newMoney = result.get();
		} else return;
		myData.getPlayerInfo().setMoney(Integer.parseInt(newMoney));
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
		myData = (PlayerData) data;
		initializeAttributes();
	}

}
