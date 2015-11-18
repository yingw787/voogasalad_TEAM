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
	private Controller myController;
	private PlayerInfo myInfo;
	
	public HUDManager(Controller c, PlayerInfo info){
		this.myController = c;
		this.myInfo = info;
		
		this.updateUserInfo();
	}
	
	public void updateUserInfo(){
		myController.updateUserInfo(myInfo);
	}
	
	public void incrementLevel(){
		String s = Integer.toString(Integer.parseInt(myInfo.getLevel())+1);
		myInfo.setLevel(s);
		updateUserInfo();
	}
	
}
