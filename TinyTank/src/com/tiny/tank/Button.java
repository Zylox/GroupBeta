package com.tiny.tank;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;



public class Button {
	private Image button;
	private Vector2f buttonPos;
	Input input;
	int posX;
	int posY;
	
	public Button(String path, int startingX,int startingY,GameContainer game) {
		try {
			/**if the path exists, success*/
			this.button=new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.buttonPos = new Vector2f(startingX,startingY);
		input = game.getInput();
	}
	
	/**prints button on screen */
	public void drawButton(Graphics g) {
		button.draw(buttonPos.x,buttonPos.y);
		
		/**this is just for debugging
		g.draw(new Rectangle(buttonPos.x,buttonPos.y,button.getWidth(),button.getHeight()));*/
	}
	/**Returns true of the mouse is over the button*/
	public Boolean isMouseOverButton() {
		posX=input.getMouseX();
		posY=input.getMouseY();
		
		boolean mouseOver= posX > buttonPos.x && posX < buttonPos.x + button.getWidth() && posY > buttonPos.y && posY < buttonPos.y+button.getHeight(); 
//		if(mouseOver) {
//			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
//				return true;
//			}
		return mouseOver;
	}
	public Boolean isButtonClicked() {
		if(isMouseOverButton()) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
