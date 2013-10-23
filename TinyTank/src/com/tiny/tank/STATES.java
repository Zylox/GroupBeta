package com.tiny.tank;

import org.newdawn.slick.state.BasicGameState;

public enum STATES {
	MAIN_MENU(new Main_Menu(1)),
	SELECT_WEAPONS_MENU(new Select_Weapons_Menu(2)), 
	MAIN_GAMEPLAY(new Main_Gameplay(3)),
	PAUSE_MENU(new Menu(4));
	
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
