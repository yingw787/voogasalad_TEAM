package units;

public class PlayerInfo {
	private int myMoney;
	private int myLives;
	private String myCurrentLevel;
	
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
}
