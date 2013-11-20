package com.tiny.tank;

import java.lang.reflect.Array;
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
	public static final String NAME_OF_PLAYER_ONE="Blue";
	public static final String NAME_OF_PLAYER_TWO="Red";
	public static final String WINS=" WINS!";
	public static final String TIE="It's a Tie!";
	public static final int CASE_1=1;
	public static final int CASE_2=2;
	public static final int CASE_3=3;
	
	final int number_of_tanks=2;
	private Button playButton;
	private Button quitButton;
	private Image background;
	private int id;
	private Input input;
	private String displayWinnerMessage;
	private int posX;
	private int posY;
	private ArrayList<String> statTitles;
	int[] playerNumbers={1,2};
	private ArrayList<String> statsList1;
	private ArrayList<String> statsList2;
	private int winner;
	private Sound click;
	

	
	public Game_Over(int id) {
		this.setId(id);
	}
	
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		input=container.getInput();
		
	}
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		statsList1=Main_Gameplay.players.get(0).getStat().listOfStats();
		statsList2=Main_Gameplay.players.get(1).getStat().listOfStats();
		
		
		/**Compares damage done
		 * Whichever player has less damage, wins
		 * Ties are possible */
		statTitles=Main_Gameplay.players.get(0).getStat().titleOfStats();
		
		int index=statTitles.indexOf(Stat.DAMAGE_TAKEN);
		if( Integer.parseInt(statsList1.get(index)) > Integer.parseInt(statsList2.get(index))) {
			setWinner(CASE_2);
		} 
		else if(Integer.parseInt(statsList1.get(index)) < Integer.parseInt(statsList2.get(index))) {
			setWinner(CASE_1);
		}
		else {
			setWinner(CASE_3);
		}
			
	}
	public void loadImages() throws SlickException{
		
		background = new Image("res/GameOver.png");
		playButton = new Button("res/play_button.png", 500, 500);
		quitButton = new Button("res/exit_button.png", 100, 500);
		
		click = new Sound("res/SoundFX/button-20.ogg");
	}
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		int numberOfStats=statTitles.size();
		
		background.draw();
		playButton.drawButton(g);
		quitButton.drawButton(g);
		
		/**
		 * Displays the winner or if there is a tie 
		 * Displays above the player that won
		 */
		switch(getWinner()){
			case CASE_1: g.drawString(NAME_OF_PLAYER_ONE + WINS ,100,175);
			break;
			case CASE_2: g.drawString(NAME_OF_PLAYER_TWO + WINS ,700,175);
			break;
			default: g.drawString(TIE ,350,175);
		}
		
			
		g.drawString(NAME_OF_PLAYER_ONE,100,250);
		g.drawString(NAME_OF_PLAYER_TWO,700,250);
		for(int i=1;i<numberOfStats;i++) {
				g.drawString(statTitles.get(i),350,250+20*i);
				g.drawString(statsList1.get(i),100,250+20*i);
				g.drawString(statsList2.get(i),700,250+20*i);
			
		}
		
		
	}


	/**
	 * Buttons for starting a new game or exiting the game
	 */

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

	public String getDisplayWinnerMessage() {
		return displayWinnerMessage;
	}



	public void setDisplayWinnerMessage(String displayWinnerMessage) {
		this.displayWinnerMessage = displayWinnerMessage;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getWinner() {
		return winner;
	}



	public void setWinner(int winner) {
		this.winner = winner;
	}
	
}
