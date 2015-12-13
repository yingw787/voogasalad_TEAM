package gamePlayer.button;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import controller.Controller;
import gameEngine.requests.SellTowerRequest;
import gamePlayer.Player;
import interfaces.IRequest;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import units.PlayerInfo;
import units.Tower;
import units.Unit;

public class ButtonFactory {
	private static final String DEFAULT_RESOURCE_BUTTON = "gamePlayer.gamePlayer";
	private ResourceBundle myResource;

	private Map<String, AButton> myButtons;
	private Controller myController; 
	private PlayerInfo myPlayerInfo;
	private Player myPlayer;
	//private TurtleGroupObserver myTurtleGroup;
	//private UserInput myUserInputObservable;

	public ButtonFactory (Controller controller, PlayerInfo playerinfo, Player player) {
		myResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_BUTTON);
		this.myController = controller;
		this.myPlayerInfo = playerinfo;
		this.myPlayer = player;
		//				this.myTurtleGroup = turtleGroup;
		//		this.myUserInputObservable = userInputObservable;
		myButtons = new HashMap<String, AButton>();
		createButtons();
	}

	public void createButtons(){
		String[] buttons = myResource.getString("buttons").split(",");
		HashMap<String, EventHandler<ActionEvent>> buttonEventHandle = new HashMap<String, EventHandler<ActionEvent>>();
		buttonEventHandle.put(buttons[0], event->startWaveButtonEvent());
		buttonEventHandle.put(buttons[1], event->buyUnitsButtonEvent());
		buttonEventHandle.put(buttons[2], event->sellUnitsButtonEvent());
		for(String buttonClassName: buttons){
			try {
				Constructor<?> c =
						Class.forName("gamePlayer.button."+buttonClassName)
						.getConstructor(EventHandler.class);
				AButton button = (AButton) c.newInstance(buttonEventHandle.get(buttonClassName));
				myButtons.put(buttonClassName, button);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public Map<String, AButton> getButtons(){
		return myButtons;
	}

	private void sellUnitsButtonEvent(){
		Unit selectedUnit = myPlayer.getSelected().getUnit();
		if(selectedUnit.getStringAttribute("Type").equals("Tower")){
			Tower tower = new Tower(selectedUnit);
			myButtons.get("SellButton").setDisable(true);
			SellTowerRequest sell = new SellTowerRequest(tower);
			List<IRequest> requestSender = new ArrayList<IRequest>();
			requestSender.add(sell);
			myController.update(requestSender);
		}
	}

	private void buyUnitsButtonEvent(){
		myButtons.get("BuyButton").setDisable(true);
	}

	private void startWaveButtonEvent() {
		System.out.println("Abhishek: "+ myPlayerInfo.getMyLevelSize() + " " + myPlayerInfo.getLevel());
		if(Integer.parseInt(myPlayerInfo.getLevel()) < myPlayerInfo.getMyLevelSize()){
			myButtons.get("StartWaveButton").setOnMouseClicked(e->myController.startWave(
					Integer.parseInt(myPlayerInfo.getLevel())));	
		}else{
			myButtons.get("StartWaveButton").setOnMouseClicked(e->startWaveAlert());
		}
	}

	/**
	 * shows alert message for Start wave.
	 */
	private void startWaveAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Alert Message");
		String label = null;
		label = "You have exceeded the total number of levels for this game";
		alert.setContentText(label);
		alert.showAndWait();
	}

}
