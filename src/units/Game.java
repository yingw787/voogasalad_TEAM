package units;

import java.util.ResourceBundle;

public class Game {
//	private GameType myGameType;
	private static ResourceBundle myDefaults = ResourceBundle.getBundle("resources/Default");
	private String myHelpPage;
	private String myTitle;
	private String myDescription;
	private String myImage;
	private String myBackground;
	private boolean myBuyTroopOption;
	private boolean myPathVisibility;
	
	/**  Constructor for Game object
	 **/
	public Game(){
		setHelpPage("");
		setTitle("");
		setBackground(myDefaults.getString("Background"));
	}
	
	public void setHelpPage(String url){
		this.myHelpPage = url;
	}
	
	public void setTitle(String title){
		this.myTitle = title;
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	public String getHelpPage(){
		return myHelpPage;
	}

	public boolean getBuyTroopOption() {
		return myBuyTroopOption;
	}

	public void setBuyTroopOption(boolean b) {
		this.myBuyTroopOption = b;
	}

	public boolean getPathVisibility() {
		return myPathVisibility;
	}

	public void setPathVisibility(boolean b) {
		this.myPathVisibility = b;
	}
	
	public String getDescription() {
		return myDescription;
	}
	
	public void setDescription(String description) {
		this.myDescription = description;
	}
	
	public String getImage() {
		return myImage;
	}
	
	public void setImage(String image) {
		this.myImage = image;
	}
	
	public String getBackground() {
		return myBackground;
	}
	
	public void setBackground(String image) {
		this.myBackground = image;
	}

}
