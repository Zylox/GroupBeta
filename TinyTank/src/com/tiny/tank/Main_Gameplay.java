package com.tiny.tank;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


import com.tiny.terrain.TerrainMap;

public class Main_Gameplay extends BasicGameState{
	
	private int id;
	public static TerrainMap map;
	private Input input;
	private Camera cam;
	
	//update counter
	private final int timeStep = 10;
	private int timeCounter;
	
	
	private final int numOfPlayers = 2;
	//index of player whose turn it is.
	private int playersTurnIndex;
	public static ArrayList<Tank> players;

	
	public Main_Gameplay(int id){
		this.id = id;
	}

	
	/**
	 * Called only once. Initializes things in state.
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		input = container.getInput();
		timeCounter = 0;
		
	}
	
	/**
	 * Called when entering the state
	 * @throws SlickException 
	 * @see org.newdawn.slick.state.BasicGameState#enter(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		//gets where we are coming from
		int previousState = TinyTank.getPreviousState();
		//clears the input so we dont get unintential input
		input.clearMousePressedRecord();
		input.clearKeyPressedRecord();
		int width = 800;
		int height = container.getHeight()*1;
		cam = new Camera(0,0,container.getWidth(),container.getHeight(),1);
		
		//no reason this should happen
		if(previousState == STATES.MAIN_GAMEPLAY.getId()){
			System.out.println("what");
			return;
		}
		
		//this is just a placeholder till we get the weapon select up and running
		if(previousState == STATES.SELECT_WEAPONS_MENU.getId()){
			players = new ArrayList<Tank>();
			map = new TerrainMap(width,height);
			try{
			players = ((Select_Weapons_Menu) STATES.SELECT_WEAPONS_MENU.getState()).getTanks();
			}catch(SlickException e){
				e.printStackTrace();
			}
			players.get(0).setFirstPos();
			players.get(1).setFirstPos();
		}
		
		//sets player one to his turn
		playersTurnIndex =0;
		timeCounter = 0;
		
	} 

	@Override
	public void leave(GameContainer container, StateBasedGame game){
		TinyTank.setPreviousState(id);
	}
	

	/**
	 * Renders our objects to the screen. Renders in layers in order put.
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		//draw order: background,map,tanks,shots
		g.setBackground(new Color(135,150,235));
		map.render(container, game, g, cam);
		for(int i = 0; i < numOfPlayers; i++){
			players.get(i).render(container, game, g, cam);
		}
		if(players.get(0).isTurn()){
			players.get(0).renderHud(container, game, g, cam);
		}
		if(players.get(1).isTurn()){
			players.get(1).renderHud(container, game, g, cam);
		}
		

		
	}

	/**
	 * All gameplay logic should be triggered here.
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		/*
		if(input.isKeyPressed(Input.KEY_Q)){
			//regenerates terrain//for testing only
			map = new TerrainMap(800, container.getHeight());
			players = ((Select_Weapons_Menu) STATES.SELECT_WEAPONS_MENU.getState()).getTanks();
		
		}*/
		/** When p is pressed, go the the pause menu*/
		if(input.isKeyDown(Input.KEY_P)) {
			game.enterState(STATES.PAUSE_MENU.getId());
		}
		/*
		if(input.isKeyDown(Input.KEY_ESCAPE)) {
			game.enterState(STATES.GAME_OVER.getId());
		}*/
		//for now t will be quit game
//		if(input.isKeyDown(KEY_T)) {
//			game.enterState(STATES.GAME_OVER.getId());
//		}

		/***************
		 * Updates go in this if statement below. This type of timestep loop allows the program
		 * to be bound by a constant timestep and not performance of a computer.
		 ***************/
		timeCounter+=delta;
		//if(timeCounter>timeStep && input.isKeyPressed(Input.KEY_LSHIFT)){
		if(timeCounter>timeStep){

			//updates camera
			cam.update();
			
			//updates players and shots
			
			
			//Updates players positions
			for(int i = 0; i < numOfPlayers; i++){
				players.get(i).update(container, game, cam);
			}
			//allows player whose turn it is to move.
			players.get(playersTurnIndex).move(input);
			
			
			if(input.isKeyDown(Input.KEY_UP)){
				cam.adjustPosY(-5);
			}else if(input.isKeyDown(Input.KEY_DOWN)){
				cam.adjustPosY(5);
			}else if(input.isKeyDown(Input.KEY_RIGHT)){
				cam.adjustPosX(5);
			}else if(input.isKeyDown(Input.KEY_LEFT)){
				cam.adjustPosX(-5);
			}else if(input.isKeyDown(Input.KEY_U)){
				cam.adjustScale(.01f);
			}else if(input.isKeyDown(Input.KEY_J)){
				cam.adjustScale(-.01f);
			}
			
			
					
			//if not players turn, switch players
			if(!players.get(playersTurnIndex).isTurn()){
				
				if(playersTurnIndex == 0){
					playersTurnIndex = 1;
				}else if(playersTurnIndex == 1){
					playersTurnIndex = 0;
				}
				players.get(playersTurnIndex).onTurnSwitch();
				onTurnSwitch();
				
			}
			//decrease time counter
			timeCounter-=timeStep;
		}

		//Game is over when the shots are 0
		if(players.get(playersTurnIndex).getShots().size()==0) {
			game.enterState(STATES.GAME_OVER.getId(), new FadeOutTransition(), new FadeInTransition());
		}
		
		
	}
	
	/**
	 * Anything that needs to happen on turn switches.
	 * In the future this will include hud switching and network notifications.
	 */
	private void onTurnSwitch(){
		input.isKeyPressed(Input.KEY_SPACE);
	}

	/**
	 * Gets the id for the this state
	 */
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
