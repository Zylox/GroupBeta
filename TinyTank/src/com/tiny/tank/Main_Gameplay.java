package com.tiny.tank;

import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.terrain.TerrainMap;
import com.tiny.weapons.Shot;
import com.tiny.weapons.shots.Shots;

public class Main_Gameplay extends BasicGameState{
	Image pause;
	
	private int id;
	public static TerrainMap map;
	private Input input;
	
	private final int timeStep = 10;
	private int timeCounter;
	
	private int numOfPlayers = 2;
	public static ArrayList<Tank> players;
	
	public Main_Gameplay(int id){
		this.id = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		map = new TerrainMap(container.getWidth(),container.getHeight());
		input = container.getInput();
		timeCounter = 0;
		pause=new Image("res/menu.png");
		players = new ArrayList<Tank>();
		
	}
	
	/*
	 * Called when entering the state
	 * @see org.newdawn.slick.state.BasicGameState#enter(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame game){
		//gets where we are coming from
		int previousState = TinyTank.getPreviousState();
		//clears the input so we dont get unintential input
		input.clearMousePressedRecord();
		input.clearKeyPressedRecord();
		
		//no reason this should happen
		if(previousState == STATES.MAIN_GAMEPLAY.getId()){
			System.out.println("what");
			return;
		}
		
		//this is just a placeholder till we get the weapon select up and running
		previousState = 5;
		if(previousState == 5){
			players = getTanks();
		}
	} 

	/*
	 * Placeholder function until weapons select is set up
	 */
	private ArrayList<Tank> getTanks(){
		
		ArrayList<Tank> tanks = new ArrayList<Tank>();
		
		tanks.add(new Tank());
		tanks.add(new Tank());
		ArrayList<Shot> weapons = new ArrayList<Shot>();
		
		weapons.add(Shots.BIG_SHOT.getShot());
		weapons.add(Shots.NORMAL_SHOT.getShot());
		weapons.add(Shots.BIG_SHOT.getShot());
		weapons.add(Shots.BIG_SHOT.getShot());
		weapons.add(Shots.NORMAL_SHOT.getShot());
		
		
		tanks.get(0).TankInfo(200, 10, 70, 100, weapons, 1);
		tanks.get(1).TankInfo(500, 10, 70, 100, weapons, 2);		
		tanks.get(0).setFirstPos();
		tanks.get(1).setFirstPos();
		
		return tanks;
		//this is what i want it to be eventually;
		//return Weapon_Select_State.getTanks();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		//draw order: background,map,tanks,shots
		g.setBackground(Color.gray);
//		pause.draw(100,0);
		map.getImage().draw();
		for(int i = 0; i < numOfPlayers; i++){
			players.get(i).render(container, game, g);
		}
		players.get(0).getShots().get(0).render(container, game, g);
			
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

		if(input.isKeyDown(Input.KEY_Q)){
			//regenerates terrain//for testing only
			map = new TerrainMap(container.getWidth(), container.getHeight());
			players = getTanks();
		}
		if(input.isKeyDown(Input.KEY_P)) {
			//pause button
			game.enterState(STATES.PAUSE_MENU.getId());
		}

		//put updates in here
		timeCounter+=delta;
		if(timeCounter>timeStep){
			//updates players and shots
			for(int i = 0; i < numOfPlayers; i++){
				players.get(i).update();
			}

			//test click bomb
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				players.get(0).getShots().get(0).init(new Vector2f(input.getMouseX(), input.getMouseY()));
				players.get(0).setShooting(true);
				map.update();
			}
					
			timeCounter-=timeStep;
		}
		
	}

	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
