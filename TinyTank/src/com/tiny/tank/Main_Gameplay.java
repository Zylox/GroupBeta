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

public class Main_Gameplay extends BasicGameState{

	private int id;
	private TerrainMap map;
	private Input input;
	
	int ppp=10;
	
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
		
		if(input.isKeyDown(Input.KEY_Q)){
			//regenerates terrain//for testing only
			map = new TerrainMap(container.getWidth(), container.getHeight());
		}
		
		//tests collision detection
		//System.out.println(input.getMouseX() + ", " + input.getMouseY());
		/*
		if(map.collision(new Vector2f(input.getMouseX(), input.getMouseY()))){
			System.out.println("collides");
		}*/
		
		/*ClickTest for exsplosion
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			ppp++;
			map.circleExplosion(input.getMouseX(), input.getMouseY(), ppp);
			map.update();
		}else{
			ppp=10;
		}*/
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
