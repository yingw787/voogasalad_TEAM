// This entire file is part of my masterpiece.
// Abhishek Upadhyaya Ghimire

package gamePlayer.button;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * An abstract class consisting of properties for all the buttons used in HUD.
 */
public abstract class AButton extends Button{
	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private ResourceBundle myResource;

    public AButton (String text, EventHandler<ActionEvent> event) {
        super(text);
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
        this.setPrefWidth(Integer.parseInt(myResource.getString("prefButtonWidth")));
        this.setStyle(myResource.getString("cssHUDButtonStyle"));
        this.setOnAction(event);

    }
}
