package gameEngine;

import controller.Controller;
import units.PlayerInfo;

public class HUDManager {

	/*
	 * HUDManager.java is the backend engine module for HUD.java, the front-end module for the gamePlayer. 
	 * 
	 * Responsibilities: 
	 * 
	 * TODO: (update as needed) 
	 */
	private Engine myEngine;
	private PlayerInfo myInfo;
	
	public HUDManager(Engine e, PlayerInfo info){
		this.myEngine = e;
		this.myInfo = info;
		
		this.updateUserInfo();
	}
	
	public void updateUserInfo(){
		myEngine.updateUserInfo(myInfo);
	}
	
	public void incrementLevel(){
		String s = Integer.toString(Integer.parseInt(myInfo.getLevel())+1);
		myInfo.setLevel(s);
		updateUserInfo();
	}
	
}
