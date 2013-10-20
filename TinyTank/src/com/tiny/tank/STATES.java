package com.tiny.tank;

import org.newdawn.slick.state.BasicGameState;

public enum STATES {

	SELECT_WEAPONS_MENU(new Select_Weapons_Menu(1)), 
	MAIN_GAMEPLAY(new Main_Gameplay(2));
	
	private int id;
	private BasicGameState state;
	
	STATES(BasicGameState state){
		this.id = state.getID();
		this.state = state;
	}
	
	BasicGameState getState(){
		return state;
	}
	
	int getId(){
		return id;
	}
}
