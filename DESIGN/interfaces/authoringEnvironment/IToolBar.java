package voogasalad_TEAM;

public interface IToolBar {
	
	/* Adds new option to the upper toolbar of the editor;
	/* parameter elem represents Node that contains String name and menu options */
	public void addElement(Node elem);
	
	/* returns the current ToolBar */
	public ToolBar getView();
	
}
