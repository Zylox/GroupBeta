package com.tiny.tank;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.guiComponents.Button;

public class Pause_Menu extends BasicGameState {

	private Button playButton;
	private Button menuButton;
	private Image pause_menu_background;
	private int id; 
	int posX;
	int posY;

	private Input input;
	
	public Pause_Menu(int id) {
		this.id = id;
	}

/** Make an image out of what was displayed during gameplay*/
	public void enter(GameContainer container, StateBasedGame game) {


		try {
			pause_menu_background = new Image(container.getWidth(),
					container.getHeight());

		} catch (SlickException e) {
			e.printStackTrace();
		}
		Graphics g = container.getGraphics();
		/** Don't continue prints the FPS counter*/
		container.setShowFPS(false);
		g.copyArea(pause_menu_background, 0, 0);		
	}
	

	@Override

	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		input = container.getInput();
	}
	
	public void loadImages(){
		playButton=new Button("res/play_button.png",300,100);
		menuButton=new Button("res/back_button.png",300,300);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		pause_menu_background.draw();/** Draw the image of the previous state*/
		/** draw the buttons on the pause menu*/
		playButton.drawButton(g);
		menuButton.drawButton(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		/** create new input object for getting mouse coordinates*/
		
	    if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            posX = input.getMouseX();
            posY = input.getMouseY();
            if (playButton.isMouseOverButton(posX, posY)) {
                    game.enterState(STATES.MAIN_GAMEPLAY.getId());
            }
            if (menuButton.isMouseOverButton(posX, posY)) {
                    game.enterState(STATES.MAIN_MENU.getId());
            }
	    }
	}
	@Override
	public void leave(GameContainer container,StateBasedGame game) {
		container.getInput().clearMousePressedRecord();
	}
	/** this is the state ID that specified in the STATES class*/
	@Override
	public int getID() {
		return id;
	}
	
}
