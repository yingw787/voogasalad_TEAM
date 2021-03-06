package editor.tabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import units.Level;
import units.Path;
import units.Troop;
import editor.IView;
import editor.tabData.ITabData;
import editor.tabData.LevelsData;
import editor.tabData.PathsData;
import editor.tabData.TroopsData;
import image.ImageMaker;

/**  Editor tab for Levels
 **/
public class LevelsTab extends ATab implements IView, ITab{
	private LevelsData myData;
	private Button myAddButton;
	private Button myDeleteButton;
	private List<Troop> myWave;
	private List<String> myPaths;
	private double mySpawnRate;
	private double mySpeed;
	private int myLevelID;// = 1;

	/**  Constructor for editor tab for Levels
	 **/
	public LevelsTab(){
		initTab();
		createButtons();
		myLevelID = 0;
		myWave = new ArrayList<Troop>();
		myPaths = new ArrayList<String>();
	}
	
	private void createButtons() {
		myAddButton = makeButton("Add New Level", e-> addLevel());
		myDeleteButton = makeButton("Delete Level", e-> deleteLevel());
		myButtons.getChildren().addAll(myAddButton, myDeleteButton);
	}
	
	private void addLevel() {
		Stage stage = new Stage();
		BorderPane levelPane = new BorderPane();
		Scene levelScene = new Scene(levelPane, 500, 300);
		HBox instructions = new HBox(150);
		ScrollPane troopOptions = new ScrollPane();
		ScrollPane chosenTroops = new ScrollPane();
		FlowPane possibleTroops = new FlowPane();
		FlowPane selectedTroops = new FlowPane();
		Label note = new Label("Choose troops to add to level:");
		Button finish = new Button("Finalize Level");
		finish.setOnAction(e -> setAttributes(stage));
		instructions.getChildren().addAll(note, finish);
		ArrayList<String> troopsList = new ArrayList<String>(TroopsData.myTroops.keySet());
		Collections.sort(troopsList);
		if (TroopsData.myTroops.keySet().size() == 0) {
			Label alert = new Label("You have not created any troops yet!");
			possibleTroops.getChildren().add(alert);
		}
		else {
			for (String troop : troopsList) {
				populateTroops(troop, possibleTroops, selectedTroops);
			}
		}
		troopOptions.setContent(possibleTroops);
		chosenTroops.setContent(selectedTroops);
		levelPane.setPadding(new Insets(20, 20, 20, 20));
		levelPane.setTop(instructions);
		levelPane.setCenter(chosenTroops);
		levelPane.setBottom(troopOptions);
		stage.setScene(levelScene);
		stage.show();
	}
	
	private void populateTroops(String troop, FlowPane options, FlowPane selected) {
		Image img = ImageMaker.getImage(TroopsData.myTroops.get(troop).getStringAttribute("Image"));
		ImageView troopImage = new ImageView(img);
		troopImage.setPreserveRatio(true);
		troopImage.setFitHeight(20);
		Button button = new Button(troop);
		button.setContentDisplay(ContentDisplay.TOP);
		button.setGraphic(troopImage);
		button.setOnAction(e -> addTroopToLevel(selected, troop));
		options.getChildren().add(button);
	}
	
	private void addTroopToLevel(FlowPane selectedTroops, String troop) {
		Troop selectedTroop = TroopsData.myTroops.get(troop);
		ImageView troopImg = new ImageView(ImageMaker.getImage(selectedTroop.getStringAttribute("Image")));
		troopImg.setPreserveRatio(true);
		troopImg.setFitHeight(50);
		myWave.add(selectedTroop);
		selectedTroops.getChildren().add(troopImg);
	}
	
	private void deleteLevel() {
		String selected = myEntriesList.getSelectionModel().getSelectedItem();
		if (selected == null) {
			return;
		}
		myEntriesToShow.remove(selected);
		String selectedLevel = selected.split(" ")[1];
		myData.remove(selectedLevel);
	}
	
