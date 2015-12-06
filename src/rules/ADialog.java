package rules;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

public abstract class ADialog {
	protected List<String> dialogData;
	protected ChoiceDialog<String> dialog;
	protected String myDescription;
	
	public String getDescription(){
		return myDescription;
	}
	
	/**
	 * @param attribute
	 * @return
	 */
	protected Optional<String> askUserForText(String title, String header, String content) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		Optional<String> result = dialog.showAndWait();
		return result;
	}

	protected String askUser(String[] choices, String type){
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
	
	
}
