package com.tiny.guiComponents;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.weapons.Shot;

public class DropDownMenu {

	private ArrayList<Shot> menuItems;
	private int indexToShow;
	private Vector2f pos;
	private int width;
	private int height;
	private boolean expanded;
	
	public DropDownMenu(ArrayList<Shot> shots){
		this(shots, 0,0, 20,100);
	}
	
	public DropDownMenu(ArrayList<Shot> shots, float x, float y, int width, int height){
		this(shots,new Vector2f(x,y), width, height);
	}
	
	public DropDownMenu(ArrayList<Shot> shots, Vector2f pos, int width, int height){
		menuItems = shots;
		this.pos = pos;
		this.width = width;
		this.height = height;
		indexToShow = 0;
	}

	public void update(){
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
		if(expanded){
			if(pos.x+menuItems.size()*height > container.getHeight()){
				//expand up
				
			}else{
				//expand down
				
			}
			
		}else{
			
		}
	}
	
	public ArrayList<Shot> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(ArrayList<Shot> menuItems) {
		this.menuItems = menuItems;
	}

	public int getIndex() {
		return indexToShow;
	}

	public void setIndex(int index) {
		this.indexToShow = index;
	}

	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
