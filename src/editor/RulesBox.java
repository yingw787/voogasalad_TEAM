// This entire file is part of my masterpiece.
// William Yang

package editor;

import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import rules.AActionDialog;
import rules.AConditionDialog;
import rules.ADialog;
import rules.ChangeAttributeDialog;
import rules.CheckAttributeDialog;
import rules.DisappearDialog;
import rules.OneTimeRule;
import rules.Rule;
import rules.ShootDialog;
import rules.StickDialog;
import rules.TimerDialog;
import units.Unit;
import actions.IAction;
import conditions.ICondition;
import editor.tabData.BulletsData;
import editor.tabData.DataController;

public class RulesBox extends ADialog implements IView, Observer {
	
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
	private final String[] actionsArray = {"ChangeAttribute", "Disappear", "Shoot", "Shoot Sticky Bullet"};
	private final String[] ruleTypeArray = {"Once", "Repeat Infinitely"};
	
	/**  Constructor for Rules object which allows creation/deletion of rules for Units
	 *   @param DataController that contains game data
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
	 * Initializes the Make and Delete Rule buttons
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
	
	/**
	 * Delete a rule from a unit
	 */
	private void deleteRule() {
		String selected = myCurrentRules.getSelectionModel().getSelectedItem();
		myCurrentUnit.removeRules(selected);
		refresh();
	}
	
	/**
	 * Makes a new Rule
	 */
	private void addRule() {
		String conditionDescription, actionDescription;
		Rule rule = null;
		ICondition condition = null;;
		IAction action = null;
		
		// Ask for the condition
		AConditionDialog conditionAsker = askForCondition(askUser(conditionsArray, "Condition"));
		condition = conditionAsker.ask(myCurrentUnit);
		conditionDescription = conditionAsker.getDescription();

		// Ask for the action
		AActionDialog actionAsker = askForAction(askUser(actionsArray, "Action"));
		action = actionAsker.ask(myCurrentUnit);
		actionDescription = actionAsker.getDescription();

		// Make sure the parts are there
		if(conditionAsker == null || condition == null || actionAsker == null || action == null){
			return;
		}
		
		// Ask if the rule is one time only
		if(askUser(ruleTypeArray, "How often should the rule run?").equals(ruleTypeArray[0])){
			rule = new OneTimeRule(condition, action);
		}
		else {
			rule = new Rule(condition, action);
		}
		
		String ruleKey = conditionDescription + actionDescription;
		myCurrentUnit.setRule(ruleKey, rule);
		refresh();
	}

	/**
	 * AConditionDialog factory
	 * @param cond
	 * @return appropriate AConditionDialog object
	 */
	private AConditionDialog askForCondition(String cond) {
		AConditionDialog conditionAsker = null;
		switch (cond) {
			case "CheckAttribute":		
				conditionAsker = new CheckAttributeDialog();
				break;
			case "Timer":
				conditionAsker = new TimerDialog();
				break;
			default:
				break;
		}
		return conditionAsker;
	}

	/**
	 * AActionDialog factory
	 * @param act
	 * @return appropriate AActionDialog
	 */
	private AActionDialog askForAction(String act) {
		AActionDialog actionAsker = null;
		switch (act){
			case "ChangeAttribute":
				actionAsker = new ChangeAttributeDialog();
				break;
			case "Disappear":
				actionAsker = new DisappearDialog();
				break;
			case "Shoot":
				actionAsker = new ShootDialog();
				((ShootDialog)actionAsker).setBullets((BulletsData) myDataController.getData("Bullets"));
				break;
			case "Shoot Sticky Bullet":
				actionAsker = new StickDialog();
				break;
			default:
				break;
		}
		return actionAsker;
	}
	
	/**
	 * Makes the Rules header sign
	 */
	private void addHeader() {
		myLabel = new HBox();
		Text label = new Text("Rules");
		label.setFont(Font.font("Verdana", 30));
		myLabel.getChildren().add(label);
		myBoxContent.getChildren().add(myLabel);
	}

	/**
	 * Returns the view for the editor
	 */
	@Override
	public ScrollPane getView() {
		return myRulesBox;
	}

	
	/**
	 * Display the unit the user selects
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String){
			clearRules();
		}
		else if(arg instanceof Unit){
			myCurrentUnit = (Unit) arg;
			refresh();
		}
	}
	
	/**
	 * Updates the display
	 */
	private void refresh(){
		clearRules();
		showRules();
	}
	
	/**
	 * Clear the list of rules
	 */
	private void clearRules() {
		if(myRuleEditor != null){
			myBoxContent.getChildren().remove(myRuleEditor);
			myRuleEditor = null;
		}
	}

	/**
	 * Show the list of rules
	 */
	private void showRules() {
		myRuleEditor = new VBox();
		myEntriesToShow = FXCollections.observableArrayList();
		myCurrentRules = new ListView<String>(myEntriesToShow);
		myCurrentRules.setMaxWidth(200);
		makeButtons();
		myRuleEditor.getChildren().addAll(myButtons, myCurrentRules);
		myBoxContent.getChildren().add(myRuleEditor);

		for(String key : myCurrentUnit.getRuleSet()){
			myEntriesToShow.add(key);
		}
	}
}
