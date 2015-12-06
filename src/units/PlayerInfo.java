package units;

public class PlayerInfo {
	private int myMoney;
	private int myLives;
	private String myCurrentLevel;
	private int myLevelSize;
	
	/**  Constructor for PlayerInfo object
	 *   @params Properties of PlayerInfo object
	 **/
	public PlayerInfo() {
		this.myMoney = 100;
		this.myLives = 1;
		this.myCurrentLevel = "0";
		this.myLevelSize = 1;
	}
	
	public PlayerInfo(int money, int lives, String lvl, int levelSize){
		this.myMoney = money;
		this.myLives = lives;
		this.myCurrentLevel = lvl;
		this.myLevelSize = levelSize;
	}
	
	public int getMoney(){
		return myMoney;
	}
	
	public int getLives(){
		return myLives;
	}
	
	public String getLevel(){
		return myCurrentLevel;
	}
	
	public void setLevel(String s){
		myCurrentLevel = s;
	}
	
	public void setMoney(int Money) {
		this.myMoney = Money;
	}

	public void setLives(int Lives) {
		this.myLives = Lives;
	}

	public int getMyLevelSize() {
		return myLevelSize;
	}

	public void setMyLevelSize(int myLevelSize) {
		this.myLevelSize = myLevelSize;
	}
	
}
