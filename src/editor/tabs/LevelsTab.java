package editor.tabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import units.Level;
import units.Troop;
import editor.IView;
import editor.tabData.ITabData;
import editor.tabData.LevelsData;
import editor.tabData.PathsData;
import editor.tabData.TroopsData;

public class LevelsTab extends Observable implements IView, ITab{
	private ScrollPane myTabView;
	private VBox myTabContent;
	private LevelsData myData;
	private List<Troop> myWave;
	private Button myAddButton;
	private Button myDeleteButton;
	private ListView<String> myPathEntriesList;
	private ObservableList<String> myEntriesToShow;
	private double mySpawnRate;
	private double mySpeed;
	private int myCurrentLevel = 1;

	public LevelsTab(){
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);
		myWave = new ArrayList<Troop>();
	}
	
	private void initializeButtons() {
		HBox buttons = new HBox();
		myAddButton = new Button("Add New Level");
		myAddButton.setOnAction(e-> addLevel());
		myDeleteButton = new Button("Delete Level");
		myDeleteButton.setOnAction(e-> deleteLevel());
		buttons.getChildren().addAll(myAddButton, myDeleteButton);
		buttons.setAlignment(Pos.BOTTOM_RIGHT); 
		myTabContent.getChildren().add(buttons);
	}
	
	private void initializeLevels() {
		myEntriesToShow = FXCollections.observableArrayList();
		myPathEntriesList = new ListView<String>(myEntriesToShow);
		myPathEntriesList.setMinWidth(432);
		myTabContent.getChildren().add(myPathEntriesList);
	}
	
	private void addLevel() {
		askSpawnRate();
	}
	
	private void deleteLevel() {
		String selected = myPathEntriesList.getSelectionModel().getSelectedItem();
		if (selected == null) {
			return;
		}
		myEntriesToShow.remove(selected);
		String selectedLevel = selected.split(" ")[1];
		myData.remove(selectedLevel);
	}
	
	@SuppressWarnings("unchecked")
	private void selectTroop() {
		@SuppressWarnings("rawtypes")
		ChoiceDialog dialog = new ChoiceDialog();
		
		// put existing troops into choice box, sorted alphabetically
		List<String> troopsList = new ArrayList<String>();
		troopsList.addAll(TroopsData.myTroops.keySet());
		Collections.sort(troopsList);
		for (String troop : troopsList) {
			dialog.getItems().add(troop);
		}
		dialog.setTitle("Choose Enemy Troop");
		dialog.setHeaderText("Choose Enemy Troop For Wave");
		dialog.setContentText("Choose a created troop to add to the wave:");
		Optional<String> result = dialog.showAndWait();
		String ret = "cancelled";
		if (result.isPresent()) {
			ret = result.get();
			selectTroopQuantity(ret);
		}
	}
	
	private void selectTroopQuantity(String troopName) {
		// ask user to input the number of troops to add
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Choose Troop Quantity");
		dialog.setHeaderText("Choose the number of " + troopName + " to add to wave");
		dialog.setContentText("Please enter a new number:");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(newValue -> {
			try{
				for (int i = 0; i < Integer.parseInt(newValue); i++) {
					myWave.add((Troop) TroopsData.myTroops.get(troopName));
				}
				addMoreTroops();
			} catch(Exception excep){
				Alert warning = new Alert(AlertType.INFORMATION);
				warning.setTitle("Warning");
				warning.setHeaderText("Invalid value");
				warning.setContentText("Only numbers allowed.");
				warning.show();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void addMoreTroops() {
		@SuppressWarnings("rawtypes")
		ChoiceDialog dialog = new ChoiceDialog();
		dialog.getItems().addAll("Yes", "No");
		dialog.setTitle("Add More Troops");
		dialog.setHeaderText("Add more troops to wave");
		dialog.setContentText("Would you like to add more troops to the wave?");
		Optional<String> result = dialog.showAndWait();
		String ret = "cancelled";
		ret = result.get();
		if (ret.equals("Yes")) selectTroop();
		if (ret.equals("No")) finishLevel();
		else {
			Alert warning = new Alert(AlertType.INFORMATION);
			warning.setTitle("Warning");
			warning.setHeaderText("No troop chosen");
			warning.setContentText("User must choose a troop.");
			warning.show();
		}
	}
	
	private void askSpawnRate() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Choose Troop Spawn Rate");
		dialog.setHeaderText("Choose the spawn rate");
		dialog.setContentText("Please enter a new number (decimals are allowed):");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(newValue -> {
			try{
				mySpawnRate = Double.parseDouble(newValue);
				askSpeed();
			} catch(Exception excep){
				Alert warning = new Alert(AlertType.INFORMATION);
				warning.setTitle("Warning");
				warning.setHeaderText("Invalid value");
				warning.setContentText("Only numbers allowed.");
				warning.show();
			}
		});
	}
	
	private void askSpeed() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Choose Troop Speed");
		dialog.setHeaderText("Choose the troop movement speed");
		dialog.setContentText("Please enter a new number (decimals are allowed):");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(newValue -> {
			try{
				mySpeed = Double.parseDouble(newValue);
				selectTroop();
				
			} catch(Exception excep){
				Alert warning = new Alert(AlertType.INFORMATION);
				warning.setTitle("Warning");
				warning.setHeaderText("Invalid value");
				warning.setContentText("Only numbers allowed.");
				warning.show();
			}
		});
	}
	
	private void finishLevel() {
		List<String> myPaths = new ArrayList<String>();
		myPaths.addAll(PathsData.myPaths.keySet());
		Collections.sort(myPaths);
		Level l = new Level(Integer.toString(myCurrentLevel), new ArrayList<Troop>(myWave), myPaths, mySpawnRate, mySpeed);
		myEntriesToShow.add("Level "+l.getName() + " [" + myWave.size() + " troops]");
		myData.add(l.getName(), l);
		myCurrentLevel++;
		refresh();
		Alert finishAlert = new Alert(Alert.AlertType.CONFIRMATION, "Level "+l.getName()+" has been created!");
		finishAlert.show();
	}
	
	private void refresh() {
		mySpawnRate = 0.0;
		mySpeed = 0.0;
		myWave.clear();
	}
	
	@Override
	public Node getView() {
		return myTabView;
	}

	@Override
	public void setData(ITabData data) {
		myData = (LevelsData) data;
		initializeButtons();
		initializeLevels();
	}

}
