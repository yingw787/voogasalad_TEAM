package units;

public class PlayerInfo {
	private int myMoney;
	private int myLives;
	private int myCurrentLevel;
	
	public PlayerInfo(int money, int lives, int lvl){
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
	
	public int getLevel(){
		return myCurrentLevel;
	}
}
