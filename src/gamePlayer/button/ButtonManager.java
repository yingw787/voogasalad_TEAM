// This entire file is part of my masterpiece.
// Abhishek Upadhyaya Ghimire

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
import units.Tower;
import units.Unit;

/**
 * A class for creating and holding Button objects.
 * @see			Controller
 * @see         Player
 * @see 		AButton
 */
public class ButtonManager {
	private static final String DEFAULT_RESOURCE_BUTTON = "gamePlayer.gamePlayer";
	private ResourceBundle myResource;
	private Map<String, AButton> myButtons;
	private Controller myController;
	private Player myPlayer;

	public ButtonManager (Controller controller, Player player) {
		myResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_BUTTON);
		this.myController = controller;
		this.myPlayer = player;
		myButtons = new HashMap<String, AButton>();
		createButtons();
	}

	public void createButtons(){
		String[] buttons = myResource.getString("buttons").split(",");
		Map<String, EventHandler<ActionEvent>> buttonEventHandle = new HashMap<String, EventHandler<ActionEvent>>();
		buttonEventHandle.put(buttons[0], event->buyUnitsButtonEvent());
		buttonEventHandle.put(buttons[1], event->sellUnitsButtonEvent());
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

	/**
	 * Sell units button event.
	 */
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

	/**
	 * Buy units button event.
	 */
	private void buyUnitsButtonEvent(){
		myButtons.get("BuyButton").setDisable(true);
	}

}
