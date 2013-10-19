package com.tiny.tank;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.terrain.TerrainMap;
import com.tiny.weapons.Shot;
import com.tiny.weapons.shots.Shots;

public class Main_Gameplay extends BasicGameState{

	private int id;
	public static TerrainMap map;
	private Input input;
	
	private final int timeStep = 100;
	private int timeCounter;
	
	private int numOfPlayers = 2;
	private ArrayList<Tank> players;
	
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
		
		players = new ArrayList<Tank>();
		for(int i = 1; i <= numOfPlayers; i++){
			players.add(new Tank());
		}
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game){
		int previousState = TinyTank.getPreviousState();
		
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
		
		
		tanks.get(0).TankInfo(100, 100, 70, 100, weapons, 1);
		tanks.get(1).TankInfo(100, 100, 70, 100, weapons, 2);		
		return tanks;
		//this is what i want it to be eventually;
		//return Weapon_Select_State.getTanks();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.setBackground(Color.gray);
		map.getImage().draw();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
		if(input.isKeyDown(Input.KEY_Q)){
			//regenerates terrain//for testing only
			map = new TerrainMap(container.getWidth(), container.getHeight());
		}

		//put updates in here
		timeCounter+=delta;
		if(timeCounter>timeStep){
			
			
			timeCounter-=delta;
		}
		
	}

	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
