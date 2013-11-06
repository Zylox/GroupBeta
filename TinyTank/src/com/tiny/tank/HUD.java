package com.tiny.tank;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class HUD {
	
	private Image hud_background;
	private Input input;

	public void enter(GameContainer container, StateBasedGame game){
		
		try {
			hud_background = new Image(container.getWidth(), 200);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		input = container.getInput();
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// current graphical representation
		hud_background.draw();
		
	}
	
	public abstract boolean update();



}
