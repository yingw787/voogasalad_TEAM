package units;

public class Game {
//	private GameType myGameType;
	private String myHelpPage;
	private String myTitle;
	
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
}
