package editor.tabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import units.Troop;
import editor.IView;
import editor.tabData.ITabData;
import editor.tabData.LevelsData;
import editor.tabData.TroopsData;

public class LevelsTab extends ATab implements IView, ITab{
	private LevelsData myData;
	private List<Troop> myWave;
	private Button myAddButton;
	private Button myDeleteButton;
	private Button myFinishButton;

	public LevelsTab(){
		initTab();
		createButtons();
	}
	
	private void createButtons() {
		myAddButton = makeButton("Add New Level", e-> addLevel());
		myDeleteButton = makeButton("Delete Level", e-> deleteLevel());
		myFinishButton = makeButton("Finalize Level", e-> finishLevel());
		myButtons.getChildren().addAll(myAddButton, myDeleteButton, myFinishButton);
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
	public void setData(ITabData data) {
		myData = (LevelsData) data;
	}

}
