package com.tiny.tank;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.terrain.TerrainMap;
import com.tiny.weapons.shots.NormalShot;

public class Main_Gameplay extends BasicGameState{

	private int id;
	public static TerrainMap map;
	private Input input;
	
	private final int timeStep = 100;
	private int timeCounter;
	
	private NormalShot test;
	
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
		test = new NormalShot(new Vector2f(0,0), 50, 10, null);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.setBackground(Color.gray);
		map.getImage().draw();
		test.render(container, game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
		if(input.isKeyDown(Input.KEY_Q)){
			//regenerates terrain//for testing only
			map = new TerrainMap(container.getWidth(), container.getHeight());
		}
		
		timeCounter+=delta;
		if(timeCounter>timeStep){
			
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				test.init(new Vector2f(input.getMouseX(),input.getMouseY()));
			}
			
			test.update();
			
			timeCounter-=delta;
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
