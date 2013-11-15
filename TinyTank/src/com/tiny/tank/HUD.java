package com.tiny.tank;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
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
	private Vector2f hudPos;
	private int shotIndex;
	
	public void loadImages() throws SlickException{
		//hud_background = new Image("res/hud_background.png");
		weapons_head = new Image("res/weapons.png");
		//move_head = new Image("res/Move.png");
		angle_head = new Image("res/Angle.png");
		power_head = new Image("res/Power.png");
		lweapons = new Button("res/LeftArrow.png", (int) (hudPos.x+320), (int) (hudPos.y+50));
		rweapons = new Button("res/RightArrow.png", (int) (hudPos.x+440), (int) (hudPos.y+50));
		//lmove = new Button("res/Left Arrow.png", 300, 500);
		//rmove = new Button("res/Right Arrow.png", 500, 500);
		langle = new Button("res/LeftArrow.png", (int) (hudPos.x+100), (int) (hudPos.y+50));
		rangle = new Button("res/RightArrow.png", (int) (hudPos.x+220), (int) (hudPos.y+50));
		lpower = new Button("res/LeftArrow.png", (int) (hudPos.x+540), (int) (hudPos.y+50));
		rpower = new Button("res/RightArrow.png", (int) (hudPos.x+660), (int) (hudPos.y+50));
			
	}
	
	
	public HUD(float barrelAng, int power, int gas, 
			int health, ArrayList<Shot> shots, int index, Vector2f hudPos) throws SlickException {
		
		this.barrelAng = barrelAng;
		this.power = power;
		this.gas = gas;
		this.health = health;
		this.shots = shots;
		this.index = index;
		this.hudPos = hudPos;
		loadImages();
		
		shotIndex = 0;
		
	}

	public void enter(GameContainer container, StateBasedGame game){
		
		try {
			hud_background = new Image(container.getWidth(), 200);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// current graphical representation
		//hud_background.draw();
		Color prevCol = g.getColor();
		g.setColor(new Color(0, 0,0, 300));
		g.fillRect(hudPos.x, hudPos.y, container.getWidth(), 90);
		//g.draw(new Rectangle(0,hudY,container.getWidth(),90));
		weapons_head.draw((int) (hudPos.x+320),(int)(hudPos.y+10));
		angle_head.draw((int)(hudPos.x+100),(int)(hudPos.y+10));
		power_head.draw((int)(hudPos.x+540),(int)(hudPos.y+10));
		//move_head.draw(550,450);
		rweapons.drawButton(g);
		lweapons.drawButton(g);
		//rmove.drawButton(g);
		//lmove.drawButton(g);
		rangle.drawButton(g);
		langle.drawButton(g);
		rpower.drawButton(g);
		lpower.drawButton(g);
		g.setColor(Color.white);
		//Angle
		g.drawString(Float.toString(barrelAng), (int)(hudPos.x+160), (int)(hudPos.y+57));
		g.drawString(Integer.toString(power), (int)(hudPos.x+605), (int)(hudPos.y+57));
	
		if(shots.size() > 0){
			g.drawString(shots.get(shotIndex).getShotName(), (int)(hudPos.x+360), (int)(hudPos.y+50));
		}
		g.setColor(prevCol);

	}
	
	public void update(GameContainer container, StateBasedGame game){
		input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int posX = input.getMouseX();
			int posY = input.getMouseY();
			
			if (rweapons.isMouseOverButton(posX, posY)) {
				shotIndex++;
				if(shotIndex > shots.size()-1){
					shotIndex=0;
				}
			}
			else if (lweapons.isMouseOverButton(posX, posY)) {
				System.out.println("lweapons!");
				shotIndex--;
				if(shotIndex < 0){
					shotIndex=shots.size()-1;
				}
			}
			/*
			else if (rmove.isMouseOverButton(posX, posY)) {
				
			}
			else if (lmove.isMouseOverButton(posX, posY)) {
				
			}
			*/
			else if (rangle.isMouseOverButton(posX, posY)) {
				System.out.println("rangle!");
				if(barrelAng < 180){
					barrelAng++;
				}
			}
			else if (langle.isMouseOverButton(posX, posY)) {
				System.out.println("langle!");

				if(barrelAng > 0){
					barrelAng--;
				}
			}
			else if (rpower.isMouseOverButton(posX, posY)) {
				System.out.println("rpower!");
				if(power < 100){
					power++;
				}
			}
			else if (lpower.isMouseOverButton(posX, posY)) {
				System.out.println("lpower!");
				if(power > 0){
					power--;
				}
			}

		}
		
		
	}

	
	public void onTurnSwitch(ArrayList<Shot> shots){
		shotIndex = 0;
		this.shots = shots;
	}

	public Vector2f getHudPos() {
		return hudPos;
	}


	public void setHudPos(Vector2f hudPos) {
		this.hudPos = hudPos;
	}



}
