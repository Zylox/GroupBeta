package com.tiny.tank;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
public class Button {
	private Image button;
	private Vector2f buttonPos;

	
	public Button(String path, int startingX,int startingY) {
		try {
			/**if the path exists, success*/
			this.button=new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.buttonPos = new Vector2f(startingX,startingY);
	}
	
	/**prints button on screen */
	public void drawButton(Graphics g) {
		button.draw(buttonPos.x,buttonPos.y);
		
		/**this is just for debugging
		g.draw(new Rectangle(buttonPos.x,buttonPos.y,button.getWidth(),button.getHeight()));*/
	}
	/**Returns true of the mouse is over the button*/
	public Boolean isMouseOverButton(int mousePosX,int mousePosY) {
		boolean mouseOver= mousePosX > buttonPos.x && mousePosX < buttonPos.x + button.getWidth() && mousePosY > buttonPos.y && mousePosY < buttonPos.y+button.getHeight(); 
		return mouseOver;
	}
	
}
