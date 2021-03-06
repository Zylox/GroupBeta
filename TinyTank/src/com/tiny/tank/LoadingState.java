package com.tiny.tank;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LoadingState extends BasicGameState {

	private int id;

	private Image splash;
	private boolean rendered;

	public LoadingState(int id) {
		this.id = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		splash = new Image("res/splash.png");
			
		rendered = false;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		//Turns of the fps so that it doesnt show on splash screen
		container.setShowFPS(false);
		splash.draw();
		//lets the update know that the splash has been rendered;
		rendered = true;
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		//if the splash screen is displayed, load other images
		if (rendered) {
			((Main_Menu) STATES.MAIN_MENU.getState()).loadImages();
			((Pause_Menu) STATES.PAUSE_MENU.getState()).loadImages();
			((Select_Weapons_Menu) STATES.SELECT_WEAPONS_MENU.getState()).loadImages();
			((Game_Over) STATES.GAME_OVER.getState()).loadImages();

			game.enterState(STATES.MAIN_MENU.getId());
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
