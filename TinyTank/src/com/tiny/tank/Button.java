package com.tiny.tank;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Button {
	private Image button;
	private Image originalImage;
	// private int posX;
	// private int posY;
	private Vector2f buttonPos;
	private Vector2f dimensions;

	// private int mousePosX;
	// private int mousePosY;

	public Button(String path, int startingX, int startingY) {
		this(path, startingX, startingY, 0, 0);
	}

	public Button(String path, int startingX, int startingY, int width,
			int height) {

		try {
			this.originalImage = new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		buttonPos = new Vector2f(startingX, startingY);
		if (width == 0 || height == 0) {
			button = originalImage;
			dimensions = new Vector2f(button.getWidth(), button.getHeight());
		} else {
			dimensions = new Vector2f(width, height);
			button = scaleImage(originalImage, width, height);
		}
	}

	/** prints button on screen */
	public void drawButton(Graphics g) {
		button.draw(buttonPos.x, buttonPos.y);
		// this is just for debugging
		// g.draw(new
		// Rectangle(buttonPos.x,buttonPos.y,button.getWidth(),button.getHeight()));

	}

	public Boolean isMouseOverButton(int mousePosX, int mousePosY) {
		boolean mouseOver = mousePosX > buttonPos.x
				&& mousePosX < buttonPos.x + dimensions.x
				&& mousePosY > buttonPos.y
				&& mousePosY < buttonPos.y + dimensions.y;
		return mouseOver;
	}

	public void resizeButton(int x, int y) {

		if (x != dimensions.x || y != dimensions.y) {
			dimensions.x = x;
			dimensions.y = y;

			button = scaleImage(button, x, y);
		}
	}

	private Image scaleImage(Image button, int x, int y) {
		try {
			button.setFilter(Image.FILTER_NEAREST);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		button.getScaledCopy(x, y);

		return button;

	}

}
