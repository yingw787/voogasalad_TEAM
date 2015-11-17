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
	Controller myController;
	
	PlayerInfo myInfo;
	public HUDManager(Controller controller , PlayerInfo info){
		this.myController = controller;
		this.myInfo = info;
		
		this.updateUserInfo();
	}
	
	
	private void updateUserInfo(){
		myController.updateUserInfo(myInfo);
	}
	
}
