package editor.tabs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import editor.IView;
import editor.tabData.GameData;
import editor.tabData.ITabData;
import image.ImageMaker;

/**  Editor tab for Game data
 **/
public class GameTab extends ATab implements IView, ITab {
	private GameData myData;
	private Text myLabel;
	private VBox myButtonBox;
	private List<Node> myButtons;

	/**  Constructor for editor tab for Game data
	 **/
	public GameTab(){
		initTab();
		myTabContent.getChildren().clear(); // find a better way to do this later
		myLabel = new Text("Game Data");
		myLabel.setFont(Font.font("Verdana", 30));
		myButtons = new ArrayList<Node>();
		myButtonBox = new VBox();
		myButtonBox.getChildren().addAll(myButtons);
		myTabContent.getChildren().addAll(myLabel, myButtonBox);
		clearAttributes();
	}

	private void initializeAttributes(){
		myButtons.add(makeButton("Game title: " + myData.getGame().getTitle(), e -> changeTitle()));
		myButtons.add(makeImageButton(myData.getGame().getImage()));
		myButtons.add(makeButton("Game help page: " + myData.getGame().getHelpPage(), e -> changeHelpPage()));
		myButtons.add(makeButton("Player can buy troops: " + myData.getGame().getBuyTroopOption(), e -> changeTroopBuyingOption()));
		myButtons.add(makeButton("Player see path: " + myData.getGame().getPathVisibility(), e -> changePathVisibility()));
		myButtons.add(makeButton("Game description: " + myData.getGame().getDescription(), e -> changeDescription()));
		
		for (Node button : myButtons) {
			button.setStyle("-fx-padding: 0 0 0 0;" + "-fx-background-color: transparent;");
		}
		myButtonBox.getChildren().addAll(myButtons);
	}
	
	private Node makeImageButton(String imageName) {
		Label label = new Label();
		List<String> imageSuffixList = new ArrayList<String>();
		for (String suffix : ImageIO.getReaderFileSuffixes()) {
			imageSuffixList.add("*." + suffix);
		}
		Button imageButton = new Button("Game image:");
		imageButton.setStyle("-fx-padding: 0 0 0 0;" + "-fx-background-color: transparent;");
		if (imageName != null) {
			label.setText("Game image: ");
			ImageView myImage;
			myImage = new ImageView(ImageMaker.getImage(imageName));
			myImage.setFitHeight(50);
			myImage.setPreserveRatio(true);
			imageButton.setGraphic(myImage);
			imageButton.setText(imageName);
		}
		imageButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Load Image From File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", imageSuffixList));
			File selectedFile = fileChooser.showOpenDialog(new Stage());
			if (selectedFile != null) {
					ImageMaker.uploadImage(selectedFile);
					myData.getGame().setImage(selectedFile.getName());
			}
			refresh();
		});
		HBox imageButtonBox = new HBox();
		imageButtonBox.getChildren().addAll(label, imageButton);
		return imageButtonBox;
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
