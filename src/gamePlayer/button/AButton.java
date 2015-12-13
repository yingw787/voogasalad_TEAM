package gamePlayer.button;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public abstract class AButton extends Button{
	private static final String DEFAULT_GAMEPLAYER_RESOURCE = "gamePlayer.gamePlayer";
	private ResourceBundle myResource;

    public AButton (String text, EventHandler<ActionEvent> event) {
        super(text);
		this.myResource = ResourceBundle.getBundle(DEFAULT_GAMEPLAYER_RESOURCE);
        this.setPrefWidth(150);
        this.setStyle(myResource.getString("cssHUDButtonStyle"));
        this.setOnAction(event);

    }
}
