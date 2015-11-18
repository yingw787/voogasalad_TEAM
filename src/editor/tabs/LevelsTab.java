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
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import units.Troop;
import editor.IView;
import editor.MainGUI;
import editor.tabData.ITabData;
import editor.tabData.LevelsData;
import editor.tabData.TroopsData;

public class LevelsTab extends Observable implements IView, ITab{
	private ScrollPane myTabView;
	private VBox myTabContent;
	private LevelsData myData;
	private List<Troop> myWave;
	private Button myAddButton;
	private Button myDeleteButton;
	private Button myFinishButton;
	private ListView<String> myPathEntriesList;
	private ObservableList<String> myEntriesToShow;

	public LevelsTab(){
		myTabView = new ScrollPane();
		myTabContent = new VBox();
		myTabView.setContent(myTabContent);
	}
	
	private void initializeButtons() {
		HBox buttons = new HBox();
		myAddButton = new Button("Add New Level");
		myAddButton.setOnAction(e-> addLevel());
		myDeleteButton = new Button("Delete Level");
		myDeleteButton.setOnAction(e-> deleteLevel());
		myFinishButton = new Button("Finalize Level");
		myFinishButton.setOnAction(e-> finishLevel());
		buttons.getChildren().addAll(myAddButton, myDeleteButton, myFinishButton);
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
		selectTroop();
	}
	
	private void deleteLevel() {
		
	}
	
	private void selectTroop() {
		ChoiceDialog dialog = new ChoiceDialog();
		
		// put existing troops into choice box, sorted alphabetically
		List<String> troopsList = new ArrayList<String>();
		troopsList.addAll(TroopsData.myTroops.keySet());
		Collections.sort(troopsList);
		for (String troop : troopsList) {
			dialog.getItems().add(troop);
		}
		dialog.setTitle("Choose Enemy Troop");
		dialog.setContentText("Choose a created troop to add to the wave:");
		Optional<String> result = dialog.showAndWait();
		String ret = "cancelled";
		if (result.isPresent()) {
			ret = result.get();
			selectTroopQuantity(ret);
		}
	}
	
	private void selectTroopQuantity(String troopName) {
//		TextInputDialog dialog = new TextInputDialog();
//		dialog.setTitle("Choose Troop Quantity");
//		dialog.setHeaderText("Choose the number of " + troopName + " to add to wave");
//		dialog.setContentText("Please enter a new number:");
//		Optional<String> result = dialog.showAndWait();
//		result.ifPresent(newValue -> {
//			try{
//				myWave = new ArrayList<Troop>();
//				for (int i = 0; i < Integer.parseInt(newValue); i++) {
//					myWave.add((Troop) TroopsData.myTroops.get(troopName).clone());
//				}
//			} catch(Exception excep){
//				Alert warning = new Alert(AlertType.INFORMATION);
//				warning.setTitle("Warning");
//				warning.setHeaderText("Invalid value");
//				warning.setContentText("Only numbers allowed.");
//				warning.show();
			//	System.out.println("fail");
//			}
//		});
//		System.out.println(myWave);
	}
	
	private void finishLevel() {
		
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
