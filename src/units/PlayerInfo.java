package units;

public class PlayerInfo {
	private int myMoney;
	private int myLives;
	private String myCurrentLevel;
	
	
	/**  Constructor for PlayerInfo object
	 *   @params Properties of PlayerInfo object
	 **/
	public PlayerInfo() {
		this.myMoney = 100;
		this.myLives = 1;
		this.myCurrentLevel = "0";
	}
	
	public PlayerInfo(int money, int lives, String lvl){
		this.myMoney = money;
		this.myLives = lives;
		this.myCurrentLevel = lvl;
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
}
