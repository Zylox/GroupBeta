package com.tiny.tank;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.weapons.Shot;

public class SimpleTempButton {

	private Vector2f pos;
	private int width;
	private int height;
	private Rectangle button;
	private Shot shot;
	
	public SimpleTempButton(Vector2f pos, int width, int height, Shot shot){
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.shot = shot;
		button = new Rectangle(pos.x,pos.y,width,height-1);
	}
	
	public boolean update(Input input, boolean clicked){
		if(isClicked(input,clicked)){
			return true;
		}
		
		return false;
	}
	
	private boolean isClicked(Input input, boolean clicked){
		if(clicked){
			int x = input.getMouseX();
			int y = input.getMouseY();
			if(button.contains(x, y)){
				return true;
			}
		}
		
		
		return false;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
		g.drawString(shot.getShotName().toUpperCase(), pos.x+20, pos.y);
		g.draw(button);
	}

	private void reInitRectangle(){
		button = new Rectangle(pos.x,pos.y,width,height);

	}

	private Rectangle getButton() {
		return button;
	}

	public Vector2f getPos() {
		return pos;
	}


	public void setPos(Vector2f pos) {
		this.pos = pos;
		reInitRectangle();
	}

	public Shot getShot() {
		return shot;
	}

	public void setShot(Shot shot) {
		this.shot = shot;
	}

	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
		reInitRectangle();
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
		reInitRectangle();
	}
	
	
}
