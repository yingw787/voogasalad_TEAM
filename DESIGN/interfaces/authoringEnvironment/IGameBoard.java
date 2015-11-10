package voogasalad_TEAM;

public interface IGameBoard {
	
	/* Returns the current SubScene representing the gameboard */
	public SubScene getView();
	
	/* Sets the background to bg
	 * Resizes bg image to fit to gameboard screen
	 * Ensures that bg is first child of SubScene so other images overlay
	 */
	public void setBackGround(Image bg);
	
	/* Adds image to appear on gameboard at a specific location */
	public void addElement(ImageView elem);
	
	/* Removes image from gameboard */
	public void removeElement(ImageView elem);
	
}
