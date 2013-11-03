package com.tiny.tank;

import org.newdawn.slick.state.BasicGameState;

public enum STATES {
	
	/**
	 * Make sure to call your loadImages() state in LoadingState if you need to load assets.
	 */
	
	LOADING(new LoadingState(0)),
	MAIN_MENU(new Main_Menu(1)),
	SELECT_WEAPONS_MENU(new Select_Weapons_Menu(2)), 
	MAIN_GAMEPLAY(new Main_Gameplay(3)),
	PAUSE_MENU(new Pause_Menu(4));
	
	private int id;
	private BasicGameState state;
	
	STATES(BasicGameState state){
		this.id = state.getID();
		this.state = state;
	}
	
	public BasicGameState getState(){
		return state;
	}
	
	public int getId(){
		return id;
	}
}
