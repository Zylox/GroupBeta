package com.tiny.tank;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause_Menu extends BasicGameState {

	private Button playButton;
	private Button menuButton;
	private Image pause_menu_background;
	private int id;
	private int posX;
	private int posY;

	private Input input;

	public Pause_Menu(int id) {
		this.id = id;
	}

	public void enter(GameContainer container, StateBasedGame game) {

		try {
			pause_menu_background = new Image(container.getWidth(),
					container.getHeight());

		} catch (SlickException e) {
			e.printStackTrace();
		}
		Graphics g = container.getGraphics();
		container.setShowFPS(false);
		g.copyArea(pause_menu_background, 0, 0);

	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		playButton = new Button("res/play_button.png", 100, 100);
		menuButton = new Button("res/back_button.png", 100, 300);
		input = container.getInput();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		pause_menu_background.draw();
		playButton.drawButton(g);
		menuButton.drawButton(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

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
	public int getID() {
		return id;
	}
}
