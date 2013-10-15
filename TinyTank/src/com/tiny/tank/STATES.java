package com.tiny.tank;

import org.newdawn.slick.state.BasicGameState;

public enum STATES {

	MAIN(new Main(1));
	
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
