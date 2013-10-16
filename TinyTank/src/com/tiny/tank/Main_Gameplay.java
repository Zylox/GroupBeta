package com.tiny.tank;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.terrain.TerrainMap;

public class Main_Gameplay extends BasicGameState{

	private int id;
	private TerrainMap map;
	private Input input;
	
	
	public Main_Gameplay(int id){
		this.id = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		map = new TerrainMap(container.getWidth(),container.getHeight());
		input = container.getInput();
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
		
		if(input.isKeyPressed(Input.KEY_Q)){
			//regenerates terrain//for testing only
			map = new TerrainMap(container.getWidth(), container.getHeight());
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
