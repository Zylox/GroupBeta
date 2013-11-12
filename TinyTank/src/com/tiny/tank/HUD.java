package com.tiny.tank;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.tiny.guiComponents.Button;
import com.tiny.weapons.Shot;

public class HUD {
	
	private Image hud_background;
	private Input input;
	private float barrelAng;
	private int power;
	private int gas;
	private int health;
	private int index;
	private ArrayList<Shot> shots;
	private Image weapons_head;
	private Image move_head;
	private Image angle_head;
	private Image power_head;
	private Button lweapons;
	private Button rweapons;
	private Button lmove;
	private Button rmove;
	private Button langle;
	private Button rangle;
	private Button lpower;
	private Button rpower;
	
	
	public void loadImages() throws SlickException{
		//hud_background = new Image("res/hud_background.png");
		weapons_head = new Image("res/weapons.png");
		move_head = new Image("res/Move.png");
		angle_head = new Image("res/Angle.png");
		power_head = new Image("res/Power.png");
		lweapons = new Button("res/Left Arrow.png", 200, 500);
		rweapons = new Button("res/Right Arrow.png", 300, 500);
		lmove = new Button("res/Left Arrow.png", 300, 500);
		rmove = new Button("res/Right Arrow.png", 500, 500);
		langle = new Button("res/Left Arrow.png", 400, 500);
		rangle = new Button("res/Right Arrow.png", 500, 500);
		lpower = new Button("res/Left Arrow.png", 500, 500);
		rpower = new Button("res/Right Arrow.png", 500, 500);
			
	}
	
	public HUD(float barrelAng, int power, int gas, 
			int health, ArrayList<Shot> shots, int index) throws SlickException {
		
		this.barrelAng = barrelAng;
		this.power = power;
		this.gas = gas;
		this.health = health;
		this.shots = shots;
		this.index = index;
		loadImages();
		
	}

	public void enter(GameContainer container, StateBasedGame game){
		
		try {
			hud_background = new Image(container.getWidth(), 200);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		input = container.getInput();
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// current graphical representation
		//hud_background.draw();
		weapons_head.draw(400,450);
		angle_head.draw(100,450);
		power_head.draw(250,450);
		move_head.draw(550,450);

		lweapons.drawButton(g);
		rweapons.drawButton(g);
		rmove.drawButton(g);
		lmove.drawButton(g);
		rangle.drawButton(g);
		langle.drawButton(g);
		rpower.drawButton(g);
		lpower.drawButton(g);

	}
	
	public void update(GameContainer container, StateBasedGame game){
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int posX = input.getMouseX();
			int posY = input.getMouseY();
			
			if (rweapons.isMouseOverButton(posX, posY)) {
				
			}
			else if (lweapons.isMouseOverButton(posX, posY)) {
				
			}
			/*
			else if (rmove.isMouseOverButton(posX, posY)) {
				
			}
			else if (lmove.isMouseOverButton(posX, posY)) {
				
			}
			*/
			else if (rangle.isMouseOverButton(posX, posY)) {
				if(barrelAng < 180){
					barrelAng++;
				}
			}
			else if (langle.isMouseOverButton(posX, posY)) {
				if(barrelAng > 0){
					barrelAng--;
				}
			}
			else if (rpower.isMouseOverButton(posX, posY)) {
				if(power < 100){
					power++;
				}
			}
			else if (lpower.isMouseOverButton(posX, posY)) {
				if(power > 0){
					power--;
				}
			}

		}
	}



}
