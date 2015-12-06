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
import rules.AActionDialog;
import rules.AConditionDialog;
import rules.ChangeAttributeDialog;
import rules.CheckAttributeDialog;
import rules.DisappearDialog;
import rules.OneTimeRule;
import rules.Rule;
import rules.ShootDialog;
import rules.TimerDialog;
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
	private final String[] ruleTypeArray = {"Once", "Repeat Infinitely"};
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
		// Ask for the condition
		ICondition condition = null;;
		IAction action = null;
		cond = askUser(conditionsArray, "Condition");
		System.out.println("Condition: " + cond);
		AConditionDialog conditionAsker = askForCondition(cond);
		if(conditionAsker == null){
			return;
		}
		condition = conditionAsker.ask(myCurrentUnit);
		conditionDescription = conditionAsker.getDescription();
		if(condition == null){
			return;
		}
		
		// Ask for the action
		act = askUser(actionsArray, "Action");
		AActionDialog actionAsker = askForAction(act);
		if(actionAsker == null){
			return;
		}
		action = actionAsker.ask(myCurrentUnit);
		actionDescription = actionAsker.getDescription();
		if(action == null){
			return;
		}
		
		String ruleKey = "";
		
		// Ask if the rule is one time only
		String ruleType = askUser(ruleTypeArray, "How often should the rule run?");
		if(ruleType.equals(ruleTypeArray[0])){
			rule = new OneTimeRule(condition, action);
			ruleKey = "One time only, ";
		}
		else {
			rule = new Rule(condition, action);
		}
		
		ruleKey += conditionDescription + actionDescription;
		
		System.out.println("Action: " + act);
		System.out.println(ruleKey);
		
		myEntriesToShow.add(ruleKey);
		myCurrentUnit.setRule(ruleKey, rule);
	}

	/**
	 * @param cond
	 * @param conditionAsker
	 * @return
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
	 * @param act
	 * @param actionAsker
	 * @return
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
			default:
				break;
		}
		return actionAsker;
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
