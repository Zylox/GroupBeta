package com.tiny.guiComponents;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.weapons.Shot;

public class DropDownMenu {

	private final int buttonWidth = 150;
	private final int buttonHeight = 20;
	
	private ArrayList<Shot> menuItems;
	private int indexToShow;
	private Vector2f pos;
	private int width;
	private int height;
	private boolean expanded;
	
	public DropDownMenu(ArrayList<Shot> shots){
		this(shots, 0,0);
	}
	
	public DropDownMenu(ArrayList<Shot> shots, float x, float y){
		this(shots, x,y,100,20);
	}
	
	public DropDownMenu(ArrayList<Shot> shots, Vector2f pos){
		this(shots, pos, 20, 100);
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
		expanded = false;
	}

	public void update(Input input){
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			float mouseX = input.getMouseX();
			float mouseY = input.getMouseY();
			if((mouseX > pos.x && mouseX < pos.x+width) && (mouseY > pos.y && mouseY < pos.y+height)){
				expanded = !expanded;
			}
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g){
		Color col = g.getColor();
		Random ran = new Random();
		if(expanded){
			if(pos.y+(menuItems.size()*height) > container.getHeight()){
				//expand up-(
				for(int i = 0; i<menuItems.size(); i++){
					//g.setColor(new Color(ran.nextInt(255)));
					g.fill(new Rectangle(pos.x, pos.y-(height*i), width, height-1));
				}
			}else{
				//expand down
				for(int i = 0; i<menuItems.size(); i++){
					//g.setColor(new Color(ran.nextInt(255)));				
					g.fill(new Rectangle(pos.x, pos.y+(height*i), width, height-1));
				}				
			}
			
		}else{
			g.fill(new Rectangle(pos.x,pos.y,width,height));
		}
		g.setColor(col);
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
