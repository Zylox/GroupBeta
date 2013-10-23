package com.tiny.tank;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class TinyTank extends StateBasedGame {

	/**
	 * Gamewide previous state indicator.
	 * Sloppy but neccesary for now.
	 */
	private static int previousState;
	
	/**
	 * The constructor for our game.
	 * Name is the thing that shows up in the top bar.
	 * @param name
	 */
	public TinyTank(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		
		try{
		AppGameContainer app = new AppGameContainer(new TinyTank("Tiny Tank"));
		

		app.setDisplayMode(800, 600, true);
		app.setAlwaysRender(true);
		//app.setTargetFrameRate(100);
		app.start();

		}catch(SlickException ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		for(STATES s : STATES.values()){
			this.addState(s.getState());
		}
	}

	public static int getPreviousState() {
		return previousState;
	}

	public static void setPreviousState(int previousState) {
		TinyTank.previousState = previousState;
	}
	
	

}
