package com.tiny.tank;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.guiComponents.Button;

public class Game_Over extends BasicGameState {
	private Button playButton;
	private Button quitButton;
	private Image background;
	private int id;
	private Input input;
	private String winner="1";
	int posX;
	int posY;

	
	String[] stats={"","Number of hits","Number of moves","Number of Shots","Hit percentage"};
	int[] players={1,2};//should be sent number of players and make a for loop to create array of numbers
	int number_of_tanks=2;
	Tank[] tanks;
	Stat game_stats;
	
	
	
	
	public Game_Over(int id) {
		this.setId(id);
	}
	
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		input=container.getInput();
	}

	public void loadImages() throws SlickException{
		
		background = new Image("res/bg.jpg");
		
		playButton = new Button("res/play_button.png", 500, 500);
		quitButton = new Button("res/exit_button.png", 100, 500);
	}
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		int i;
		int j;
		int numberOfStats=stats.length;

		playButton.drawButton(g);
		quitButton.drawButton(g);
		g.drawString("Game Over Page", 100, 50);
		
		g.drawString("Player "+winner+" Wins!",100,100);
		//TODO: move score screen to be player 1 on the left and player 2 on the right
		//have it fade out to this
//		g.drawString("Player ",200,200);
		for(i=1;i<numberOfStats;i++) {
			g.drawString(stats[i],350,200+20*i);
			for(j=0;j<number_of_tanks;j++) {
				g.drawString(Integer.toString(players[j]),100+600*j,200);
				g.drawString("100",100+600*j,200+20*i);
			}
		}
		
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame game, int arg2)
			throws SlickException {
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            posX = input.getMouseX();
            posY = input.getMouseY();
            if (playButton.isMouseOverButton(posX, posY)) {
                    game.enterState(STATES.SELECT_WEAPONS_MENU.getId());
            }
            if (quitButton.isMouseOverButton(posX, posY)) {
                    System.exit(0);
            }
	    }
	}

	@Override
	public int getID() {
		return id;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	
	
}