package com.tiny.guiComponents;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.weapons.Shot;

public class DropDownMenu {

	private ArrayList<Shot> menuItems;
	private ArrayList<Rectangle> boxes;
//	private Rectangle indexed;
	private int indexToShow;
	private Vector2f pos;
	private int width;
	private int height;
	private boolean expanded;

	public DropDownMenu(ArrayList<Shot> shots) {
		this(shots, 0, 0);
	}

	public DropDownMenu(ArrayList<Shot> shots, float x, float y) {
		this(shots, x, y, 100, 20);
	}
	
	public DropDownMenu(ArrayList<Shot> shots, float x, float y, int width, int height) {
		this(shots, new Vector2f(x, y), width, height, false);
	}

	public DropDownMenu(ArrayList<Shot> shots, float x, float y, boolean openUp){
		this(shots,new Vector2f(x,y),100,20,openUp);
	}

	public DropDownMenu(ArrayList<Shot> shots, float x, float y, int width, int height, boolean openUp) {
		this(shots, new Vector2f(x, y), width, height, openUp);
	}
	
	public DropDownMenu(ArrayList<Shot> shots, Vector2f pos, int width, int height, boolean openUp) {
		menuItems = shots;
		
		boxes = new ArrayList<Rectangle>();
		if (!openUp) {
			for (int i = 0; i < menuItems.size(); i++) {
				boxes.add(new Rectangle(pos.x, pos.y + i * height, width,
						height - 1));
			}
		}else{
			for (int i = 0; i < menuItems.size(); i++) {
				boxes.add(new Rectangle(pos.x, pos.y - i * height, width,
						height - 1));
			}
		}
	//	indexed = boxes.get(0);

		this.pos = pos;
		this.width = width;
		this.height = height;
		indexToShow = 0;
		expanded = false;
	}

	public void update(Input input) {
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			float mouseX = input.getMouseX();
			float mouseY = input.getMouseY();
			if (boxes.get(0).contains(mouseX, mouseY)) {
				expanded = !expanded;
				return;
			}

			if(expanded){
				for (int i = 1; i < menuItems.size(); i++) {
					if (boxes.get(i).contains(mouseX, mouseY)) {
						if(i == indexToShow){
							indexToShow = 0;
						}else{
							indexToShow = i;
						}
		//				updateIndexed();
						
						expanded = !expanded;
						return;
					}
				}
			}
		}
	}


	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		Color col = g.getColor();
		g.setColor(Color.black);
		if (expanded) {
			/*
			 * if(pos.y+(menuItems.size()*height) > container.getHeight()){
			 * //expand up-( for(int i = 0; i<menuItems.size(); i++){ g.fill(new
			 * Rectangle(pos.x, pos.y-(height*i), width, height-1)); } }else{
			 */
			// expand down

			g.setColor(Color.white);
			g.fill(boxes.get(0));
			g.setColor(Color.black);
			g.drawString(menuItems.get(indexToShow).getShotName(), boxes.get(0).getX(), boxes.get(0).getY());
			
			for (int i = 1; i < menuItems.size(); i++) {
				Rectangle box = boxes.get(i);
				g.setColor(Color.white);
				g.fill(box);
				g.setColor(Color.black);
				if(i == indexToShow){
					g.drawString(menuItems.get(0).getShotName(), box.getX(), box.getY());	
				}else{
					g.drawString(menuItems.get(i).getShotName(), box.getX(), box.getY());
				}
			}
			// }

		} else {
			g.setColor(Color.white);
			g.fill(boxes.get(0));
			g.setColor(Color.black);
			g.drawString(menuItems.get(indexToShow).getShotName(), boxes.get(0).getX(), boxes.get(0).getY());
		}
		
		g.setColor(col);
	}

	
	public void inializeOnTurn(ArrayList<Shot> shots){
		if(boxes.get(0).getX() < boxes.get(1).getX()){
			for (int i = 0; i < menuItems.size(); i++) {
				boxes.add(new Rectangle(pos.x, pos.y - i * height, width, height - 1));
			}
		}else if(boxes.get(0).getX() > boxes.get(1).getX()){
			for (int i = 0; i < menuItems.size(); i++) {
				boxes.add(new Rectangle(pos.x, pos.y + i * height, width, height - 1));
			}			
		}		
	}
	/*
	private void updateIndexed() {
		Rectangle ind = boxes.get(0);
		indexed = new Rectangle(ind.getX(), ind.getY(), ind.getWidth(),
				ind.getHeight());
	} */
	
	public void setOpenUp(boolean openUp){
		if(openUp && boxes.get(0).getX() < boxes.get(1).getX()){
			for (int i = 0; i < menuItems.size(); i++) {
				boxes.add(new Rectangle(pos.x, pos.y - i * height, width, height - 1));
			}
		}else if(!openUp && boxes.get(0).getX() > boxes.get(1).getX()){
			for (int i = 0; i < menuItems.size(); i++) {
				boxes.add(new Rectangle(pos.x, pos.y + i * height, width, height - 1));
			}			
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

	public int getIndexToShow() {
		return indexToShow;
	}
	
	public void setIndexToShow(int indexToShow) {
		this.indexToShow = indexToShow;
	}
}
