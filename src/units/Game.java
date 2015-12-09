package units;

public class Game {
//	private GameType myGameType;
	private String myHelpPage;
	private String myTitle;
	private boolean myBuyTroopOption;
	private boolean myPathVisibility;
	
	/**  Constructor for Game object
	 **/
	public Game(){
		setHelpPage("");
		setTitle("");
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
}
