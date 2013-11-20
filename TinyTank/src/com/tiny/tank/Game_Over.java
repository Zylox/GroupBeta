package com.tiny.tank;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.guiComponents.Button;

public class Game_Over extends BasicGameState {
	private String nameOfPlayer1="Blue";
	private String nameOfPlayer2="Red";
	final int number_of_tanks=2;
	private Button playButton;
	private Button quitButton;
	private Image background;
	private int id;
	private Input input;
	private String displayWinnerMessage;
	private int posX;
	private int posY;
	private String[] statTitles;
	int[] playerNumbers={1,2};//should be sent number of players and make a for loop to create array of numbers
	private ArrayList<String> statsList1;
	private ArrayList<String> statsList2;
	private Sound click;
	
	
	
	public Game_Over(int id) {
		this.setId(id);
	}
	
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		input=container.getInput();
		
//		Main_Gameplay.players.get(0).getStat();
	}
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		statsList1=Main_Gameplay.players.get(0).getStat().listOfStats();
		statsList2=Main_Gameplay.players.get(1).getStat().listOfStats();
		
		if( Integer.parseInt(statsList1.get(4)) > Integer.parseInt(statsList2.get(4))) {
			setDisplayWinnerMessage( getNameOfPlayer1() +" Wins!");
		} 
		else if(Integer.parseInt(statsList1.get(4)) > Integer.parseInt(statsList2.get(4))) {
			setDisplayWinnerMessage( getNameOfPlayer2() +" Wins!");
		}
		else {
			setDisplayWinnerMessage("It's a tie!");
		}
			
		statTitles=Main_Gameplay.players.get(0).getStat().titleOfStats();
		
		
	}
	public void loadImages() throws SlickException{
		
		background = new Image("res/GameOver.png");
		playButton = new Button("res/play_button.png", 500, 500);
		quitButton = new Button("res/exit_button.png", 100, 500);
		
		click = new Sound("res/SoundFX/button-20.ogg");
	}
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		int i;
		int j;
		int numberOfStats=statTitles.length;
		
		background.draw();
		playButton.drawButton(g);
		quitButton.drawButton(g);
		
		
		g.drawString(getDisplayWinnerMessage(),100,175);
		//TODO: move score screen to be player 1 on the left and player 2 on the right
		//have it fade out to this
//		g.drawString("Player ",200,200);
		g.drawString(nameOfPlayer1,100,250);
		g.drawString(nameOfPlayer2,700,250);
		for(i=1;i<numberOfStats;i++) {
				g.drawString(statTitles[i],350,250+20*i);
				g.drawString(statsList1.get(i),100,250+20*i);
				g.drawString(statsList2.get(i),700,250+20*i);
			
		}
		
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame game, int arg2)
			throws SlickException {
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            posX = input.getMouseX();
            posY = input.getMouseY();
            if (playButton.isMouseOverButton(posX, posY)) {
            		click.play();
                    game.enterState(STATES.SELECT_WEAPONS_MENU.getId());
            }
            if (quitButton.isMouseOverButton(posX, posY)) {
            		click.play();
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



	public String getWinner() {
		return displayWinnerMessage;
	}



	public void setWinner(String winner) {
		this.displayWinnerMessage = winner;
	}



	public String getDisplayWinnerMessage() {
		return displayWinnerMessage;
	}



	public void setDisplayWinnerMessage(String displayWinnerMessage) {
		this.displayWinnerMessage = displayWinnerMessage;
	}



	public String getNameOfPlayer1() {
		return nameOfPlayer1;
	}



	public void setNameOfPlayer1(String nameOfPlayer1) {
		this.nameOfPlayer1 = nameOfPlayer1;
	}



	public String getNameOfPlayer2() {
		return nameOfPlayer2;
	}



	public void setNameOfPlayer2(String nameOfPlayer2) {
		this.nameOfPlayer2 = nameOfPlayer2;
	}

	
	
}
