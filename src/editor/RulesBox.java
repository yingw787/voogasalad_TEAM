package editor;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import actions.ChangeAttributeAction;
import actions.DisappearAction;
import actions.IAction;
import actions.ShootAction;
import conditions.CheckAttributeCondition;
import conditions.ICondition;
import conditions.TimerCondition;
import rules.Rule;
import units.Bullet;
import units.Tower;
import units.Unit;
import editor.tabData.BulletsData;
import editor.tabData.DataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RulesBox implements IView, Observer {
	
	private ScrollPane myRulesBox;
	private DataController myDataController;
	private VBox myBoxContent;
	private HBox myLabel;
	private VBox myRuleEditor;
	private HBox myButtons;
	private Button myAddButton;
	private Button myDeleteButton;
	private ListView<String> myCurrentRules;
	private ObservableList<String> myEntriesToShow;
	private Unit myCurrentUnit;
	private final String[] conditionsArray = {"CheckAttribute", "Timer"};
	private final String[] actionsArray = {"ChangeAttribute", "Disappear", "Shoot"};
	private List<String> dialogData;
	
	/**  Constructor for Rules object which allows creation/deletion of rules for Units
	 *   @params DataController DataController to write rules to 
	 **/
	public RulesBox(DataController data) {
		myRulesBox = new ScrollPane();
		myRulesBox.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myRulesBox.setFitToHeight(true);
		myBoxContent = new VBox();
		myRulesBox.setContent(myBoxContent);
		myDataController = data;
		addHeader();
		
	}

	/**
	 * 
	 */
	private void makeButtons() {
		myButtons = new HBox();
		myAddButton = new Button("Make Rule");
		myAddButton.setOnAction(e -> addRule());
		myDeleteButton = new Button("Delete Rule");
		myDeleteButton.setOnAction(e -> deleteRule());
		myButtons.getChildren().addAll(myAddButton, myDeleteButton);
		myBoxContent.getChildren().add(myButtons);
	}
	
	private void deleteRule() {
		String selected = myCurrentRules.getSelectionModel().getSelectedItem();
		myEntriesToShow.remove(selected);
		myCurrentUnit.removeRules(selected);
		for(String rule : myCurrentUnit.getRuleSet()){
			System.out.println(rule);
		}
	}
	
	private ChoiceDialog dialog;
	private void addRule() {
		String cond = "";
		String act = "";
		String conditionDescription = "";
		String actionDescription = "";
		Rule rule = null;;
		ICondition condition = null;;
		IAction action = null;
		cond = askUser(conditionsArray, "Condition");
		System.out.println("Condition: " + cond);
		switch (cond) {
			case "CheckAttribute":
				// Ask for attribute to change
				String[] availableAttributes = myCurrentUnit.getAttributeArray();
				String attribute = askUser(availableAttributes, "Attribute");
				System.out.println(attribute + " chosen");
				
				double lower = 0.0;
				double higher = 0.0;
				String title = "Bounding Values";
				String header = "Please Enter a Lower Bound";
				String content = "Please enter a number:";
				// Ask for lower bound
				Optional<String> result = askUserForText(title, header, content);
				if (result.isPresent()){
					lower = Double.parseDouble(result.get());
				} else return;
				// Ask for higher bound
				header = "Please Enter a Higher Bound";
				result = askUserForText(title, header, content);
				if (result.isPresent()){
					higher = Double.parseDouble(result.get());
				} else return;				
				condition = new CheckAttributeCondition(attribute, lower, higher);
				conditionDescription = "When " + attribute + " is in (" + lower + ", " + higher + "), ";
				break;
			case "Timer":
				// Ask for delay
				int delay = 0;
				Optional<String> result2 = askUserForText("Timer Delay", "Enter a delay between actions", "Positive numbers only");
				if (result2.isPresent()){
					delay = Integer.parseInt(result2.get());
				} else return;
				condition = new TimerCondition(delay);
				conditionDescription = "Every delay of " + delay + " frames, ";
				break;
			default:
				return;
		}
		
		act = askUser(actionsArray, "Action");
		switch (act){
			case "ChangeAttribute":
				double change = 0.0;
				String[] availableAttributes = myCurrentUnit.getAttributeArray();
				String attribute = askUser(availableAttributes, "Please select an Attribute to change");
				Optional<String> result = askUserForText("Change value", "Please enter a number to be added to the current value", "Positive or negative numbers only");
				if (result.isPresent()){
					change = Double.parseDouble(result.get());
				} else return;
				action = new ChangeAttributeAction(attribute, change);
				actionDescription = "change " + attribute + " by " + change;
				break;
			case "Disappear":
				action = new DisappearAction();
				actionDescription = "disappear from the board";
				break;
			case "Shoot":
				double range = 0.0;

				String[] availableBullets = ((BulletsData) myDataController.getData("Bullets")).getBulletNamesArray();
				
				if(availableBullets.length == 0){
					Alert warning = new Alert(AlertType.INFORMATION);
					warning.setTitle("Warning");
					warning.setHeaderText("You're trying to shoot a bullet...");
					warning.setContentText("But you haven't made any bullets yet!");
					warning.show();
					return;
				}
				String bulletName = askUser(availableBullets, "Please select a bullet to shoot");
				Bullet bullet = ((BulletsData) myDataController.getData("Bullets")).get(bulletName);
				Optional<String> result2 = askUserForText("Tower Range", "Please enter a range (pixels) for the tower to shoot this bullet", "Positive numbers only");
				if (result2.isPresent()){
					range = Double.parseDouble(result2.get());
				} else return;
				action = new ShootAction(bullet, range);
				actionDescription = "shoot a " + bulletName + " with range " + range;
				
				break;
			default:
				return;
		}
		System.out.println("Action: " + act);
		String ruleKey = conditionDescription + actionDescription;
		System.out.println(ruleKey);
		rule = new Rule(condition, action);
		
		myEntriesToShow.add(ruleKey);
		myCurrentUnit.setRule(ruleKey, rule);
		
	}

	/**
	 * @param attribute
	 * @return
	 */
	private Optional<String> askUserForText(String title, String header, String content) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		Optional<String> result = dialog.showAndWait();
		return result;
	}

	private String askUser(String[] choices, String type){
		dialogData = Arrays.asList(choices);

		dialog = new ChoiceDialog(dialogData.get(0), dialogData);
		dialog.setTitle("New Rule");
		dialog.setHeaderText(type);

		Optional<String> result = dialog.showAndWait();
		String ret = "cancelled.";
				
		if (result.isPresent()) {
		    ret = result.get();
		}
		return ret;
	}
	
	private void addHeader() {
		myLabel = new HBox();
		Text label = new Text("Rules");
		label.setFont(Font.font("Verdana", 30));
		myLabel.getChildren().add(label);
		myBoxContent.getChildren().add(myLabel);
	}

	@Override
	public ScrollPane getView() {
		return myRulesBox;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
//		System.out.println("Rules box: tab changed to " + (String) arg);
		if(arg instanceof String){
			clearRules();
		}
		else if(arg instanceof Unit){
			System.out.println("Rules box: user selected Unit: " + ((Unit)arg).getStringAttribute("Name"));
			clearRules();
			myCurrentUnit = (Unit) arg;
			showRules((Unit) arg);
		}
	}

	private void clearRules() {
		if(myRuleEditor != null){
			myBoxContent.getChildren().remove(myRuleEditor);
			myRuleEditor = null;
		}
	}

	private void showRules(Unit arg) {
		myRuleEditor = new VBox();
		myEntriesToShow = FXCollections.observableArrayList();
		myCurrentRules = new ListView<String>(myEntriesToShow);
		myCurrentRules.setMaxWidth(200);
		makeButtons();
		myRuleEditor.getChildren().addAll(myButtons, myCurrentRules);
		myBoxContent.getChildren().add(myRuleEditor);

		for(String key : arg.getRuleSet()){
			myEntriesToShow.add(key);
		}
		
	}
}