	private void finishLevel(Stage s) {
		if (myWave.size() == 0) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR, "You cannot create a level with no troops!");
			errorAlert.show();
		}
		else {
		Level l = new Level(Integer.toString(myLevelID), new ArrayList<Troop>(myWave), 
				new ArrayList<String>(this.myPaths), mySpawnRate, mySpeed);
		myEntriesToShow.add("Level "+l.getName() + " [" + myWave.size() + " troops]");
		myData.add(l.getName(), l);
		myLevelID++;
		refresh();
		Alert finishAlert = new Alert(Alert.AlertType.CONFIRMATION, "Level "+l.getName()+" has been created!");
		finishAlert.show();
		}
		s.close();
	}
	
	private void setAttributes(Stage stage) {
		VBox levelAttributes = new VBox(10);
		HBox pathAttributes = new HBox(20);
		Scene levelScene = new Scene(levelAttributes, 300, 200);
		Button spawnButton = new Button("Spawn Rate: ");
		Button speedButton = new Button("Troop Speed: ");
		Button confirmButton = new Button("Add Path");
		Button finishButton = new Button("Finish Level");
		spawnButton.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Change Spawn Rate");
			dialog.setHeaderText("Changing value for spawn rate:");
			dialog.setContentText("Please enter a new value:");
			Optional<String> result = dialog.showAndWait();
				try{
					mySpawnRate = Double.parseDouble(result.get());
					spawnButton.setText("Spawn Rate: "+mySpawnRate);
				} catch(Exception excep){
					Alert warning = new Alert(AlertType.INFORMATION);
					warning.setTitle("Warning");
					warning.setHeaderText("Invalid value");
					warning.setContentText("Only numbers allowed.");
					warning.show();
				}
		});
		speedButton.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Change Troop Speed");
			dialog.setHeaderText("Changing value for troop speed:");
			dialog.setContentText("Please enter a new value:");
			Optional<String> result = dialog.showAndWait();
				try{
					mySpeed = Double.parseDouble(result.get());
					speedButton.setText("Troop Speed: "+mySpeed);
				} catch(Exception excep){
					Alert warning = new Alert(AlertType.INFORMATION);
					warning.setTitle("Warning");
					warning.setHeaderText("Invalid value");
					warning.setContentText("Only numbers allowed.");
					warning.show();
				}
		});
		spawnButton.setStyle("-fx-padding: 0 0 0 0;" + "-fx-background-color: transparent;");
		speedButton.setStyle("-fx-padding: 0 0 0 0;" + "-fx-background-color: transparent;");
		finishButton.setOnAction(e-> {
				if (myPaths.size() == 0) {
					Alert error = new Alert(AlertType.ERROR, "You must select at least one path for this level!");
					error.show();
				}
				else {
				finishLevel(stage);
				}
		});
		Label selectPaths = new Label("Choose the paths for this level:");
		ChoiceBox<String> levelPaths = new ChoiceBox<String>();
		for (String path : PathsData.myPaths.keySet()) {
			levelPaths.getItems().add(path);
		}
		pathAttributes.getChildren().addAll(levelPaths, confirmButton);
		confirmButton.setOnAction(e-> {
		myPaths.add(levelPaths.getValue());
		levelPaths.getItems().remove(levelPaths.getValue());
		});
		levelAttributes.getChildren().addAll(spawnButton, speedButton, selectPaths, pathAttributes, finishButton);
		levelAttributes.setPadding(new Insets(20, 20, 20, 20));
		stage.setScene(levelScene);
		stage.show();
	}
	
	private void refresh() {
		mySpawnRate = 1.0;
		mySpeed = 1.0;
		myWave.clear();
		myPaths.clear();
	}
	
	@Override
	public Node getView() {
		return myTabView;
	}

	@Override
	public void setData(ITabData data) {
		myData = (LevelsData) data;
		for (Object o : myData.getData()) {
			Level level = (Level) o;
			myEntriesToShow.add("Level "+ level.getName() + " [" + level.getTroops().size() + " troops]");
			myLevelID++;
		}
	}

}
