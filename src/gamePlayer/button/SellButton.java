// This entire file is part of my masterpiece.
// Abhishek Upadhyaya Ghimire

package gamePlayer.button;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SellButton extends AButton{

	public SellButton (EventHandler<ActionEvent> event) {
		super("Sell", event);
		setDisable(true);
	}
}