package voogasalad_TEAM;

public class IEditorTabs {
	
	/* Returns current TabPane representing tab bar */
	public TabPane getView();
	
	/* Adds tab to current TabPane with label tabName and content tabContent */
	public void addPane(String tabName, Pane tabContent);
	
}
