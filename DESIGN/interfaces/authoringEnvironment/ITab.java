package voogasalad_TEAM;

public interface ITab {
	
	/* Returns tab content represented by a Pane */
	public Pane getView();
	
	/* Adds Node representing created unit option to within the tab content pane */
	public void addOption(Node elem);
	
	/* Removes Node representing existing unit option from within the tab content pane */
	public boolean removeOption(Node elem);
	
}
