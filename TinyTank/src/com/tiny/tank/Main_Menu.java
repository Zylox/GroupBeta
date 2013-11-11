package com.tiny.tank;

//import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.guiComponents.Button;


public class Main_Menu extends BasicGameState {
	private Button playButton;
	private Button quitButton;
	private Image background;
	private int id;

	private Input input;

	public Main_Menu(int id) {
		this.id = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		input = container.getInput();
	}
	
	public void loadImages() throws SlickException{
		background = new Image("res/main.png");

		playButton = new Button("res/play_button.png", 290, 250);
		quitButton = new Button("res/exit_button.png", 290, 400);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw();
		playButton.drawButton(g);
		quitButton.drawButton(g);
	
	}

	@Override

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		container.setShowFPS(true);
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int posX = input.getMouseX();
			int posY = input.getMouseY();
			
			if (playButton.isMouseOverButton(posX, posY)) {
				game.enterState((STATES.SELECT_WEAPONS_MENU).getId());
			}
			else if (quitButton.isMouseOverButton(posX, posY)) {
				System.exit(0);
			}
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		TinyTank.setPreviousState(id);
	}

	@Override
	public int getID() {
		return id;
	}
}
